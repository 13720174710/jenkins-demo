package com.study.current.toutiao.one;

public class Test2 {

    /**
     * 用两个线程，一个输出数字，一个输出字母，交替输出1A2B3C...
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
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

        private boolean flag = false;

        public synchronized void pritlnNum(int i) throws InterruptedException {
            while (flag){
                this.wait();
            }
            System.out.print(1+i);
            Thread.sleep(500);
            flag = true;
            this.notify();
        }

        public synchronized void pritlnEnglish(int i) throws InterruptedException {
            String[] strings = {"A","B","C","D","E","F","G","H","I","J"};
            while (!flag){
                this.wait();
            }
            System.out.print(strings[i]);
            Thread.sleep(500);
            flag = false;
            this.notify();
        }

    }

}

