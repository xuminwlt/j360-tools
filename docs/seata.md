seata 源码阅读

## 相关概念

**2PC**

1. TC
2. TM
3. RM

**seata**



## undoLog生成规则

1. 分析更新语句
```
update * from test where name like %match%

```

2. 查询更新语句对应的Rows
```
select * from test where name like %match%
```

3. 分析对应Rows对应的PK, 主键IDS
4. 以主键PK作为关键字,生成对应行数据到undoLog
5. 执行业务逻辑 进入Commit6 或者rollback7
6. 提交业务本地事务, 异步1S一次执行undoLog对应batch delete
7. 撤销回滚本地事务, 执行undoLog对应的PK, 并按照undoLog恢复之前数据, 最后删除undoLog

**Q&A**
性能问题

checkSql
```
private static final String CHECK_SQL_TEMPLATE = "SELECT * FROM %s WHERE %s in (%s)";
String checkSQL = String.format(CHECK_SQL_TEMPLATE, sqlUndoLog.getTableName(), pkName,
                   replace.substring(0, replace.length() - 1));
```
beforeImage:
根据主键PK+update columns进行log保存,此处做到了按需存储undo
afterImage: 
同理实现方式,获取最后的undo信息
在执行update钱通过checkSql方式检查是否需要执行undo逻辑,比如update没有任何数据情况,则跳过
```
public static TableRecords buildRecords(TableMeta tmeta, ResultSet resultSet) throws SQLException {
        TableRecords records = new TableRecords(tmeta);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
            List<Field> fields = new ArrayList<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String colName = resultSetMetaData.getColumnName(i);
                ColumnMeta col = tmeta.getColumnMeta(colName);
                Field field = new Field();
                field.setName(col.getColumnName());
                if (tmeta.getPkName().equals(field.getName())) {
                    field.setKeyType(KeyType.PrimaryKey);
                }
                field.setType(col.getDataType());
                field.setValue(resultSet.getObject(i));
                fields.add(field);
            }

            Row row = new Row();
            row.setFields(fields);

            records.add(row);
        }
        return records;
    }
```


## 事务存储相关

1. 启动服务生成 sessionStore/root.data
2. 开始事务1 TC:DefaultCore.begin StoreMode:DB,FILE -> SessionStore

```
    /**
     * The constant ROOT_SESSION_MANAGER_NAME.
     */
    public static final String ROOT_SESSION_MANAGER_NAME = "root.data";
    /**
     * The constant ASYNC_COMMITTING_SESSION_MANAGER_NAME.
     */
    public static final String ASYNC_COMMITTING_SESSION_MANAGER_NAME = "async.commit.data";
    /**
     * The constant RETRY_COMMITTING_SESSION_MANAGER_NAME.
     */
    public static final String RETRY_COMMITTING_SESSION_MANAGER_NAME = "retry.commit.data";
    /**
     * The constant RETRY_ROLLBACKING_SESSION_MANAGER_NAME.
     */
    public static final String RETRY_ROLLBACKING_SESSION_MANAGER_NAME = "retry.rollback.data";
```


## 通信连接相关

1. TC和TM/RM只通讯存储事务元数据和锁相关关键数据
2. RM undoLog保存于本机文件或者数据库中

lockKeys
模拟数据库行锁设计的PK
```
 /**
     * build lockKey
     *
     * @param rowsIncludingPK the records
     * @return the string
     */
    protected String buildLockKey(TableRecords rowsIncludingPK) {
        if (rowsIncludingPK.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(rowsIncludingPK.getTableMeta().getTableName());
        sb.append(":");
        int filedSequence = 0;
        for (Field field : rowsIncludingPK.pkRows()) {
            sb.append(field.getValue());
            filedSequence++;
            if (filedSequence < rowsIncludingPK.pkRows().size()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
```

## 集群相关

Server集群部署


## 最终一致性检查

A -> B -> C -> D

B success
C success
1. D fail 发生宕机, undo未回滚, 如何判断失败
2. D success 发生宕机, undo未删除, 如何判断成功

TM控制事务提交和回滚
TC控制所有Branch提交和回滚


**Q&A**
1. RM undoLog是否应该和业务BizDB在一台数据库上面?
2. undo过程中发生的table表结构变化问题?
3. 如何确保RM中的业务success、fail和undo逻辑宕机问题?

猜测:
补偿通过事务反向回查实现最终一致性补偿。


对话作者:
https://mp.weixin.qq.com/s?__biz=MzU4NzU0MDIzOQ==&mid=2247485437&idx=1&sn=e2fa8769966468faa63f1eeba7ec646c&chksm=fdeb359dca9cbc8b71a48fb6e127af57097a9eed5b6318fbced19f88d053af1ae1ddfcb74d8a&scene=21#wechat_redirect

```
Q9：因网络中断、网张闪断、节点宕机和超时等引起的异常，Fescar会提供相应的补偿措施么？

A9：这些异常情况的处理是分布式事务解决方案的基本要求，Fescar 同样也是提供了整套方案来处理各类异常场景。这方面的具体机制会在 HA Cluster 版本发布时，给出全面的分析介绍。

```
