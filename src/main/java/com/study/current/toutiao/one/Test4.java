package com.study.current.toutiao.one;

public class Test4 {

    static volatile int j = 0;

    /**
     * 设计4个线程，其中两个线程每次对j加1，另外两个队j减少1
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new ThreadA()).start();
        new Thread(new ThreadA()).start();
        new Thread(new ThreadB()).start();
        new Thread(new ThreadB()).start();
    }

    static class ThreadA implements Runnable{
        @Override
        public void run() {
            synchronized (Test4.class){
                Test4.j++;
                System.out.println("ThreadA:"+j);
            }
        }

    }

    static class ThreadB implements Runnable{
        @Override
        public void run() {
            synchronized (Test4.class){
                Test4.j--;
                System.out.println("ThreadB:"+j);
            }
        }

    }

}
