package com.yinong.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ContainerWithMostWater {
	
	public static void main(String[] args){
		int[] b = new int[15000];
//		int[] a = {76,155,15,188,180,154,84,
//		           34,187,142,22,5,27,183,111,
//		           128,50,58,2,112,179,2,100,
//		           111,115,76,134,120,118,103,
//		           31,146,58,198,134,38,104,170,
//		           25,92,112,199,49,140,135,160,
//		           20,185,171,23,98,150,177,198,
//		           61,92,26,147,164,144,51,196,
//		           42,109,194,177,100,99,99,125,
//		           143,12,76,192,152,11,152,124,
//		           197,123,147,95,73,124,45,86,168,
//		           24,34,133,120,85,81,163,146,75,92,
//		           198,126,191};
		for(int i = 0; i < 15000; i++){
			b[i] = i+1;
		}
		System.out.println(maxAreaV3(b));
	}
	
	
	static int maxAreaV3(int[] height){
		int left = 0, right = height.length-1, max_area= -1;
		while(left < right){
			if(height[left] <= height[right]){
				max_area = Math.max(max_area, height[left]*(right-left));
				left += 1;
			}else{
				max_area = Math.max(max_area, height[right]*(right-left));
				right -= 1;
			}
		}
		return max_area;
	}
	
	static int maxAreaV2(int[] height){
		double water = -1, mean_height = -1, min_height, max_water = -1;
		int left;
		for(int i = 0; i < height.length-1; i++){
			water = 0;
			left = height[i];
			if(left <= mean_height){
				continue;
			}
			for(int j = i+1; j < height.length; j++){
				min_height = water / (j - i);
				if(min_height >= height[j]){
					continue;
				}
				
				if(left <= height[j]){
					water = left * (j - i);
				}else{
					water = (j - i) * height[j];
				}
			}
			//此时water中存放的是一轮迭代结束后，最多的水量
			mean_height = water / (height.length - 1 - i);
			if(water > max_water){
				max_water = water;
			}
		}
		return (int)max_water;
	}
	
	static int maxArea(int[] height){
		ArrayList<int[]> info = new ArrayList<int[]>();
		for(int i = 0; i < height.length; i++){
			info.add(new int[]{height[i], i});
		}
		Collections.sort(info, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[0]-o1[0];
			}
		});
		int max = -1, left, right, tmp_area;
		int[] fir, sec, tmp;
		Iterator<int[]> iter = info.iterator();
		fir = iter.next();
		sec = iter.next();
		left = fir[1] < sec[1] ? fir[1] : sec[1];
		right = fir[1] > sec[1] ? fir[1] : sec[1];
		max = sec[0] * (right - left);
		while(iter.hasNext()){
			tmp = iter.next();
			if(tmp[1] < left){
				tmp_area = (right - tmp[1]) * tmp[0];
				if(tmp_area > max){
					max = tmp_area;
					left = tmp[1];
				}
			}else if(tmp[1] > right){
				tmp_area = (tmp[1] - left) * tmp[0];
				if(tmp_area > max){
					max = tmp_area;
					right = tmp[1];
				}
			}
		}
		return max;
	}
}
