package me.j360.tools.ref;

import java.io.IOException;

/**
 * @author: min_xu
 * @date: 2018/7/5 下午12:45
 * 说明：
 */
public class RunFinalizeEscape {

    private static RunFinalizeEscape block = null;

    private void isAlive() {
        System.out.println("alive");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        block = new RunFinalizeEscape();
        block = null;

        System.gc();
        Thread.sleep(500);

        //
        if (block != null) {
            block.isAlive();
        } else {
            System.out.println("dead");
        }

        System.out.println("gc again");
        block = null;
        System.gc();
        Thread.sleep(500);
        if (block != null) {
            block.isAlive();
        } else {
            System.out.println("dead");
        }

    }

    @Override
    protected void finalize() throws Throwable {
        try {
            System.out.println("invoke finalize");
            block = this; //toggle here //block = this;

        } finally {
            super.finalize();
        }
    }
}
