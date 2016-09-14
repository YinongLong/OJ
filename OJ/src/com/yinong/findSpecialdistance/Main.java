package com.yinong.findSpecialdistance;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int T, n, m, s, t, u, v, w, result;
		int[][] graph;
		while(scan.hasNext()){
			T = scan.nextInt();
			for(int i = 0; i < T; i++){
				n = scan.nextInt();
				m = scan.nextInt();
				s = scan.nextInt();
				t = scan.nextInt();
				graph = new int[n][n];
				initGaph(graph);
				for(int j = 0; j < m; j++){
					u = scan.nextInt();
					v = scan.nextInt();
					w = scan.nextInt();
					graph[u-1][v-1] = w;
					graph[v-1][u-1] = w;
				}
				result = getSpecialDistance(graph, s-1, t-1);
				if(result == -1){
					System.out.println("Case #" + (i+1) + ": No answer");
				}else{
					System.out.println("Case #" + (i+1) + ": " + result);
				}
			}
		}
		scan.close();
	}
	
	static int getSpecialDistance(int[][] graph, int start, int end){
		//存储已经找到最短路径的点
		boolean[] min_found = new boolean[graph.length];
		min_found[start] = true;
		//初始化存储距离的向量
		int[] arr_distance = new int[graph.length];
		//存储路径
		int[] paths = new int[graph.length];
		for(int i = 0; i < arr_distance.length; i++){
			arr_distance[i] = graph[start][i];
			if(arr_distance[i] != -1){
				paths[i] = start;
			}else{
				paths[i] = -1;
			}
		}
		
		boolean isFound = false;
		while(!isFound){
			//找最近点
			int max_index = -1, max_num = -1;
			for(int i = 0; i < arr_distance.length; i++){
				if(min_found[i]) continue;
				if(arr_distance[i] != -1){
					if(max_num == -1){
						max_num = arr_distance[i];
						max_index = i;
					}else if(arr_distance[i] < max_num){
						max_num = arr_distance[i];
						max_index = i;
					}
				}
			}
			if(max_index == end){
				isFound = true;
				continue;
			}else if(max_index == -1){
				return -1;
			}
			min_found[max_index] = true;
			
			//更新一次存储距离的数组
			for(int i = 0; i < arr_distance.length; i++){
				if(min_found[i]) continue;
				if(graph[max_index][i] != -1){
					if(arr_distance[i] == -1 || arr_distance[max_index] + graph[max_index][i] < arr_distance[i]){
						arr_distance[i] = arr_distance[max_index] + graph[max_index][i];
						paths[i] = max_index;
					}
				}
			}
			
		}
		
		int result = 0, pre_index = paths[end], cur_index = end;
		while(pre_index != start){
			if(graph[pre_index][cur_index] > result){
				result = graph[pre_index][cur_index];
			}
			cur_index = pre_index;
			pre_index = paths[cur_index];
		}
		if(graph[pre_index][cur_index] > result) result = graph[pre_index][cur_index];
		return result;
	}
	
	static void initGaph(int[][] graph){
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				graph[i][j] = -1;
			}
		}
	}
}
