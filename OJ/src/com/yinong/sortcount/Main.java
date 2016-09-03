package com.yinong.sortcount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class Main {
	
	public static void main(String[] args){
		int[] numbers = {1,2,3,4,5,6,2,1,3,4,1,13,4,5};
		
		// 计数
		HashMap<Integer, Integer> info = new HashMap<>();
		for(int num : numbers){
			if(info.containsKey(num)){
				info.replace(num, info.get(num)+1);
			}else{
				info.put(num, 1);
			}
		}
		
		// 列表排序
		Set<Entry<Integer, Integer>> result = info.entrySet();
		ArrayList<Entry<Integer, Integer>> list_result = new ArrayList<>();
		for(Entry<Integer, Integer> item : result){
			list_result.add(item);
		}
		Collections.sort(list_result, new Comparator<Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
				if(o1.getValue() < o2.getValue())
					return 1;
				else if(o1.getValue() > o2.getValue())
					return -1;
				else
					return 0;
			}
		});
		
		// 生成结果
		int index = 0;
		Entry<Integer, Integer> item;
		Iterator<Entry<Integer, Integer>> iter = list_result.iterator();
		while(iter.hasNext()){
			item = iter.next();
			for(int i = index; i < index+item.getValue(); i++)
				numbers[i] = item.getKey();
			index += item.getValue();
		}
		
		// 输出结果
		for(int num : numbers){
			System.out.print(num + " ");
		}
	}
}
