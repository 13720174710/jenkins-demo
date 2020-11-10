package com.study.current.likou.algorithm.number;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Test7 {

    /**
     * 假设有任意多张面额为 2元 3元 7元 的货币  先要用它们凑出100元  求总共有多少总可能性
     * @param args
     */
    public static void main(String[] args) {
//        method1();
        method2();
    }

    private static void method2() {
        int count = 0;
        for (int i = 0; i < (100 / 7); i++) {
            for (int j = 0; j < (100 / 3); j++) {
                if ((100 - i * 7 - j * 3) % 2 == 0) {
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }

    private static void method1() {
        int count = 0;
        //  2x + 3y + 7z = 100
        for (int i = 0; i < (100/7); i++) {
            for (int j = 0; j < (100/3); j++) {
                for (int k = 0; k < (100/2); k++) {
                    if(7*i+3*j+2*k==100){
                        count +=1;
                        System.out.println("2元"+k +"张"+","+"3元"+j +"张"+","+"7元"+i +"张");
                    }
                }
            }
        }
        System.out.println(count);
    }

}
