package com.study.current.toutiao.one;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test3 {

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
                } catch (Exception e) {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static class Pritln{
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private boolean flag = true;

        public void pritlnNum(int i) {
            try {
                lock.lock();
                if(!flag){
                    condition.await();
                }
                System.out.print(1+i);
                Thread.sleep(500);
                flag = false;
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public void pritlnEnglish(int i){
            try{
                lock.lock();
                String[] strings = {"A","B","C","D","E","F","G","H","I","J"};
                if(flag){
                    condition.await();
                }
                System.out.print(strings[i]);
                Thread.sleep(500);
                flag = true;
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }

}

