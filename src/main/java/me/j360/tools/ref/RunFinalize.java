package me.j360.tools.ref;

import java.io.IOException;

/**
 * @author: min_xu
 * @date: 2018/7/5 下午12:45
 * 说明：
 */
public class RunFinalize {

    private static Block block = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        block = new Block();
        block = null;

        for( int i= 0; i < 10; i++){
            System. gc();
            Thread. sleep( 100);
        }
    }

    static class Block {
        byte[] _100M = new byte[100*1024*1024];

        @Override
        protected void finalize() throws Throwable {
            try {
                System.out.println("invoke finalize");
            } finally {
                super.finalize();
            }
        }
    }
}
