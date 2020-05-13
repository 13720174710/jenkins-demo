package com.study.current.likou.algorithm.number;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test5 {

    /**
     * 给定一副牌，每张牌上都写着一个整数。
     *
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     *
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true
     *
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     *
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     *
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     *
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     *
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     * @param args
     */
    public static void main(String[] args) {
        int[] deck = {1,1,2,2,2,2};
        boolean b = hasGroupsSizeX(deck);
        System.out.println("hasGroupsSizeX:"+b);
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            if(map.containsKey(deck[i])){
                int num = map.get(deck[i]);
                map.put(deck[i],++num);
            }else{
                map.put(deck[i],1);
            }
        }
        Set<Integer> keys = map.keySet();
        for (Integer key :keys) {
            Integer num = map.get(key);
            if(num%2==1){
                return false;
            }
        }
        return true;
    }

}
