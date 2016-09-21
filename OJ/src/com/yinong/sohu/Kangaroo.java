package com.yinong.sohu;

import java.util.Scanner;

public class Kangaroo {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int N;
		int[] nums;
		while(scan.hasNext()){
			N = scan.nextInt();
			nums = new int[N];
			for(int i = 0; i < N; i++){
				nums[i] = scan.nextInt();
			}
			System.out.println(getMinimumOfStep(nums));
		}
		scan.close();
	}
	
	static int getMinimumOfStep(int[] nums){
		int[] min_steps = new int[nums.length];
		for(int i = 0; i < min_steps.length; i++){
			min_steps[i] = Integer.MAX_VALUE;
		}
		int strength,
			nextPosi;
		for(int i = nums.length-1; i >= 0; i--){
			strength = nums[i];
			if(strength == 0){
				min_steps[i] = -1;
			}else{
				for(int k = 1; k <= strength; k++){
					nextPosi = i + k;
					if(nextPosi >= nums.length){
						min_steps[i] = 1;
						break;
					}else{
						if(min_steps[nextPosi] == -1)
							continue;
						min_steps[i] = min_steps[nextPosi] + 1 < min_steps[i] ?
								min_steps[nextPosi] + 1 : min_steps[i];
					}
				}
			}
		}
		if(min_steps[0] == -1 || min_steps[0] == Integer.MAX_VALUE){
			return -1;
		}
		return min_steps[0];
	}
}
