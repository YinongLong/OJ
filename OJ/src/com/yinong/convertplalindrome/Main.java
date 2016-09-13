package com.yinong.convertplalindrome;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n;
		int[] nums;
		while(scan.hasNext()){
			n = scan.nextInt();
			nums = new int[n];
			for(int i = 0; i < n; i++){
				nums[i] = scan.nextInt();
			}
			System.out.println(numsConvert(nums));
		}
		scan.close();
	}
	
	static int numsConvert(int[] nums){
		int left = 0, right = nums.length-1, count = 0;
		while(left < right){
			if(nums[left] != nums[right]){
				boolean left_small = nums[left] < nums[right] ? true : false;
				if(left_small){
					nums[left+1] = nums[left] + nums[left+1];
					left += 1;
					count += 1;
				}else{
					nums[right-1] = nums[right] + nums[right-1];
					right -= 1;
					count += 1;
				}
			}else{
				left += 1;
				right -= 1;
			}
		}
		return count;
	}
}
