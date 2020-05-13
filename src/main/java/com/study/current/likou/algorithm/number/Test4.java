package com.study.current.likou.algorithm.number;

import java.util.*;

public class Test4 {

    public static void main(String[] args) {
        int personCount = 5; //参与人数
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int lunCount = 3; //3轮传递
        int i = numWays(personCount, relation, lunCount);
        System.out.println("numWays:"+i);

    }

    private static int count;

    public static int numWays(int personCount, int[][] relation, int lunCount) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int[] temp : relation){
            if(!map.containsKey(temp[0]))
                map.put(temp[0], new ArrayList<>());
            map.get(temp[0]).add(temp[1]);
        }
        count = 0;
        backTracking(map, lunCount, personCount, 0, 0);
        return count;
    }

    private static void backTracking(Map<Integer, List<Integer>> map, int lunCount, int personCount, int cur, int curPerson){
        if(cur == lunCount){
            if(curPerson == personCount - 1)
                count++;
            return;
        }
        if(!map.containsKey(curPerson))
            return;
        for(int i : map.get(curPerson)){
            backTracking(map, lunCount, personCount, cur + 1, i);
        }
    }


}
