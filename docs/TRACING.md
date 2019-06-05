## 实战全链路跟踪 Skywalking/Zipkin

理解链路中的概念

1. logging - event时间落地
2. tracing - request范围
3. metrics - aggr聚合分析

参考: https://wu-sheng.github.io/me/articles/metrics-tracing-and-logging

zipkin使用: https://github.com/xuminwlt/j360-zipkin-parent
zipkin学习框架: https://github.com/xuminwlt/j360-trace

**Skywalking**

### Skywalking常用架构方案：

- zipkin+skywalking

**a) skywalkagent**
1. jvm
2. mesh

**b) zipkin**
1. trace

- skywalking agent + skywalking

**Skywalking Server部署**

https://github.com/apache/skywalking/blob/master/docs/en/setup/backend/backend-ui-setup.md

1. Server是一个前后端分离的架构, 生产部署时使用独立部署方案: 独立UI + 多集群化Backend模式
2. Backend部署时可能采用单机单VM或者单机多VM方案, 此时需要配置ip+port, application.yml提供3级的配置
3. 集群模式下初始化: 必须只能启动一台机器完成初始化过程, 集群的注册中心使用注册中心组建zk等需要完成对应的配置
4. 完成接收器的配置
5. 完成接收数据存储的配置
5. 完成链路采样率配置
6. 完成数据库慢查询定义的参数配置
7. 完成报警的功能配置

提供: grafana UI支持

**Skywalking Agent部署**

1. 查看Supported list确认支持的中间件和框架,确认收集信息颗粒度
2. 可选配置外部config方案和外部环境配置变量配置,否则使用默认的config配置
3. 将agent默认拷贝至统一的目录, 使用shell方案通过agent命令完成配置

https://github.com/apache/skywalking/blob/master/docs/en/setup/service-agent/java-agent/README.md



Q&A
1. 如何手动完成部分SPAN API追踪
https://github.com/apache/skywalking/blob/master/docs/en/setup/service-agent/java-agent/Application-toolkit-trace.md

2. 如何支持跨线程问题
https://github.com/apache/skywalking/blob/master/docs/en/setup/service-agent/java-agent/Application-toolkit-trace-cross-thread.md

3. 如何自定义行为的范围
https://github.com/apache/skywalking/blob/master/docs/en/setup/service-agent/java-agent/Customize-enhance-trace.md

https://github.com/apache/skywalking/blob/master/docs/en/setup/README.md

