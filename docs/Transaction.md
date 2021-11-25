
# 事务家族

## Mysql事务

## JDBC事务

## Spring事务

## 分布式事务

## Sharding-Sphere事务

## RocketMQ事务

## ActiveMQ事务

## MongoDB事务

## 基于分表分库场景下的复杂分布式事务分析

用户UserA打赏用户UserB,打赏金额5元, 用户A账户余额0, 需要先充值, 充值完自动完成打赏动作

### 规则: 
   
    - 基于UID取模/2 分库分库
    - 订单表、流水表基于UID/2分库、订单、流水ID取模/2分表
    - 同库分表基于同库操作,使用本地事务
    - 服务划分: 用户服务、账户服务、订单服务

- UserB 用户表: db_0.tb_user_0..1 账户表: db_0.tb_user_account_0..1 流水表: db_0.tb_user_account_log_0..1 订单表: db_0_tb_order_0..1
- UserA 用户表: db_1.tb_user_0..1 账户表: db_0.tb_user_account_1..1 流水表: db_1.tb_user_account_log_0..1 订单表: db_1_tb_order_0..1

- UserB uid = 2 db_0.tb_user_0 账户表: db_0.tb_user_account_0 流水表: db_0.tb_user_account_log_0..1
- UserA uid = 1 db_1.tb_user_1 账户表: db_1.tb_user_account_1 流水表: db_1.tb_user_account_log_0..1

### 顺序图

1. ->【本地事务】 UserA发起打赏, 创建订单表订单号:id=1 db_1_tb_order_1
2. UserA发起充值动作, 支付完成
3. 回调验证(微信、支付宝) -> 收到支付确认回调 | 主动验证(IAP苹果支付) -> 主动根据IAP凭证确认支付结果
4. ->【本地事务】 验证通过, 修改UserA用户账户, db_1.tb_user_account_1 -> 5元, 新增入账流水logId=1: db_1.tb_user_account_log_1
5. 发起MQ消息/发起事务动作, 执行打赏命令
6. =>【RPC事务、跨库事务】 1)更新订单完成, 2)修改UserA账户, 3)生成UserA出账流水, 4)修改UserB账户, 5)生成UserB出账流水
7. 发起MQ消息/发起异步动作, 推送打赏消息

### 分布式事务

第6步涉及到跨VM RPC事务和本地跨库事务, 分别使用一下四种事务实现(不包含XA 2PC事务)

 - MQ异步确保型事务, 1)更新订单信息 -> { 发送MQ1 -> 修改UserA账户 -> 发送MQ1.1 -> 生成出账流水, 发送MQ2 -> 修改UserB账户 -> 发送MQ2.1 -> 生成UserB出账信息 }
 - TCC
 - SAGA
 - Fescar 目前Fescar前提是确保本地事务的ACID一致性, 因存在单VM跨库事务存在,所以Feascar需要匹配其他方案执行或者执行二次拆分成多库逻辑,意味着存在N个库=拆分成N条逻辑执行

### 支付订单检查及对账

- 定时检查: 定时检查未完成或未关闭的订单,检查支付状态并循环执行 3 -> 7
- 每日对账: 每日下载三方支付对账单, 判断是否存在异常订单
