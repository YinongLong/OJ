package com.yinong.leetcode;

public class StringToInteger {
	
	public static void main(String[] args){
		System.out.println(myAtoi("    +1146905820n1"));
	}
	
	static int myAtoi(String str){
		str = ifconvert(str);
		if(str != null){
			if(str.startsWith("-") && str.length() >= 12){
				return Integer.MIN_VALUE;
			}else if(!str.startsWith("-") && str.length() >= 12){
				return Integer.MAX_VALUE;
			}
			long result = Long.parseLong(str);
			if(result > Integer.MAX_VALUE){
				return Integer.MAX_VALUE;
			}
			if(result < Integer.MIN_VALUE){
				return Integer.MIN_VALUE;
			}
			return (int)result;
		}else return 0;
	}
	
	/**
	 * 判断一个字符串是否可以进行转换
	 * @param str
	 * @return
	 */
	static String ifconvert(String str){
		str = str.trim();
		System.out.println(str);
		char temp_char;
		for(int i = 0; i < str.length(); i++){
			temp_char = str.charAt(i);
			if(!Character.isDigit(temp_char)){
				if(i == 0){
					if(temp_char == '-' || temp_char == '+'){
						continue;
					}else{
						return null;
					}
				}else{
					str = str.substring(0, i);
				}
			}
		}
		if(str.equals("") ||
				str.equals("-") ||
				str.equals("+")) return null;
		return str;
	}
}
