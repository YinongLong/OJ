package com.yinong.leetcode;

public class ReverseInteger {
	
	public static void main(String[] args){
		System.out.println(reverse(1534236469));
	}
	
	static int reverse(int x){
		String initial = Integer.toString(x);
		boolean have_sign = initial.startsWith("-");
		StringBuffer result = new StringBuffer();
		int end = 0, posi = initial.length()-1;
		if(have_sign) end = 1;
		while(posi >= end){
			result.append(initial.charAt(posi--));
		}
		if(have_sign) result.insert(0, '-');
		long re = Long.parseLong(result.toString());
		if(re > Integer.MAX_VALUE || re < Integer.MIN_VALUE)
			return 0;
		return (int)Long.parseLong(result.toString());
	}
}
