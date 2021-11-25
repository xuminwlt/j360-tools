package me.j360.tools.broadcast;

import lombok.Builder;
import lombok.Data;

import java.util.Queue;
import java.util.UUID;

@Builder
@Data
public class Client {

    private UUID sessionId;

    private Queue<Message> queue;

    public void send(Message message) {
        queue.add(message);
    }
}
