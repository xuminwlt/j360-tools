package me.j360.tools.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author: min_xu
 * @date: 2018/7/4 下午3:09
 * 说明：
 */
public class BinaryHeapTest {

    private static BinaryHeap<Long> binaryHeap;
    private static Long start = System.currentTimeMillis();
    static {
        binaryHeap = new BinaryHeap<Long>(1000, Comparator.naturalOrder());
        binaryHeap.add(start);
    }

    @Test
    public void add() {
        binaryHeap.add(System.currentTimeMillis());
    }


    @Test
    public void poll() {
        while (binaryHeap.size() < 100) {
            binaryHeap.add(System.currentTimeMillis());
        }
        Long result = binaryHeap.poll();
        assertThat(result).isEqualTo(start);
    }
}
