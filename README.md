
1. 在String中使用KMP算法计算部分操作
2. JMH验证
3. 常规自定义线程池如何确定Queue的使用,依据是什么
4. 二叉堆实现有序队列
5. 解读ThreadLocal
6. Java中的排序场景,Collections.sort, TreeMap
7. 线程生命周期各状态在jstack中的解读
8. String.intern, Long, Integer等对象池在dump中的查看
9. finalize,phantomReference使用
10. 验证hash、一致性hash的分布



```
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfKMPLongText

# Run progress: 25.00% complete, ETA 00:00:13
# Fork: 1 of 1
# Warmup Iteration   1: 29.253 ops/ms
# Warmup Iteration   2: 29.390 ops/ms
Iteration   1: 31.507 ops/ms
Iteration   2: 32.576 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfKMPLongText":
  32.042 ops/ms


# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfString

# Run progress: 50.00% complete, ETA 00:00:08
# Fork: 1 of 1
# Warmup Iteration   1: 2963106.630 ops/ms
# Warmup Iteration   2: 3022976.143 ops/ms
Iteration   1: 2961620.876 ops/ms
Iteration   2: 3028052.756 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfString":
  2994836.816 ops/ms


# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfStringLongText

# Run progress: 75.00% complete, ETA 00:00:04
# Fork: 1 of 1
# Warmup Iteration   1: 1101564.035 ops/ms
# Warmup Iteration   2: 1069506.214 ops/ms
Iteration   1: 1514619.665 ops/ms
Iteration   2: 1506904.525 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfStringLongText":
  1510762.095 ops/ms


# Run complete. Total time: 00:00:17

Benchmark                              Mode  Cnt        Score   Error   Units
ToolsBenchmark.indexOfKMP             thrpt    2    23489.364          ops/ms
ToolsBenchmark.indexOfKMPLongText     thrpt    2       32.042          ops/ms
ToolsBenchmark.indexOfString          thrpt    2  2994836.816          ops/ms
ToolsBenchmark.indexOfStringLongText  thrpt    2  1510762.095          ops/ms
min-xufpdeMacBook-Pro% cd ..                                
min-xufpdeMacBook-Pro% cd target                            
min-xufpdeMacBook-Pro% java -jar j360-tools-1.0-SNAPSHOT.jar
# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfKMP

# Run progress: 0.00% complete, ETA 00:00:16
# Fork: 1 of 1
# Warmup Iteration   1: 21342.368 ops/ms
# Warmup Iteration   2: 19360.043 ops/ms
Iteration   1: 22852.490 ops/ms
Iteration   2: 21478.014 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfKMP":
  22165.252 ops/ms


# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfKMPLongText

# Run progress: 25.00% complete, ETA 00:00:14
# Fork: 1 of 1
# Warmup Iteration   1: 1209.584 ops/ms
# Warmup Iteration   2: 1310.762 ops/ms
Iteration   1: 1336.679 ops/ms
Iteration   2: 1350.806 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfKMPLongText":
  1343.742 ops/ms


# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfString

# Run progress: 50.00% complete, ETA 00:00:09
# Fork: 1 of 1
# Warmup Iteration   1: 2811042.069 ops/ms
# Warmup Iteration   2: 2810104.037 ops/ms
Iteration   1: 2977092.793 ops/ms
Iteration   2: 2938082.707 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfString":
  2957587.750 ops/ms


# JMH version: 1.19
# VM version: JDK 1.8.0_101, VM 25.101-b13
# VM invoker: /Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/jre/bin/java
# VM options: <none>
# Warmup: 2 iterations, 1 s each
# Measurement: 2 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: me.j360.tools.ToolsBenchmark.indexOfStringLongText

# Run progress: 75.00% complete, ETA 00:00:04
# Fork: 1 of 1
# Warmup Iteration   1: 1259915.584 ops/ms
# Warmup Iteration   2: 1267775.593 ops/ms
Iteration   1: 1244850.831 ops/ms
Iteration   2: 1097218.575 ops/ms


Result "me.j360.tools.ToolsBenchmark.indexOfStringLongText":
  1171034.703 ops/ms


# Run complete. Total time: 00:00:18

Benchmark                              Mode  Cnt        Score   Error   Units
ToolsBenchmark.indexOfKMP             thrpt    2    22165.252          ops/ms
ToolsBenchmark.indexOfKMPLongText     thrpt    2     1343.742          ops/ms
ToolsBenchmark.indexOfString          thrpt    2  2957587.750          ops/ms
ToolsBenchmark.indexOfStringLongText  thrpt    2  1171034.703          ops/ms
```


9. Finalize

- RunFinalize.java
- PhantomReference: 在垃圾回收时收到一个系统通知

复写
- vm 
-verbose:gc  -Xloggc:gc_%p.log -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC -XX:+PrintGCTimeStamps -XX:+PrintTenuringDistribution -XX:+PrintGCApplicationStoppedTime


只有在finalize中再次引用对象本身才会发生对象复活1次的行为,在执行System.gc时因为对象回收的不确定性,所以根据gc日子判断回收问题,并不能非常准确的描述出事件发生节点
结论如下:
对象复活的行为必须是对象本身在垃圾回收时,在finalize引用一次
同时再次执行垃圾回收时,该对象必被回收,这是回收时间点不确定
根据gc日志的节点只能作为参考,不能作为实验判断依据

- jdk7+ 终止方法中在try withresource使用场景,在finalize中实现非内存资源的释放, file/ inputstream/ socket等等
- Finalizer类和FinalizerThread最终实现垃圾回收的工作,并因为其优先级的原因,无法做到准确的预测时间
- 虚拟引用比其他引用可以更安全的重写finalize导致的问题,作为垃圾回收清理时的通知机制的特殊需求
参考:
https://www.jianshu.com/p/9d2788fffd5f
http://www.cnblogs.com/jqyp/archive/2010/11/27/1889414.html

我的回复:
应该是有些问题，博主愿意继续一起探讨下吗？
有几个问题：
1.finalize覆写问题，super.finalize
2.根据gc日志判断不够准确，根据jvm机制，垃圾回收存在不确定性，即便是执行了system.gc
3.根据书上所讲，在对象被回收并且在finalize中引用自身时，会逃过一次回收，但是不会有第二次，和你的描述有异议

```
Java HotSpot(TM) 64-Bit Server VM (25.101-b13) for bsd-amd64 JRE (1.8.0_101-b13), built on Jun 22 2016 02:42:15 by "java_re" with gcc 4.2.1 (Based on Apple Inc. build 5658) (LLVM build 2336.11.00)
Memory: 4k page, physical 8388608k(104744k free)

/proc/meminfo:

CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintGC -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -XX:+PrintTenuringDistribution -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 
{Heap before GC invocations=1 (full 0):
 PSYoungGen      total 38400K, used 2662K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 8% used [0x0000000795580000,0x0000000795819a28,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 102400K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746400010,0x000000074ba00000)
 Metaspace       used 2998K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.683-0800: 0.382: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 2662K->496K(38400K)] 105062K->102904K(228864K), 0.0023376 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=1 (full 0):
 PSYoungGen      total 38400K, used 496K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 9% used [0x0000000797600000,0x000000079767c010,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 102408K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746402010,0x000000074ba00000)
 Metaspace       used 2998K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=2 (full 1):
 PSYoungGen      total 38400K, used 496K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 9% used [0x0000000797600000,0x000000079767c010,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 102408K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746402010,0x000000074ba00000)
 Metaspace       used 2998K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.685-0800: 0.384: [Full GC (System.gc()) [PSYoungGen: 496K->0K(38400K)] [ParOldGen: 102408K->102800K(190464K)] 102904K->102800K(228864K), [Metaspace: 2998K->2998K(1056768K)], 0.0080959 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=2 (full 1):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 102800K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746464098,0x000000074ba00000)
 Metaspace       used 2998K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:43.694-0800: 0.393: Total time for which application threads were stopped: 0.0108421 seconds, Stopping threads took: 0.0000182 seconds
{Heap before GC invocations=3 (full 1):
 PSYoungGen      total 38400K, used 665K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 2% used [0x0000000795580000,0x0000000795626678,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 102800K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746464098,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.797-0800: 0.496: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 665K->64K(38400K)] 103465K->102864K(228864K), 0.0020611 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
Heap after GC invocations=3 (full 1):
 PSYoungGen      total 38400K, used 64K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 1% used [0x0000000797b00000,0x0000000797b10000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 102800K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746464098,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=4 (full 2):
 PSYoungGen      total 38400K, used 64K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 1% used [0x0000000797b00000,0x0000000797b10000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 102800K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 53% used [0x0000000740000000,0x0000000746464098,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.799-0800: 0.498: [Full GC (System.gc()) [PSYoungGen: 64K->0K(38400K)] [ParOldGen: 102800K->380K(190464K)] 102864K->380K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0049124 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap after GC invocations=4 (full 2):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 380K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005f110,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:43.804-0800: 0.503: Total time for which application threads were stopped: 0.0073095 seconds, Stopping threads took: 0.0000133 seconds
{Heap before GC invocations=5 (full 2):
 PSYoungGen      total 38400K, used 665K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 2% used [0x0000000795580000,0x0000000795626678,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 380K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005f110,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.910-0800: 0.609: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 665K->0K(38400K)] 1045K->380K(228864K), 0.0030703 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=5 (full 2):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 380K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005f110,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=6 (full 3):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 380K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005f110,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:43.914-0800: 0.613: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 380K->379K(190464K)] 380K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0095584 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=6 (full 3):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:43.923-0800: 0.622: Total time for which application threads were stopped: 0.0133921 seconds, Stopping threads took: 0.0002595 seconds
{Heap before GC invocations=7 (full 3):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.027-0800: 0.726: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0003714 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=7 (full 3):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=8 (full 4):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.028-0800: 0.727: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0042423 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap after GC invocations=8 (full 4):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.032-0800: 0.731: Total time for which application threads were stopped: 0.0048995 seconds, Stopping threads took: 0.0000187 seconds
{Heap before GC invocations=9 (full 4):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.135-0800: 0.834: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0005511 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=9 (full 4):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=10 (full 5):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.136-0800: 0.835: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0085671 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=10 (full 5):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.145-0800: 0.844: Total time for which application threads were stopped: 0.0098196 seconds, Stopping threads took: 0.0000446 seconds
{Heap before GC invocations=11 (full 5):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.249-0800: 0.948: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0004497 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap after GC invocations=11 (full 5):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=12 (full 6):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.250-0800: 0.949: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0055632 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
Heap after GC invocations=12 (full 6):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.256-0800: 0.955: Total time for which application threads were stopped: 0.0064833 seconds, Stopping threads took: 0.0000177 seconds
{Heap before GC invocations=13 (full 6):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.358-0800: 1.057: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0005333 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=13 (full 6):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=14 (full 7):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.359-0800: 1.058: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0044933 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=14 (full 7):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.363-0800: 1.062: Total time for which application threads were stopped: 0.0053674 seconds, Stopping threads took: 0.0000274 seconds
{Heap before GC invocations=15 (full 7):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.467-0800: 1.166: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0005387 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=15 (full 7):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=16 (full 8):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.467-0800: 1.166: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0050005 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=16 (full 8):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.472-0800: 1.171: Total time for which application threads were stopped: 0.0059694 seconds, Stopping threads took: 0.0000191 seconds
{Heap before GC invocations=17 (full 8):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.575-0800: 1.274: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0179505 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
Heap after GC invocations=17 (full 8):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=18 (full 9):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.593-0800: 1.292: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0090135 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap after GC invocations=18 (full 9):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.602-0800: 1.301: Total time for which application threads were stopped: 0.0273376 seconds, Stopping threads took: 0.0000162 seconds
{Heap before GC invocations=19 (full 9):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
  to   space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.706-0800: 1.405: [GC (System.gc()) 
Desired survivor size 5242880 bytes, new threshold 7 (max 15)
[PSYoungGen: 0K->0K(38400K)] 379K->379K(228864K), 0.0005922 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap after GC invocations=19 (full 9):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
{Heap before GC invocations=20 (full 10):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
2018-07-05T13:28:44.707-0800: 1.406: [Full GC (System.gc()) [PSYoungGen: 0K->0K(38400K)] [ParOldGen: 379K->379K(190464K)] 379K->379K(228864K), [Metaspace: 2999K->2999K(1056768K)], 0.0062198 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
Heap after GC invocations=20 (full 10):
 PSYoungGen      total 38400K, used 0K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 0% used [0x0000000795580000,0x0000000795580000,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 2999K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 331K, capacity 388K, committed 512K, reserved 1048576K
}
2018-07-05T13:28:44.714-0800: 1.412: Total time for which application threads were stopped: 0.0071747 seconds, Stopping threads took: 0.0000322 seconds
Heap
 PSYoungGen      total 38400K, used 665K [0x0000000795580000, 0x0000000798000000, 0x00000007c0000000)
  eden space 33280K, 2% used [0x0000000795580000,0x00000007956267d8,0x0000000797600000)
  from space 5120K, 0% used [0x0000000797b00000,0x0000000797b00000,0x0000000798000000)
  to   space 5120K, 0% used [0x0000000797600000,0x0000000797600000,0x0000000797b00000)
 ParOldGen       total 190464K, used 379K [0x0000000740000000, 0x000000074ba00000, 0x0000000795580000)
  object space 190464K, 0% used [0x0000000740000000,0x000000074005ef78,0x000000074ba00000)
 Metaspace       used 3006K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 332K, capacity 388K, committed 512K, reserved 1048576K

```


jstack

- 1.synchronize锁下拿到锁的线程占据了cpu控制权,其他等待锁的block阻塞
- 2.在1情况下的线程如果执行wait或者wait(time),则有条件出让锁和cpu资源,竞争2线程拿到锁,,1线程wait状态,等待2线程notify
- 3.1线程情况下,如果线程sleep仅仅是线程sleep,进入wait状态,但是不出让cpu资源,等待sleep结束自我唤醒
- 4.wait、wait_time发生的情况有2中,1自己拿到锁,自己睡眠sleep,2自己拿到锁,自己wait
- 5.其他线程执行完并notify时,wait线程收到通知进入竞争状态,生产者消费者场景是该问题排查的常用场景

````
min-xufpdeMacBook-Pro% jstack 73952
2018-07-05 14:41:24
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.101-b13 mixed mode):

"Attach Listener" #14 daemon prio=9 os_prio=31 tid=0x00007f838a061800 nid=0xd07 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" #13 prio=5 os_prio=31 tid=0x00007f83890a0800 nid=0x2603 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"pool-1-thread-3" #12 prio=5 os_prio=31 tid=0x00007f83890a0000 nid=0x3b03 waiting on condition [0x0000700003fb9000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at me.j360.tools.ref.JstackCase$Task3.run(JstackCase.java:63)
        - locked <0x00000007956f70f0> (a java.lang.Object)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"pool-1-thread-2" #11 prio=5 os_prio=31 tid=0x00007f838a060800 nid=0x3e03 waiting for monitor entry [0x0000700003eb6000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000007956f70f0> (a java.lang.Object)
        at me.j360.tools.ref.JstackCase$Task.run(JstackCase.java:39)
        - locked <0x00000007956f70f0> (a java.lang.Object)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"pool-1-thread-1" #10 prio=5 os_prio=31 tid=0x00007f838a02b000 nid=0x3a03 waiting for monitor entry [0x0000700003db3000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000007956f70f0> (a java.lang.Object)
        at me.j360.tools.ref.JstackCase$Task.run(JstackCase.java:39)
        - locked <0x00000007956f70f0> (a java.lang.Object)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"Monitor Ctrl-Break" #9 daemon prio=5 os_prio=31 tid=0x00007f8389897000 nid=0x3903 runnable [0x0000700003cb0000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(Native Method)
        at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
        at java.net.ServerSocket.implAccept(ServerSocket.java:545)
        at java.net.ServerSocket.accept(ServerSocket.java:513)
        at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:90)
        at java.lang.Thread.run(Thread.java:745)

"Service Thread" #8 daemon prio=9 os_prio=31 tid=0x00007f838a844000 nid=0x3803 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007f838a058000 nid=0x3703 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007f838a057000 nid=0x3503 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007f838a055800 nid=0x4603 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007f838a055000 nid=0x3303 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007f838901a000 nid=0x4f03 in Object.wait() [0x000070000359b000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x0000000795588ee0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
        - locked <0x0000000795588ee0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

"Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007f8389017800 nid=0x2d03 in Object.wait() [0x0000700003498000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x0000000795586b50> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x0000000795586b50> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"VM Thread" os_prio=31 tid=0x00007f838a040800 nid=0x5203 runnable 

"GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007f838981c000 nid=0x1d07 runnable 

"GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007f838a000800 nid=0x1e03 runnable 

"GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007f838a001000 nid=0x2b03 runnable 

"GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007f838a002000 nid=0x5303 runnable 

"VM Periodic Task Thread" os_prio=31 tid=0x00007f8389831000 nid=0x4203 waiting on condition 

JNI global references: 21

min-xufpdeMacBook-Pro% jstack 73952
2018-07-05 14:41:39
Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.101-b13 mixed mode):

"Attach Listener" #14 daemon prio=9 os_prio=31 tid=0x00007f838a061800 nid=0xd07 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" #13 prio=5 os_prio=31 tid=0x00007f83890a0800 nid=0x2603 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"pool-1-thread-3" #12 prio=5 os_prio=31 tid=0x00007f83890a0000 nid=0x3b03 waiting on condition [0x0000700003fb9000]
   java.lang.Thread.State: WAITING (parking)
        at sun.misc.Unsafe.park(Native Method)
        - parking to wait for  <0x00000007956f6cf8> (a java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject)
        at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
        at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
        at java.util.concurrent.LinkedBlockingQueue.take(LinkedBlockingQueue.java:442)
        at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1067)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1127)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"pool-1-thread-2" #11 prio=5 os_prio=31 tid=0x00007f838a060800 nid=0x3e03 in Object.wait() [0x0000700003eb6000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000007956f70f0> (a java.lang.Object)
        at me.j360.tools.ref.JstackCase$Task.run(JstackCase.java:39)
        - locked <0x00000007956f70f0> (a java.lang.Object)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"pool-1-thread-1" #10 prio=5 os_prio=31 tid=0x00007f838a02b000 nid=0x3a03 in Object.wait() [0x0000700003db3000]
   java.lang.Thread.State: TIMED_WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000007956f70f0> (a java.lang.Object)
        at me.j360.tools.ref.JstackCase$Task.run(JstackCase.java:39)
        - locked <0x00000007956f70f0> (a java.lang.Object)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)

"Monitor Ctrl-Break" #9 daemon prio=5 os_prio=31 tid=0x00007f8389897000 nid=0x3903 runnable [0x0000700003cb0000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(Native Method)
        at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
        at java.net.ServerSocket.implAccept(ServerSocket.java:545)
        at java.net.ServerSocket.accept(ServerSocket.java:513)
        at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:90)
        at java.lang.Thread.run(Thread.java:745)

"Service Thread" #8 daemon prio=9 os_prio=31 tid=0x00007f838a844000 nid=0x3803 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007f838a058000 nid=0x3703 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007f838a057000 nid=0x3503 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007f838a055800 nid=0x4603 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007f838a055000 nid=0x3303 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007f838901a000 nid=0x4f03 in Object.wait() [0x000070000359b000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x0000000795588ee0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
        - locked <0x0000000795588ee0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

"Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007f8389017800 nid=0x2d03 in Object.wait() [0x0000700003498000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x0000000795586b50> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x0000000795586b50> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"VM Thread" os_prio=31 tid=0x00007f838a040800 nid=0x5203 runnable 

"GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007f838981c000 nid=0x1d07 runnable 

"GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007f838a000800 nid=0x1e03 runnable 

"GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007f838a001000 nid=0x2b03 runnable 

"GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007f838a002000 nid=0x5303 runnable 

"VM Periodic Task Thread" os_prio=31 tid=0x00007f8389831000 nid=0x4203 waiting on condition 

JNI global references: 21
````