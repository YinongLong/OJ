package com.yinong.escapeMaze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n, m, p;
		int[][] graph;
		while(scan.hasNext()){
			n = scan.nextInt();
			m = scan.nextInt();
			p = scan.nextInt();
			graph = new int[n][m];
			for(int i = 0; i < n; i++){
				for(int j = 0; j < m; j++){
					graph[i][j] = scan.nextInt();
				}
			}
			System.out.println(getPath(graph, n, m, p));
		}
		scan.close();
	}
	
	/**
	 * 根据此时的坐标以及能量进行判断是否继续进行展开
	 * @param x
	 * @param y
	 * @param p
	 * @return
	 */
	static boolean goahead(int x, int y, int m, int p){
		if(p <= 0)
			return false;
		else{
			if((3 * x + (m - 1) - y) >= p)
				return false;
			else
				return true;
		}
	}
	
	static String getPath(int[][] graph, int n, int m, int p){
		Node root = new Node(n, m);
		root.p = p;
		root.posi = new int[]{0, 0};
		root.isAccessed[m*root.posi[0]+root.posi[1]] = true;
		Queue<Node> waitExpend = new ArrayDeque<Node>();
		waitExpend.add(root);
		int new_up,
			new_down,
			new_left,
			new_right;
		ArrayList<Node> success = new ArrayList<Node>();
		while(!waitExpend.isEmpty()){
			Node temp = waitExpend.poll();
			if(temp.p <= 0) continue;
			new_left = temp.posi[1] - 1;
			new_right = new_left + 2;
			new_up = temp.posi[0] - 1;
			new_down = new_up + 2;
			if(new_left >= 0 && graph[temp.posi[0]][new_left] != 0 
					&& temp.p > 1 && !temp.isAccessed[m*temp.posi[0]+new_left]){//往左移
				Node left_child = new Node();
				left_child.p = temp.p - 1;
				left_child.parent = temp;
				left_child.posi = new int[]{temp.posi[0], new_left};
				
				left_child.isAccessed = temp.isAccessed.clone();
				left_child.isAccessed[m*temp.posi[0]+new_left] = true;
				
				if(goahead(left_child.posi[0], left_child.posi[1], m, left_child.p)){
					waitExpend.add(left_child);
				}
			}
			if(new_right <= m-1 && graph[temp.posi[0]][new_right] != 0
					&& temp.p >= 1 && !temp.isAccessed[m*temp.posi[0]+new_right]){//往右移
				Node right_child = new Node();
				right_child.p = temp.p - 1;
				right_child.parent = temp;
				right_child.posi = new int[]{temp.posi[0], new_right};
				
				right_child.isAccessed = temp.isAccessed.clone();
				right_child.isAccessed[m*temp.posi[0]+new_right] = true;
				
				if(right_child.posi[0] == 0 && right_child.posi[1] == m-1){
					success.add(right_child);
				}else if(goahead(right_child.posi[0], right_child.posi[1], m, right_child.p)){
					waitExpend.add(right_child);
				}
			}
			if(new_up >= 0 && graph[new_up][temp.posi[1]] != 0
					&& temp.p >= 3 && !temp.isAccessed[m*new_up+temp.posi[1]]){//向上移动
				Node up_child = new Node();
				up_child.p = temp.p - 3;
				up_child.parent = temp;
				up_child.posi = new int[]{new_up, temp.posi[1]};
				
				up_child.isAccessed = temp.isAccessed.clone();
				up_child.isAccessed[m*new_up+temp.posi[1]] = true;
				
				if(up_child.posi[0] == 0 && up_child.posi[1] == m-1){
					success.add(up_child);
				}else if(goahead(up_child.posi[0], up_child.posi[1], m, up_child.p)){
					waitExpend.add(up_child);
				}
			}
			if(new_down <= n-1 && graph[new_down][temp.posi[1]] != 0
					&& !temp.isAccessed[m*new_down+temp.posi[1]]){
				Node down_child = new Node();
				down_child.p = temp.p;
				down_child.parent = temp;
				down_child.posi = new int[]{new_down, temp.posi[1]};
				
				down_child.isAccessed = temp.isAccessed.clone();
				down_child.isAccessed[m*new_down+temp.posi[1]] = true;
				
				if(goahead(down_child.posi[0], down_child.posi[1], m, down_child.p)){
					waitExpend.add(down_child);
				}
			}
		}
		String path;
		if(success.isEmpty())
			path = "Can not escape!";
		else{
			int max_p = -1,
				max_index = -1;
			for(int i = 0; i < success.size(); i++){
				if(max_index == -1){
					max_p = success.get(i).p;
					max_index = i;
				}else{
					if(max_p < success.get(i).p){
						max_p = success.get(i).p;
						max_index = i;
					}
				}
			}
			//找出了消耗最少的路径
			Node end = success.get(max_index);
			path = "";
			while(end != null){
				path = "[" + end.posi[0] + "," + end.posi[1] + "]," + path;
				end = end.parent;
			}
			path = path.substring(0, path.length()-1);
		}
		return path;
	}
}

class Node{
	// 用来标到目前为止，以该节点为终止节点的路径经过的点
	boolean[] isAccessed;
	
	public Node(int n, int m){
		isAccessed = new boolean[n*m];
	}
	
	public Node(){}
	
	Node parent = null;
	//表征自己在图中的索引位置
	int[] posi;
	int p = -1;
}
