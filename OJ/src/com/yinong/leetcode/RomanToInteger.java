package com.yinong.leetcode;

import java.util.HashMap;

public class RomanToInteger {
	
	public static void main(String[] args){
		System.out.println(romanToInt("MMMCMXCIX"));
	}
	
	static int romanToInt(String s){
		if(s == null || s.length() == 0){
			return 0;
		}
		HashMap<String, Integer> roman_int = new HashMap<String, Integer>();
		roman_int.put("I", 1);
		roman_int.put("V", 5);
		roman_int.put("X", 10);
		roman_int.put("L", 50);
		roman_int.put("C", 100);
		roman_int.put("D", 500);
		roman_int.put("M", 1000);
		int sum = 0, temp_max = 0, temp;
		String roman;
		for(int i = s.length()-1; i >= 0; i--){
			roman = s.substring(i, i+1);
			temp = roman_int.get(roman);
			if(temp >= temp_max){
				sum += temp;
				temp_max = temp;
			}else{
				sum -= temp;
			}
		}
		return sum;
	}
}
