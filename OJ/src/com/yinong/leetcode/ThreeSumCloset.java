package com.yinong.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeSumCloset {
	
	public static void main(String[] args){
		int[] nums = new int[]{-1, 2, 1, -4};
		System.out.println(threeSumCloset(nums, 1));
	}
	
	static int threeSumCloset(int[] nums, int target){
		// 将数字放入到列表中，并且按照从小到达进行排序
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int num : nums){
			numbers.add(num);
		}
		Collections.sort(numbers);
		// 查找和最接近target的三元组
		int val_fir,
			val_sec,
			val_residual,
			val_closet = Integer.MAX_VALUE,
			val_result = target,
			start,
			temp,
			end;
		for(int i = 0; i < numbers.size()-2; i++){
			val_fir = numbers.get(i);
			for(int j = i+1; j < numbers.size()-1; j++){
				val_sec = numbers.get(j);
				val_residual = target - val_fir - val_sec;
				// 二分查找最接近val_residual的值
				start = j + 1;
				end = numbers.size() - 1;
				while(start <= end){
					temp = (start + end) / 2;
					if(numbers.get(temp) == val_residual){
						return target;
					}else if(numbers.get(temp) > val_residual){
						end = temp - 1;
					}else{
						start = temp + 1;
					}
				}
				// 说明此时没有精确等于target的三元组，则返回更新最接近的值
				if(end == numbers.size()-1){
					temp = numbers.get(end);
				}else if(start == j+1){
					temp = numbers.get(start);
				}else{
					temp = Math.abs(numbers.get(end) - val_residual) >
						   Math.abs(numbers.get(end+1) - val_residual) ?
						   numbers.get(end+1) : numbers.get(end);
				}
				if(Math.abs(temp - val_residual) < val_closet){
					val_closet = Math.abs(temp - val_residual);
					val_result = target - val_residual + temp;
				}
			}
		}
		return val_result;
	}
}
