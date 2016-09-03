package com.yinong.minseconds;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int position, temp_x, temp_y;
		String[] x_s, y_s;
		while(scanner.hasNextLine()){
			position = Integer.parseInt(scanner.nextLine().trim());
			x_s = scanner.nextLine().trim().split(" ");
			y_s = scanner.nextLine().trim().split(" ");
			int minSeconds = -1;
			for(int i = 0; i < position; i++){
				temp_x = Integer.parseInt(x_s[i]) - 1;
				temp_y = Integer.parseInt(y_s[i]) - 1;
				if(minSeconds < 0 || minSeconds > temp_x + temp_y){
					minSeconds = temp_x + temp_y;
				}
			}
			System.out.print(minSeconds);
		}
		scanner.close();
	}
}
