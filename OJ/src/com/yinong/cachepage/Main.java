package com.yinong.cachepage;

public class Main {
	
	public static void main(String[] args){
		int max_cacge_size=2;
		int[] cache_list = {1, 2, 1, 3, 1, 2};
		
		int reslut = max_cacge_size, page, start=0, end=max_cacge_size;
		for(int i = max_cacge_size; i< cache_list.length; i++){
			page = cache_list[i];
			if(!isExist(cache_list, start, end, page)){
				reslut += 1;
				start += 1;
				end += 1;
			}
		}
		System.out.print(reslut);
	}
	
	static boolean isExist(int[] list, int start, int end, int page){
		for(int i = start; i < end; i++){
			if(list[i] == page)
				return true;
		}
		return false;
	}
}
