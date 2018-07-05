package me.j360.tools.ref;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: min_xu
 * @date: 2018/7/5 下午2:02
 * 说明：
 */
public class JstackCase {

    public static Executor executor = Executors.newFixedThreadPool(3);
    public static Object lock = new Object();

    public static void main(String[] args) {
        Task task1 = new Task();
        Task task2 = new Task();
        Task3 task3 = new Task3();
        executor.execute(task1);
        executor.execute(task2);
        executor.execute(task3);
    }

    static class Task implements Runnable {
        private boolean stop = false;

        @Override
        public void run() {
            synchronized (lock) {
                int i = 0;
                while (!stop) {
                    i++;
                    if (i > 10) {
                        stop = true;
                    }
                    System.out.println(i + "" +Thread.currentThread().getName());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Task2 implements Runnable {
        private boolean stop = false;

        @Override
        public void run() {
            synchronized (lock) {
                int i = 0;
                while (!stop) {
                    if (Thread.currentThread().isInterrupted()) {

                    }
                    i++;
                    if (i > 10) {
                        stop = true;
                    }
                    System.out.println(i + "" +Thread.currentThread().getName());

                    lock.notify();
                }
            }
        }
    }

    static class Task3 implements Runnable {
        private boolean stop = false;

        @Override
        public void run() {
            synchronized (lock) {
                int i = 0;
                while (!stop) {
                    i++;
                    if (i > 4) {
                        stop = true;
                    }
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
