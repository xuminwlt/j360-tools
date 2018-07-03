

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