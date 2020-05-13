package com.study.current.likou.algorithm.number;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = {11, 15, 2, 7};
        int target = 9;
        int[] ints = twoSum(nums, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])){
                return new int[] {i,i-1};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
    }

}
