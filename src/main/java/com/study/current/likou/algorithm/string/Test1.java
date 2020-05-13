package com.study.current.likou.algorithm.string;

public class Test1 {

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * 输入: 123
     * 输出: 321
     *
     * 输入: -123
     * 输出: -321
     *
     * 输入: 120
     * 输出: 21
     * @param args
     */
    public static void main(String[] args) {
        int reverse = reverse(123);
        System.out.println("reverse:"+reverse);
    }

    public static int reverse(int x) {
        int num = 0;
        while (x!=0){
            int y = x%10;
            x /=10;
            num = num*10+y;
            System.out.println("y:"+y+"  x:"+x+"  num:"+num);
            System.out.println("----------------");
        }
        return num;
    }

}
