package com.study.current.likou.algorithm.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Test2 {

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat dog";
        wordPattern(pattern,str);
    }

    public static boolean wordPattern(String pattern, String str) {
        if (pattern == null && str == null) {
            return true;
        }
        if (pattern == null || str == null) {
            return false;
        }
        String[] word = str.split(" ");
        if (pattern.length() != word.length) {
            return false;
        }
        Map<Object, Integer> mem = new HashMap<>();
        for (int i = 0; i < word.length; i++) {
            Integer pi = mem.put(pattern.charAt(i), i);
            Integer si = mem.put(word[i], i);
            if (!Objects.equals(pi, si)) {
                return false;
            }
        }
        return true;
    }

}
