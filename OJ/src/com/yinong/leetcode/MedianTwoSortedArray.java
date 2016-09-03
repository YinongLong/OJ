package com.yinong.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianTwoSortedArray {
	
	public static void main(String[] args){
		int[] nums1 = {8};
		int[] nums2 = {1,2,3,4,5,6,7};
		System.out.println(findMedianSortedArrays(nums1, nums2));
	}
	
	static double findMedianSortedArrays(int[] nums1, int[] nums2){
		int num_numbers = nums1.length + nums2.length;
		int median_posi = num_numbers/2+1, 
				count = 0, nums1_index = 0, nums2_index = 0;
		// 首先找到中值点的位置
		while(count < median_posi){
			if(nums1_index >= nums1.length){
				nums2_index++;
			}else if(nums2_index >= nums2.length){
				nums1_index++;
			}else{
				if(nums1[nums1_index] <= nums2[nums2_index]){
					nums1_index++;
				}else{
					nums2_index++;
				}
			}
			count += 1;
		}
		int[] indices = {nums1_index-1, nums1_index-2, nums2_index-1, nums2_index-2};
		int[] identity = {1, 1, 2, 2};
		PriorityQueue<Integer> numbers = new PriorityQueue<Integer>(
				new Comparator<Integer>() {

			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
	
		for(int i = 0; i < indices.length; i++){
			if(indices[i] >= 0){
				if(identity[i] == 1){
					numbers.add(nums1[indices[i]]);
				}else{
					numbers.add(nums2[indices[i]]);
				}
			}
		}
		if(num_numbers % 2 == 1)
			return numbers.poll();
		else
			return (numbers.poll() + numbers.poll()) * 1.0 / 2;
	}
}
