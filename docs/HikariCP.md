
# 兔子洞看看 - HikariCP如何做到高出其他线程池百倍千倍的性能测试数据

<a href="https://github.com/brettwooldridge/HikariCP">官网: https://github.com/brettwooldridge/HikariCP</a>

<自译>
是该亮出秘密武器了, 当你跟我们一样通过基准测试想知道的一系列的疑问都是必须要解决的, 当你在思考连接池的性能时, 你可能会被带着想去了解线程池才是整个性能方程式里面最重要的部分吧, 不太清楚, 囧, 对比其他JDBC, getConnection() 操作更小, 汇小成大, 剥削诸如 'delegates', Connection, Statement 其中的个操作的性能优化


## 我们进入到了你的机器大脑中

为了让HikariCP能够更快, 我们深入到字节码级处理引擎, 并且比这还要深。 我们变出各种戏法让JIT都来帮助你,  我们分析从编译器得到字节码, 甚至从JIT得到的汇编指令使其比内联阈值优化还要小, 我们扁平化了继承层次结构，隐藏了成员变量，消除了强制转换。


## 微优化 - 显微知著

### ArrayList

### ConcurrentBag

- 无锁设计
- ThreadLocal缓存
- 队列窃取
- 交付优化


### Invocation: invokevirtual vs invokestatic




## 量子调度器


## 消除CPU缓存行伪共享

