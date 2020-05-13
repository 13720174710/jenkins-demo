package com.study.current.likou.algorithm.sort;

import java.util.HashSet;

public class Test1 {

    public static void main(String[] args) {
        int [] scores = {1,2,3};
        int score = expectNumber(scores);
        System.out.println(score);
    }

    public static int expectNumber(int[] scores) {
        HashSet<Integer> odds = new HashSet<Integer>();
        for (int i = 0; i < scores.length; i++) {
            odds.add(scores[i]);
        }
        return odds.size();
    }


}
