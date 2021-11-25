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

public class BroadcastTimeRunner {


    private static Map<String, Set<UUID>> MAP = new ConcurrentHashMap<>();
    private static Map<UUID, Client> CLIENTS = new ConcurrentHashMap<>();
    private static Message message = new Message("i am a message");

    public static void main(String args[]) throws Exception{
        initMap();
        //warmup
        directDispatch();
        directDispatch();
        System.out.println("directDispatch warmup end");
        long nano1 = System.currentTimeMillis();
        directDispatch();
        System.out.println(":" + (System.currentTimeMillis() - nano1));

        //warmup
        collectAndDispatch();
        collectAndDispatch();
        System.out.println("collectAndDispatch warmup end");
        long nano2 = System.currentTimeMillis();
        collectAndDispatch();
        System.out.println(":" + (System.currentTimeMillis() - nano2));
    }


    public static void collectAndDispatch() {
        Iterable<Client> clients = getClients("test");
        for (Client client : clients ) {
            client.send(message);
            writeAndFlush(client);
        }
    }

    public static void directDispatch() {
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

    private static Iterable<Client> getClients(String room) {
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
