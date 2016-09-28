package com.yinong.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
	
	public static void main(String[] args){
		int[] nums = new int[]{1,0,-1,0,-2,2};
		List<List<Integer>> re = fourSum(nums, 0);
		for(List<Integer> temp : re){
			System.out.println(temp.get(0) + " " + temp.get(1) + " "
		+ temp.get(2) + " " + temp.get(3));
		}
	}
	
	static List<List<Integer>> fourSum(int[] nums, int target){
		sortArray(nums);
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> temp;
		int fir = 0, sec = 0, thi = 0, rest, index;
		boolean isstart = true;
		for(int i = 0; i < nums.length-3; i++){
			if(!isstart && nums[i] == fir && i != 0) continue;
			fir = nums[i];
			for(int j = i+1; j < nums.length-2; j++){
				if(!isstart && nums[j] == sec && j != i+1) continue;
				sec = nums[j];
				for(int k = j+1; k < nums.length-1; k++){
					if(!isstart && nums[k] == thi && k != j+1) continue;
					if(isstart) isstart = false;
					thi = nums[k];
					rest = target - (fir + sec + thi);
					index = Arrays.binarySearch(nums, k+1, nums.length, rest);
					if(index >= k+1){
						temp = new ArrayList<>();
						temp.add(fir);
						temp.add(sec);
						temp.add(thi);
						temp.add(rest);
						result.add(temp);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 对数组进行归并排序
	 * @param nums
	 */
	static void sortArray(int[] nums){
		 mSort(nums, new int[nums.length], 0, nums.length-1);
	}
	
	static void mSort(int[] nums, int[] tempArray, int sPos, int ePos){
		int center;
		if(sPos < ePos){
			center = (sPos + ePos) / 2;
			mSort(nums, tempArray, sPos, center);
			mSort(nums, tempArray, center+1, ePos);
			merge(nums, tempArray, sPos, center+1, ePos);
		}
	}
	
	static void merge(int[] nums, int[] tempArray, int lStart, int rStart, int rEnd){
		int lEnd = rStart - 1;
		int tempLeft = lStart;
		int numsElements = rEnd - lStart + 1;
		while(lStart <= lEnd && rStart  <= rEnd){
			if(nums[lStart] <= nums[rStart]){
				tempArray[tempLeft++] = nums[lStart++];
			}else{
				tempArray[tempLeft++] = nums[rStart++];
			}
		}
		
		while(lStart <= lEnd){
			tempArray[tempLeft++] = nums[lStart++];
		}
		while(rStart <= rEnd){
			tempArray[tempLeft++] = nums[rStart++];
		}
		for(int i = 0; i < numsElements; i++, rEnd--){
			nums[rEnd] = tempArray[rEnd];
		}
	}
}
