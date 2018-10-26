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

## 实时还是吞吐量


## Location信息


## WebApp

