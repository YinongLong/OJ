package com.yinong.algorithms_analysis;

public class MaxSubsequenceSum {
	
	public static void main(String[] args){
		int[] a = new int[]{-4,-5,-6,7,0,9,87,-6,3,4,5,-67,9,8,-7,4,-5,6,-7};
		System.out.println(getMaxSubsequenceSumV4(a));
	}
	
	/**
	 * O(N^3)时间复杂度
	 * @param sequence
	 * @return
	 * 
	 */
	static int getMaxSubsequenceSumV1(int[] sequence){
		int max_sum = 0, temp_sum = 0;
		for(int i = 0; i < sequence.length; i++){
			for(int j = i; j < sequence.length; j++){
				temp_sum = 0;
				for(int k = i; k < j; k++){
					temp_sum += sequence[k];
				}
				if(temp_sum > max_sum){
					max_sum = temp_sum;
				}
			}
		}
		return max_sum;
	}
	
	/**
	 * O(N^2)时间复杂度
	 * @param sequence
	 * @return
	 */
	static int getMaxSubsequenceSumV2(int[] sequence){
		int max_sum = 0, temp_sum = 0;
		for(int i = 0; i < sequence.length; i++){
			temp_sum = 0;
			for(int j = i; j < sequence.length; j++){
				temp_sum += sequence[j];
				if(temp_sum > max_sum){
					max_sum = temp_sum;
				}
			}
		}
		return max_sum;
	}
	
	/**
	 * O(N)时间复杂度
	 * @param sequence
	 * @return
	 */
	static int getMaxSubsequenceSumV3(int[] sequence){
		int max_sum = 0, temp_sum = 0;
		for(int i = 0; i < sequence.length; i++){
			temp_sum += sequence[i];
			if(temp_sum > max_sum){
				max_sum = temp_sum;
			}else if(temp_sum < 0){
				temp_sum = 0;
			}
		}
		return max_sum;
	}
	
	/**
	 * N*log(N)时间复杂度
	 * @param sequence
	 * @return
	 */
	static int getMaxSubsequenceSumV4(int[] sequence){
		return getMaxSum(sequence, 0, sequence.length-1);
	}
	
	static int getMaxSum(int[] sequence, int left, int right){
		if(left == right){
			if(sequence[left] < 0)
				return 0;
			else{
				return sequence[left];
			}
		}
		int center = (left + right) / 2;
		int left_maxSum = getMaxSum(sequence, left, center);
		int right_maxSum = getMaxSum(sequence, center+1, right);
		int leftSum = 0, max_leftSum = 0;
		for(int i = center; i >= left; i--){
			leftSum += sequence[i];
			if(leftSum > max_leftSum)
				max_leftSum = leftSum;
		}
		int rightSum = 0, max_rightSum = 0;
		for(int i = center+1; i <= right; i++){
			rightSum += sequence[i];
			if(rightSum > max_rightSum)
				max_rightSum = rightSum;
		}
		return Math.max((max_leftSum + max_rightSum),
				Math.max(left_maxSum, right_maxSum));
	}
}
