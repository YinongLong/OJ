package com.yinong.leetcode;

public class RegularExpressionMatching {
	
	public static void main(String[] args){
		System.out.println(isMatch("", "c*c"));
	}
	
	/**
	 * 使用递归，但是尽可能的放弃递归是做好的方式！
	 * @param s
	 * @param p
	 * @return
	 */
	static boolean isMatch(String s, String p){
		if(s.equals(p)){
			return true;
		}
		if(p.equals("")){
			return false;
		}
		if(s.equals("")){
			if(p.length() % 2 != 0){
				for(int i = 0; i < p.length(); i += 2){
					if(p.charAt(i) != '*'){
						return false;
					}
				}
			}else{
				for(int i = 1; i < p.length(); i += 2){
					if(p.charAt(i) != '*'){
						return false;
					}
				}
			}
			return true;
		}
		char p_cur_char = p.charAt(p.length()-1);
		if(p_cur_char == '.'){
			return isMatch(s.substring(0, s.length()-1), p.substring(0, p.length()-1));
		}else if(p_cur_char == '*'){
			char p_pre_char;
			boolean result;
			if(p.length() > 1){
				p_pre_char = p.charAt(p.length()-2);
				if(p_pre_char == '.'){
					for(int i = 0; i <= s.length(); i++){
						result = isMatch(s.substring(0, s.length()-i), p.substring(0, p.length()-2));
						if(result){
							return true;
						}
					}
					return false;
				}else{
					int s_count = 0;
					for(int j = s.length()-1; j >=0 && s.charAt(j) == p_pre_char; j--){
						s_count += 1;
					}
					for(int i = 0; i <= s_count; i++){
						result = isMatch(s.substring(0, s.length()-i), p.substring(0, p.length()-2));
						if(result){
							return true;
						}
					}
					return false;
				}
			}else{
				return false;
			}
		}else{
			if(s.charAt(s.length()-1) == p_cur_char){
				return isMatch(s.substring(0, s.length()-1), p.substring(0, p.length()-1));
			}else{
				return false;
			}
		}
	}
}
