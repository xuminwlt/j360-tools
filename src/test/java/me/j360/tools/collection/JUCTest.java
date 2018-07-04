package me.j360.tools.collection;

import org.junit.Test;


/**
 * @author: min_xu
 * @date: 2018/7/4 下午5:12
 * 说明：
 */
public class JUCTest {

    @Test
    public void arrayBlockQueue() {
        ArrayBlockingQueue<Long> blockingQueue = new ArrayBlockingQueue<Long>(10, false);
        blockingQueue.offer(1L);
        blockingQueue.offer(2L);
        blockingQueue.offer(3L);
        blockingQueue.offer(4L);

        blockingQueue.poll();
        blockingQueue.poll();
        System.out.println(blockingQueue.count + " " + blockingQueue.size());

        blockingQueue.offer(5L);
        blockingQueue.offer(6L);
        blockingQueue.offer(7L);
        blockingQueue.offer(8L);

        System.out.println(blockingQueue.count + " " + blockingQueue.size());

    }

    @Test
    public void linkedBlockQueue() {

    }
}
