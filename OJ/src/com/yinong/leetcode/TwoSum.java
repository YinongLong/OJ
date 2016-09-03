package com.yinong.leetcode;

import java.util.Hashtable;

public class TwoSum {
	
	public static void main(String[] args){
		
		int[] nums = {-1, -2, -3, -4, -5};
		int target = -8;
		int[] result = twosum(nums, target);
		System.out.println(result[0] + " " + result[1]);
	}
	
	static int[] twosum(int[] nums, int target){
		
		Hashtable<Integer, Integer> info = new Hashtable<Integer, Integer>();
		int count = 0;
		for(int num : nums){
			info.put(num, count++);
		}
		
		int temp_num, residual, left=0, right=0;
		for(int i=0; i < nums.length; i++){
			temp_num = nums[i];
			residual = target - temp_num;
			if(info.containsKey(residual)){
				left = i;
				right = info.get(residual);
				if(right == left) continue;
				break;
			}
		}
		return new int[]{left, right};
	}
}
