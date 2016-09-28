package com.yinong.telenumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int T;
		String line;
		while(scan.hasNext()){
			line = scan.nextLine().trim();
			T = Integer.parseInt(line);
			for(int i = 0; i < T; i++){
				line = scan.nextLine().trim();
				System.out.println(getResult(line));
			}
		}
		scan.close();
	}
	
	static String getResult(String line){
		HashMap<Character, Integer> count = new HashMap<>();
		char temp;
		for(int i = 0; i < line.length(); i++){
			temp = line.charAt(i);
			count.put(temp, count.getOrDefault(temp, 0) + 1);
		}
		
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
		int nums;
		// 判断0
		if(count.containsKey('Z')){
			nums = count.get('Z');
			result.put(0, nums); // 0 出现的次数
			if(count.containsKey('E'))
				count.put('E', count.get('E') - nums);
			if(count.containsKey('R'))
				count.put('R', count.get('R') - nums);
			if(count.containsKey('O'))
				count.put('O', count.get('O') - nums);
		}
		// 判断4
		if(count.containsKey('U')){
			nums = count.get('U');
			result.put(4, nums); // 如果4出现，则得到4出现的次数
			if(count.containsKey('F'))
				count.put('F', count.get('F') - nums);
			if(count.containsKey('O'))
				count.put('O', count.get('O') - nums);
			if(count.containsKey('R'))
				count.put('R', count.get('R') - nums);
		}
		//判断5
		if(count.containsKey('F') && count.get('F') != 0){
			nums = count.get('F');
			result.put(5, nums);
			count.put('F', 0);
			if(count.containsKey('I'))
				count.put('I', count.get('I') - nums);
			if(count.containsKey('V'))
				count.put('V', count.get('V') - nums);
			if(count.containsKey('E'))
				count.put('E', count.get('E') - nums);
		}
		
		// 判断7
		if(count.containsKey('V') && count.get('V') != 0){
			nums = count.get('V');
			result.put(7, nums);
			count.put('V', 0);
			if(count.containsKey('S'))
				count.put('S', count.get('S') - nums);
			if(count.containsKey('N'))
				count.put('N', count.get('N') - nums);
			if(count.containsKey('E'))
				count.put('E', count.get('E') - 2 * nums);
		}
		
		// 判断8
		if(count.containsKey('G')){
			nums = count.get('G');
			result.put(8, nums);
			if(count.containsKey('E'))
				count.put('E', count.get('E') - nums);
			if(count.containsKey('I'))
				count.put('I', count.get('I') - nums);
			if(count.containsKey('H'))
				count.put('H', count.get('H') - nums);
			if(count.containsKey('T'))
				count.put('T', count.get('T') - nums);
		}
		
		// 判断3
		if(count.containsKey('H') && count.get('H') != 0){
			nums = count.get('H');
			result.put(3, nums);
			count.put('H', 0);
			if(count.containsKey('T'))
				count.put('T', count.get('T') - nums);
			if(count.containsKey('R'))
				count.put('R', count.get('R') - nums);
			if(count.containsKey('E'))
				count.put('E', count.get('E') - 2 * nums);
		}
		// 判断2
		if(count.containsKey('T') && count.get('T') != 0){
			nums = count.get('T');
			result.put(2, nums);
			count.put('T', 0);
			if(count.containsKey('W'))
				count.put('W', count.get('W') - nums);
			if(count.containsKey('O'))
				count.put('O', count.get('O') - nums);
		}
		
		// 判断6
		if(count.containsKey('S') && count.get('S') != 0){
			nums = count.get('S');
			result.put(6, nums);
			count.put('S', 0);
			if(count.containsKey('I'))
				count.put('I', count.get('I') - nums);
			if(count.containsKey('X'))
				count.put('X', count.get('X') - nums);
		}
		
		// 判断9
		if(count.containsKey('I') && count.get('I') != 0){
			nums = count.get('I');
			result.put(9, nums);
			count.put('I', 0);
			if(count.containsKey('N'))
				count.put('N', count.get('N') - 2 * nums);
			if(count.containsKey('E'))
				count.put('E', count.get('E') - nums);
		}
		
		// 判断1
		if(count.containsKey('O') && count.get('O') != 0){
			nums = count.get('O');
			result.put(1, nums);
			count.put('O', 0);
		}
		
		// 数字转换完成
		Iterator<Entry<Integer, Integer>> iter = result.entrySet().iterator();
		ArrayList<CusEntry> arr_re = new ArrayList<>();
		Entry<Integer, Integer> temp_en;
		int key, val;
		while(iter.hasNext()){
			temp_en = iter.next();
			key = temp_en.getKey();
			val = temp_en.getValue();
			key -= 8;
			if(key < 0) key += 10;
			arr_re.add(new CusEntry(key, val));
		}
		
		
		Collections.sort(arr_re, new Comparator<CusEntry>() {
			public int compare(CusEntry o1, CusEntry o2) {
				return o1.getKey()-o2.getKey();
			}
		});
		
		String retu_re = "";
		
		int temp_num;
		for(int i = 0; i < arr_re.size(); i++){
			temp_en = arr_re.get(i);
			temp_num = temp_en.getKey();
			for(int j = 0; j < temp_en.getValue(); j++){
				retu_re += temp_num;
			}
		}
 		return retu_re;
	}
}

class CusEntry implements Entry<Integer, Integer>{
	
	private int key;
	private int value;
	
	public CusEntry(int key, int value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public Integer getKey() {
		return key;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer setValue(Integer value) {
		return this.value = value;
	}
	
}