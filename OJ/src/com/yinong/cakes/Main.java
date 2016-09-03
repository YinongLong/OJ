package com.yinong.cakes;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int W, H, row, column, sum;
		boolean start_head = true, column_decrease = false;
		while(scanner.hasNext()){
			row = 0;
			column = 0;
			sum = 0;
			W = scanner.nextInt();
			H = scanner.nextInt();
			for(int i = 0; i < H; i++){
				if(start_head){
					column = 0;
				}else{
					column = 2;
				}
				for(int j = 0; j < W; j++){
					if(column_decrease && column != 0){
						column -= 1;
						continue;
					}
					column_decrease = false;
					if(column < 2 && !column_decrease){
						sum += 1;
						column += 1;
					}else{
						column_decrease = true;
						column -= 1;
					}
				}
				row += 1;
				if(row == 2){
					start_head = !start_head;
					row = 0;
				}
			}
			System.out.print(sum);
		}
		scanner.close();
	}
}
