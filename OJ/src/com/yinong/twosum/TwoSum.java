package com.yinong.twosum;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 计算给定一列偶数任意两个之间的质数的个数
 * @author YinongLong
 *
 */
public class TwoSum {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int count;
		int[] nums;
		while(scan.hasNext()){
			count = scan.nextInt();
			nums = new int[count];
			for(int i = 0; i < count; i++){
				nums[i] = scan.nextInt();
			}
			System.out.println(getSum(nums));
		}
		scan.close();
//		测试大序列
//		int[] nums = new int[10000];
//		for(int i = 0; i < nums.length; i++){
//			nums[i] = (i + 2) * 2;
//		}
//		System.out.println(getSum(nums));
	}
	
	/**
	 * 返回start到end的所有质数
	 * @param start
	 * @param end
	 * @return
	 */
	static ArrayList<Integer> generatePrime(int start, int end){
		//计算得到所有2到end所有的质数
		ArrayList<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		boolean isPrime;
		for(int i = 3; i < end; i+=2){
			isPrime = true;
			for(int j = 0; j < primes.size() && primes.get(j) <= Math.sqrt(i); j++){
				if(i % primes.get(j) == 0){
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				primes.add(i);
			}
		}
		//删掉所有小于start的质数
		for(int i = 0; i < primes.size();){
			if(primes.get(i) < start){
				primes.remove(i);
			}else{
				break;
			}
		}
		return primes;
	}
	
	static int getSum(int[] nums){
		//得到所有需要计算的质数
		ArrayList<Integer> all_primes = generatePrime(nums[0], nums[nums.length-1]);
		//计算连续的两个偶数之间的质数个数
		int[] each_num = new int[nums.length-1];
		int cou = 0, index = 1;
		for(int i = 0; i < all_primes.size(); i++){
			if(all_primes.get(i) > nums[index]){
				each_num[index-1] = cou;
				cou = 0;
				index += 1;
			}else{
				cou += 1;
			}
		}
		each_num[index-1] = cou;
		
		//计算所有的两个偶数之间的质数个数
		int count = each_num.length, all_sum = 0, sum = all_primes.size();
		while(count >= 1){
			all_sum += count * sum;
			for(int i = 0; i < each_num.length - count + 1; i++){
				sum -= each_num[i] + each_num[each_num.length-1-i];
			}
			count-=2;
		}
		return all_sum;
	}
}
