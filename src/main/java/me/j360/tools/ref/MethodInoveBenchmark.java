package me.j360.tools.ref;

import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author: min_xu
 * @date: 2019/1/29 6:18 PM
 * 说明：
 */
public class MethodInoveBenchmark {

    public static void main(String args[]) throws Exception{
        Options opt = new OptionsBuilder()
                .include(MethodInoveBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(10) //预热次数
                .measurementIterations(10) //真正执行次数
                .build();

        new Runner(opt).run();
    }


    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @SneakyThrows
    public void jdk() {
        PreparedStatement preparedStatement = new PreparedStatement();
        for (int i = 0 ;i <= 10000 ; i++) {
            JdbcMethodInvocation actual = new JdbcMethodInvocation(PreparedStatement.class.getMethod("setObject", int.class, Object.class), new Object[] {1, 100});
            actual.invoke(preparedStatement);
        }
    }


    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @SneakyThrows
    public void cgilib() {
        PreparedStatement preparedStatement = new PreparedStatement();
        for (int i = 0 ;i <= 10000 ; i++) {
            JdbcFastMethodInvocation actual = new JdbcFastMethodInvocation(JdbcFastMethodInvocation.build(PreparedStatement.class, PreparedStatement.class.getMethod("setObject", int.class, Object.class)), new Object[] {1, 100});
            actual.invoke(preparedStatement);
        }
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @SneakyThrows
    public void cgilib2() {
        PreparedStatement preparedStatement = new PreparedStatement();
        for (int i = 0 ;i <= 10000 ; i++) {
            JdbcFastMethodInvocation actual = new JdbcFastMethodInvocation(JdbcFastMethodInvocation.get(PreparedStatement.class.getMethod("setObject", int.class, Object.class)), new Object[] {1, 100});
            actual.invoke(preparedStatement);
        }
    }
}
