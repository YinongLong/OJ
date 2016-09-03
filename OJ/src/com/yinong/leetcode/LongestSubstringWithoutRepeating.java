package com.yinong.leetcode;

public class LongestSubstringWithoutRepeating {
	
	public static void main(String[] args){
		System.out.println(lengthOfLongestSubstring("aaab"));
	}
	
	static int lengthOfLongestSubstring(String s){
		StringBuffer result = new StringBuffer();
		char temp_char;
		int index, temp_max = 0, temp_len;
		for(int i = 0; i < s.length(); i++){
			temp_char = s.charAt(i);
			index = result.indexOf(temp_char+"");
			if(index == -1){
				result.append(temp_char);
			}else{
				temp_len = result.length();
				if(temp_len > temp_max) temp_max = temp_len;
				result = result.delete(0, index+1);
				result.append(temp_char);
			}
		}
		int last = result.length();
		if(last > temp_max)
			return last;
		else
			return temp_max;
	}
}


