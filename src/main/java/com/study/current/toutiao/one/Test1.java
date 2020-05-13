package com.study.current.toutiao.one;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test1 {

    /**
     * 用两个线程，一个输出数字，一个输出字母，交替输出1A2B3C...
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        final Pritln pritln = new Pritln();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        pritln.pritlnNum(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        pritln.pritlnEnglish(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Pritln{

        BlockingQueue queue1 = new ArrayBlockingQueue(1);

        BlockingQueue queue2 = new ArrayBlockingQueue(1);

        {
            try {
                queue2.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void pritlnNum(int i) throws InterruptedException {
            queue1.put(1);
            System.out.print(1+i);
            Thread.sleep(500);
            queue2.take();
        }

        public void pritlnEnglish(int i) throws InterruptedException {
            queue2.put(1);
            String[] strings = {"A","B","C","D","E","F","G","H","I","J"};
            System.out.print(strings[i]);
            Thread.sleep(500);
            queue1.take();
        }

    }

}

