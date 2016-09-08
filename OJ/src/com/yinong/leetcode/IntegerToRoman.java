package com.yinong.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class IntegerToRoman {
	
	
	public static void main(String[] args){
		System.out.println(intToRoman(10));
	}
	
	/**
	 * 罗马字符 I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
	 * @param num
	 * @return
	 */
	static String intToRoman(int num){
		Queue<Integer> digits = new ArrayDeque<>();
		int digit;
		while(true){
			digit = num % 10;
			num /= 10;
			if(num != 0 || digit != 0){
				digits.add(digit);
			}else{
				break;
			}
		}
		int count = 0;
		String result = "";
		while(!digits.isEmpty()){
			digit = digits.poll();
			result = convertNum(digit, count) + result;
			count += 1;
		}
		return result;
	}
	
	/**
	 * 
	 * @param digit 0-9
	 * @param posi
	 * @return
	 */
	static String convertNum(int digit, int posi){
		String[][] romans = new String[][]{
			{"I", "V", "X"},
			{"X", "L", "C"},
			{"C", "D", "M"},
			{"M"}};
		String result = "";
		if(digit >= 4){
			if(digit == 4)
				result = romans[posi][0] + romans[posi][1];
			else if(digit == 9){
				result = romans[posi][0] + romans[posi][2];
			}else{
				result = romans[posi][1];
				for(int i = 0; i < (digit-5); i++){
					result += romans[posi][0];
				}
			}
		}else{
			for(int i = 0; i < digit; i++){
				result += romans[posi][0];
			}
		}
		return result;
	}
}
