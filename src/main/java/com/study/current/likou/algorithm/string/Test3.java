package com.study.current.likou.algorithm.string;

public class Test3 {

    public static void main(String[] args) {
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
        order.compareTo("");
        boolean alienSorted = isAlienSorted(words, order);
        System.out.println("alienSorted:"+alienSorted);
    }

    public static boolean isAlienSorted(String[] words, String order) {


        return true;
    }

}
