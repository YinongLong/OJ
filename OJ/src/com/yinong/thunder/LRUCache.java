package com.yinong.thunder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class LRUCache {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String line;
		String[] eles;
		int capacity, result;
		line = scan.nextLine().trim();
		capacity = Integer.parseInt(line);
		Cache process_data = new Cache(capacity);
		while(scan.hasNext()){
			line = scan.nextLine().trim();
			eles = line.split(" ");
			if(eles.length > 1){
				process_data.set(Integer.parseInt(eles[0]), Integer.parseInt(eles[1]));
			}else{
				result = process_data.get(Integer.parseInt(eles[0]));
				System.out.println(result);
			}
			
		}
		scan.close();
	}
	
}

class Cache{
	
	private int capacity;
	// 记录已经存储的数据的个数
	private int count = 0;
	// 记录最近最久未使用的顺序
	private int order= 0;
	
	private int old_key;
	
	HashMap<Integer, Integer> container;
	HashMap<Integer, Integer> record;
	
	public Cache(int capacity){
		this.capacity = capacity;
		// 保存key-value数据
		container = new HashMap<Integer, Integer>();
		// 记录最近最久未使用
		record = new HashMap<Integer, Integer>();
	}
	
	public int get(int key){
		if(container.get(key) == null)
			return -1;
		else{
			record.put(key, order++);
			sortOrder();
			return container.get(key);
		}
	}
	
	/**
	 * 对最近最久未使用进行排序
	 */
	private void sortOrder(){
		ArrayList<Entry<Integer, Integer>> temp = new ArrayList<>();
		Iterator<Entry<Integer, Integer>> iter = record.entrySet().iterator();
		while(iter.hasNext()){
			temp.add(iter.next());
		}
		Collections.sort(temp, new Comparator<Entry<Integer, Integer>>() {
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				return o1.getValue()-o2.getValue();
			}
		});
		old_key = temp.get(0).getKey();
	}
	
	private void deleteEle(){
		container.remove(old_key);
		record.remove(old_key);
		sortOrder();
	}
	
	public void set(int key, int value){
		if(count < capacity){
			if(container.get(key) == null){
				count += 1;
			}
			container.put(key, value);
			record.put(key, order++);
		}else{
			deleteEle();
			container.put(key, value);
			record.put(key, order++);
		}
		sortOrder();
	}
	
}
