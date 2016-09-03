package com.yinong.rectangle;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		// 读入所有的线段的坐标点
		int nums_line = scanner.nextInt();
		int[][] points = new int[nums_line][4];
		int temp_x, temp_y;
		for(int i = 0; i < nums_line; i++){
			for(int j = 0; j < 4; j++){
				points[i][j] = scanner.nextInt();
			}
			if(points[i][0] > points[i][2]){
				temp_x = points[i][0];
				temp_y = points[i][1];
				points[i][0] = points[i][2];
				points[i][1] = points[i][3];
				points[i][2] = temp_x;
				points[i][3] = temp_y;
			}else if(points[i][0] == points[i][2]){
				if(points[i][1] > points[i][3]){
					temp_x = points[i][0];
					temp_y = points[i][1];
					points[i][0] = points[i][2];
					points[i][1] = points[i][3];
					points[i][2] = temp_x;
					points[i][3] = temp_y;
				}
			}
		}
		scanner.close();
		
		ArrayList<Integer> horizontal = new ArrayList<Integer>();
		ArrayList<Integer> vertical = new ArrayList<Integer>();
		for(int i = 0; i < nums_line; i++){
			if((points[i][0] == points[i][2]) && (points[i][1] == points[i][3])){//说明为点
				continue;
			}
			if(points[i][0] == points[i][2]){//横坐标相同，说明为竖直线
				vertical.add(i);
			}
			if(points[i][1] == points[i][3]){//纵坐标相同，说明为水平线
				horizontal.add(i);
			}
		}
		ArrayList<int[]> sample_rec_hori = new ArrayList<>();
		ArrayList<int[]> sample_rec_vert = new ArrayList<>();
		//寻找可能属于同一个矩形的水平线
		int[] temp_point, another_point;
		for(int i = 0; i < horizontal.size(); i++){
			temp_point = points[horizontal.get(i)];
			for(int j = i+1; j < horizontal.size(); j++){
				another_point = points[horizontal.get(j)];
				if((temp_point[0] == another_point[0]) && (temp_point[2] == another_point[2])
						&& (temp_point[1] != another_point[1]))
					sample_rec_hori.add(new int[]{i, j});
			}
		}
		
		for(int i = 0; i < vertical.size(); i++){
			temp_point = points[vertical.get(i)];
			for(int j = i+1; j < vertical.size(); j++){
				another_point = points[vertical.get(j)];
				if((temp_point[1] == another_point[1]) && (temp_point[3] == another_point[3])
						&& (temp_point[0] != another_point[0]))
					sample_rec_vert.add(new int[]{i, j});
			}
		}
		
		int[] indices, another_indices;
		int[] h_1, h_2, v_1, v_2;
		int left_x = 0, left_y = 0, right_x = 0, right_y = 0, j = 0;
		boolean find = false;
		for(int i = 0; i < sample_rec_hori.size(); i++){
			indices = sample_rec_hori.get(i);
			h_1 = points[horizontal.get(indices[0])];
			h_2 = points[horizontal.get(indices[1])];
			for(j = 0; j < sample_rec_vert.size(); j++){
				another_indices = sample_rec_vert.get(j);
				v_1 = points[vertical.get(another_indices[0])];
				v_2 = points[vertical.get(another_indices[1])];
				if(v_1[0] == h_1[0] && v_2[0] == h_1[2] && v_1[1] == h_1[1] && v_1[3] == h_2[1]){//good
					left_x = v_1[0]; right_x = v_2[0]; left_y = h_1[1]; right_y = h_2[1];
					find = true; break;
				}else
				if(v_1[0] == h_1[0] && v_2[0] == h_1[2] && v_1[1] == h_2[1] && v_1[3] == h_1[1]){//good
					left_x = v_1[0]; right_x = v_2[0]; left_y = h_2[1]; right_y = h_1[1];
					find = true; break;
				}else
				if(v_1[0] == h_1[2] && v_2[0] == h_1[0] && v_1[1] == h_1[1] && v_1[3] == h_2[1]){//good
					left_x = v_2[0]; right_x = v_1[0]; left_y = h_1[1]; right_y = h_2[1];
					find = true; break;
				}else
				if(v_1[0] == h_1[2] && v_2[0] == h_1[0] && v_1[1] == h_2[1] && v_1[3] == h_1[1]){//good
					left_x = v_2[0]; right_x = v_1[0]; left_y = h_2[1]; right_y = h_1[1];
					find = true; break;
				}
			}
			if(find){
				String result = left_x + " " + left_y + " " + right_x + " " + right_y;
				System.out.print(result);
				break;
			}
		}
	}

}
