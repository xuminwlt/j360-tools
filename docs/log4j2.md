# Log4j2 平衡与优化

用java来实现或者优化数据存储功能,手头上能快速想到参考的框架很多,复杂的ActiveMQ,RocketMQ等,作为数据落盘本地的功能之一,log4j2的实现方案参考意义很大

就学习落盘这个机会,顺便了解下log4j2的一些使用上的平衡和优化,能有针对性的对项目中的log框架进行优化调整,这里仅仅针对落盘也就是Appender相关的内容。

Log4j2 2.9.0+

## 涉及到的类和知识点

- FileAppender
- RandomAccessFileAppender
- RandomAccessFileManager
- AsyncAppender

--------------------

- ByteBuffer
- RandomAccessFile
- LMAX Disruptor

## 写日志过程

在进行任何的log.info、log.warn、log.error等操作就会触发一次日志写操作,这些写操作是在log框架完全初始化的情况下执行的,也就是写操作前已经确定该操作的执行主线过程,这些过程对应的主要参数分别有

- 是否append append
- 是否使用缓存 bufferedIO(仅限FileAppender)
- 缓冲池大小
- 是否立即刷新 immediateFlush

### 无缓冲

每次执行log写操作,在FileAppender中可配置,使用常规方式进行写操作

- log转化成byte
- 使用FileChannel.write(byte)

### 有缓冲

- log转化成byte -> ByteBuffer
- 查看是否理解刷新
- 查看log大小和缓冲池大小
- 再次判断是否满足刷盘条件
- 刷盘

### 异步

异步(AsyncAppender)是建立在底层Appender上面的一个组合型Appender,使用队列来实现异步任务的存储和分发,异步操作在大部分场景下可以明显提高日志吞吐量,在log4j中的配置项是队列的选择
默认使用:ArrayBlockingQueue

```
Log4j-2.9 and higher require disruptor-3.3.4.jar or higher on the classpath. Prior to Log4j-2.9, disruptor-3.0.0.jar or higher was required.
https://logging.apache.org/log4j/2.x/manual/async.html

```
LMAX Disruptor

需要配置对应的asyncLoggerRingBufferSize,默认256 * 1024

## 实时还是吞吐量



## Location信息

本地信息需要耗费大量的性能和时间代价,所以线上环境中,尽量取消本地信息获取
同步环境:1.3-5倍时长
异步环境:30-100倍时长

```
If one of the layouts is configured with a location-related attribute like HTML locationInfo, or one of the patterns %C or $class, %F or %file, %l or %location, %L or %line, %M or %method, Log4j will take a snapshot of the stack, and walk the stack trace to find the location information.

This is an expensive operation: 1.3 - 5 times slower for synchronous loggers. Synchronous loggers wait as long as possible before they take this stack snapshot. If no location is required, the snapshot will never be taken.

However, asynchronous loggers need to make this decision before passing the log message to another thread; the location information will be lost after that point. The performance impact of taking a stack trace snapshot is even higher for asynchronous loggers: logging with location is 30-100 times slower than without location. For this reason, asynchronous loggers and asynchronous appenders do not include location information by default.

You can override the default behaviour in your logger or asynchronous appender configuration by specifying includeLocation="true".
```

## WebApp

WebApp环境下需要考虑到当前Servlet版本,3.0+/2.5有着不同的配置
在Web环境下需要考虑到引入log4j2-web.jar,从而更好的得到Contailer而不是JVM的启动和结束的事件
log4j2-web在Servlet3.0中使用标准的Log4jServletContainerInitializer来监听(Log4jServletContextListener)容器的生命周期,特别是异步环境下的刷盘机制防止日志丢失尤为重要


## Multi JVMs

FileAppender

使用多jvm操作文件必须使用locking配置,但是会显著影响性能

```
locking	boolean	
When set to true, I/O operations will occur only while the file lock is held allowing FileAppenders in multiple JVMs and potentially multiple hosts to write to the same file simultaneously. This will significantly impact performance so should be used carefully. Furthermore, on many systems the file lock is "advisory" meaning that other applications can perform operations on the file without acquiring a lock. The default value is false.

share特性(同RandomAccessFileAppender)
two web applications in a servlet container can have their own configuration and safely write to the same file if Log4j is in a ClassLoader that is common to both of them.
```

RandomAccessFileAppender不支持多JVMs操作同一个文件
