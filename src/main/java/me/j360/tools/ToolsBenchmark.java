package me.j360.tools;

import me.j360.tools.text.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.util.FileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * @author: min_xu
 * @date: 2018/7/3 下午6:11
 * 说明：
 */
public class ToolsBenchmark {

    public static void main(String args[]) throws Exception{
        Options opt = new OptionsBuilder()
                .include(ToolsBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(2) //预热次数
                .measurementIterations(2) //真正执行次数
                .build();

        new Runner(opt).run();
    }

    static {
        ClassLoader cl = ToolsBenchmark.class.getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("text/text.txt");
        Collection<String> collection = null;
        try {
            collection = FileUtils.readAllLines(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        longText = collection.toString();
    }

    private static String longText;
    private static String longParterText = "345678909876543";

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void indexOfKMP() {
        StringUtils.indexOf("StringUtilsTest", "sTe");
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void indexOfString() {
        "StringUtilsTest".indexOf("sTe");
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void indexOfKMPLongText() {
        StringUtils.indexOf(longText, longParterText);
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.Throughput)
    @Benchmark
    public void indexOfStringLongText() {
        longText.indexOf(longParterText);
    }
}
