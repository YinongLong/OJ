package com.yinong.leetcode;

public class LongestCommonPrefix {
	
	public static void main(String[] args){
		String[] strs = new String[]{
				"aa","a"
		};
		System.out.println(longestCommonPrefix(strs));
	}
	
	static String longestCommonPrefix(String[] strs){
		if(strs == null || strs.length == 0){
			return "";
		}
		String com_prefix = strs[0], temp_str;
		if(com_prefix.equals("")) return "";
		for(int i = 1; i < strs.length; i++){
			temp_str = strs[i];
			int j = 0;
			for(j = 0; j < com_prefix.length() && j < temp_str.length(); j++){
				if(com_prefix.charAt(j) != temp_str.charAt(j)){
					com_prefix = com_prefix.substring(0, j);
					break;
				}
			}
			com_prefix = com_prefix.substring(0, j);
		}
		return com_prefix;
	}
}
