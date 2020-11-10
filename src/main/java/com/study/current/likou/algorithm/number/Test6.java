package com.study.current.likou.algorithm.number;

import java.util.Arrays;

public class Test6 {

    /**
     * 数组中的值调换输出
     * @param args
     */
    public static void main(String[] args) {
        int [] arr2 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr2[i] = i+1;
        }
        int [] arr1 = {1,2,3,4,5,6};
        method1(arr2);
        method2(arr2);
    }

    private static void method2(int[] arr1) {
        long time1 = System.currentTimeMillis();
        int temp = 0;
        for (int i = 0; i < arr1.length/2; i++) {
            temp = arr1[i];
            arr1[i] = arr1[arr1.length-i-1];
            arr1[arr1.length-i-1] = temp;
        }
        long time2 = System.currentTimeMillis();
        System.out.println("method2 spend time:"+(time2-time1));
//        System.out.println("method1:"+Arrays.toString(arr1));
    }

    private static void method1(int[] arr1) {
        long time1 = System.currentTimeMillis();
        int [] arr2 = new int[100000];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        for (int i = 0; i < arr1.length; i++) {
            arr2[arr1.length-i-1] = arr1[i];
        }
        long time2 = System.currentTimeMillis();
        System.out.println("method1 spend time:"+(time2-time1));
//        System.out.println("method1:"+Arrays.toString(arr2));
    }

}
