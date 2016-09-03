package com.yinong.findpath;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		String[] eles = line.split(" ");
		int n, m;
		n = Integer.parseInt(eles[0]);
		m = Integer.parseInt(eles[1]);
		int[][] graph = new int[n][m];
		for(int i = 0; i < n; i++){
			line = scanner.nextLine().trim();
			for(int j = 0; j < m; j++){
				if(line.charAt(j) == '.'){
					graph[i][j] = 0; //0 表示可以通行
				}else{
					graph[i][j] = 1; //1 表示障碍
				}
			}
		}
		int x0, y0, k;
		x0 = scanner.nextInt();
		y0 = scanner.nextInt();
		k = scanner.nextInt();
		int[][] steps = new int[k][2];
		for(int i = 0; i < k; i++){
			steps[i][0] = scanner.nextInt();
			steps[i][1] = scanner.nextInt();
		}
		System.out.print(findMaxSteps(graph, steps, x0, y0, n, m));
		scanner.close();
	}
	
	static boolean hasNextLevel(int[] level){
		for(int flag : level){
			if(flag == 1)
				return true;
		}
		return false;
	}
	
	static int findMaxSteps(int[][] graph, int[][] steps, int x0, int y0, int n, int m){
		boolean[][] accessed = new boolean[n][m];
		accessed[x0][y0] = true;
		ArrayList<int[]> points = new ArrayList<int[]>();
		ArrayList<int[]> another_points = new ArrayList<int[]>();
		points.add(new int[]{x0, y0});
		int temp_x, temp_y, c_x = x0, c_y = y0, count = 0;
		do{
			while(!points.isEmpty()){
				int[] point = points.remove(points.size()-1);
				c_x = point[0];
				c_y = point[1];
				for(int i = 0; i < steps.length; i++){
					temp_x = c_x + steps[i][0];
					temp_y = c_y + steps[i][1];
					//首先检查是否越界
					if(temp_x >= 0 && temp_x < n && temp_y >= 0 && temp_y < m){
						//在没有越界的基础上检查是否改点为障碍点
						if(graph[temp_x][temp_y] == 0){
							//在不是障碍点的基础上检查是否已经被较少的移动次数访问过
							if(!accessed[temp_x][temp_y]){
								accessed[temp_x][temp_y] = true;
								another_points.add(new int[]{temp_x, temp_y});
							}
						}
					}
				}
			}
			points.addAll(another_points);
			another_points.clear();
			if(points.size() > 0)
				count += 1;
		}while(points.size() > 0);
		if(count == 0)
			count = -1;
		return count;
	}

}
