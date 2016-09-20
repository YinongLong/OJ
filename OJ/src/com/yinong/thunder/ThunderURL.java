package com.yinong.thunder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

public class ThunderURL {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		StringBuffer content = new StringBuffer();
		String line;
		
		while(scan.hasNext()){
			line = scan.nextLine().trim();
			content.append(line);
		}
		HashMap<String, Integer> result = getThunderURL(content);
		if(result.isEmpty()){
			System.out.println("no");
		}else{
			System.out.println(result.size());
			Iterator<Entry<String, Integer>> iter = result.entrySet().iterator();
			List<Entry<String, Integer>> sorted_result = new ArrayList<Entry<String,Integer>>();
			while(iter.hasNext()){
				sorted_result.add(iter.next());
			}
			Collections.sort(sorted_result, new Comparator<Entry<String, Integer>>() {
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o1.getValue()-o2.getValue();
				}
			});
			for(int i = 0; i < sorted_result.size(); i++){
				System.out.println(sorted_result.get(i).getKey());
			}
		}
		scan.close();
	}
	
	static HashMap<String, Integer> getThunderURL(StringBuffer content){
		int startIndex = 0,
			fixed_len = 19,
			posi = 1,
			foundIndex,
			charIndex;
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		StringBuffer tempURL;
		String sign = "<a href=\"thunder://";
		foundIndex = content.indexOf(sign, startIndex);
		while(foundIndex != -1){
			tempURL = new StringBuffer();
			tempURL.append(sign);
			foundIndex += fixed_len;
			for(charIndex = foundIndex; isLegal(content.charAt(charIndex)); charIndex++){
				tempURL.append(content.charAt(charIndex));
			}
//			while(tempURL.charAt(tempURL.length()-1) == '/'){
//				tempURL.deleteCharAt(tempURL.length()-1);
//			}
			result.put(tempURL.toString().substring(9), posi++);
			foundIndex = content.indexOf(sign, charIndex);
		}
		
		return result;
	}
	
	static boolean isLegal(char temp){
		if(Character.isDigit(temp)
				|| Character.isLetter(temp)
				|| temp == '+'
				|| temp == '='
				|| temp == '/'){
			return true;
		}
		return false;
	}
}
