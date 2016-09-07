package com.yinong.leetcode;

public class ZigzagConversion {
	
	public static void main(String[] args){
		System.out.println(convert("ABC", 1));
	}
	
	static String convert(String s, int numRows){
		if(numRows == 1) return s;
		StringBuffer[] result = new StringBuffer[numRows];
		for(int i = 0; i < numRows; i++)
			result[i] = new StringBuffer();
		int rowCount = -1;
		boolean isdown = true;
		for(int i = 0; i < s.length(); i++){
			if(isdown){
				result[++rowCount].append(s.charAt(i));
				if(rowCount == (numRows-1)) isdown = false;
			}else{
				result[--rowCount].append(s.charAt(i));
				if(rowCount == 0) isdown = true;
			}
		}
		String re = "";
		for(StringBuffer item : result){
			re += item.toString();
		}
		return re;
	}
}
