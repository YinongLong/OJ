package com.yinong.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
	
	public static void main(String[] args){
		int[] nums = new int[]{7,-1,14,-12,-8,7,2,-15,8,8,
				-8,-14,-4,-5,7,9,11,-4,-15,-6,1,-14,4,3,10,
				-5,2,1,6,11,2,-2,-5,-7,-6,2,-15,11,-6,8,-4,
				2,1,-1,4,-6,-15,1,5,-15,10,14,9,-8,-6,4,-6,
				11,12,-15,7,-1,-9,9,-1,0,-4,-1,-12,-2,14,-9,
				7,0,-3,-4,1,-2,12,14,-10,0,5,14,-1,14,3,8,10,
				-8,8,-5,-2,6,-11,12,13,-7,-12,8,6,-13,14,-2,
				-5,-11,1,3,-6};
		List<List<Integer>> result = threeSumV2(nums);
		for(int i = 0; i < result.size(); i++){
			for(int j = 0; j < result.get(i).size(); j++){
				System.out.print(result.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	static List<List<Integer>> threeSumV2(int[] nums){
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		boolean zero_found = false;
		for(int temp : nums){
			if(temp == 0) zero_found = true;
			numbers.add(temp);
		}
		Collections.sort(numbers);
		
		int head = 0,
			tail = numbers.size()-1,
			head_temp = Integer.MAX_VALUE,
			end_temp = Integer.MIN_VALUE,
			sum_temp;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(nums.length < 3) return result;
		while(head <= numbers.size()-3){
			if(numbers.get(head) == head_temp){
				head += 1;
				continue;
			}
			tail = numbers.size()-1;
			head_temp = numbers.get(head);
			while(tail >= head + 2){
				if(numbers.get(tail) == end_temp){
					tail -= 1;
					continue;
				}
				end_temp = numbers.get(tail);
				sum_temp = head_temp + end_temp;
				boolean found = false;
				if(sum_temp == 0){
					if(zero_found) found = true;
				}else{
					//对有序数组进行二分查找
					int start = head + 1,
						end = tail - 1,
						mid;
					while(start <= end){
						mid = (start + end) / 2;
						if(numbers.get(mid) == sum_temp * -1){
							found = true;
							break;
						}else if(numbers.get(mid) > sum_temp * -1){
							end = mid - 1;
						}else{
							start = mid + 1;
						}
					}
				}
				if(found){
					ArrayList<Integer> result_temp = new ArrayList<Integer>();
					result_temp.add(head_temp);
					result_temp.add(end_temp);
					result_temp.add(-1 * sum_temp);
					result.add(result_temp);
				}
				tail -= 1;
			}
			head += 1;
		}
		return result;
	}
}
