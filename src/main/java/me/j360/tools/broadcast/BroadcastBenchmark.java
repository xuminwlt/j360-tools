package me.j360.tools.broadcast;

import lombok.SneakyThrows;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Threads(value = 4)
public class BroadcastBenchmark {


    private static Map<String, Set<UUID>> MAP = new ConcurrentHashMap<>();
    private static Map<UUID, Client> CLIENTS = new ConcurrentHashMap<>();
    private static Message message = new Message("i am a message");

    public static void main(String args[]) throws Exception{
        Options opt = new OptionsBuilder()
                .include(BroadcastBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(10) //预热次数
                .measurementIterations(10) //真正执行次数
                .build();

        new Runner(opt).run();
    }

    // 其实也可以放在static中。
    // 不过指明 @Setup 不会计算在总时间里
    @Setup(Level.Trial)
    public void init() {
        initMap();
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @SneakyThrows
    public void collectAndDispatch() {
        Iterable<Client> clients = getClients("test");
        for (Client client : clients ) {
            client.send(message);
            writeAndFlush(client);
        }
    }

    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    @SneakyThrows
    public void directDispatch() {
        Set<UUID> sessionIds = MAP.get("test");
        for (UUID sessionId : sessionIds) {
            Client client = CLIENTS.get(sessionId);
            if(client != null) {
                client.send(message);
                writeAndFlush(client);
            }
        }
    }

    private static void initMap() {
        for (int i = 0; i < 3000; i++) {
            UUID sessionId = UUID.randomUUID();
            MAP.computeIfAbsent("test", s -> new HashSet<UUID>()).add(sessionId);
            CLIENTS.put(sessionId, Client.builder().sessionId(sessionId).queue(new LinkedBlockingQueue<>()).build());
        }
    }

    private Iterable<Client> getClients(String room) {
        Set<UUID> sessionIds = MAP.get(room);

        List<Client> result = new ArrayList<Client>();
        for (UUID sessionId : sessionIds) {
            Client client = CLIENTS.get(sessionId);
            if(client != null) {
                result.add(client);
            }
        }
        return result;
    }

    @SneakyThrows
    private static void writeAndFlush(Client client)  {
        while (true) {
            Queue<Message> queue = client.getQueue();
            Message packet = queue.poll();
            if (packet == null) {
                return;
            }
            //TimeUnit.MILLISECONDS.sleep(1);
        }
    }
}
