package com.yinong.playcards;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		int groups = scanner.nextInt();
		for(int i = 0; i < groups; i++){
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int[] numbers = new int[2*n];
			for(int j = 0; j < numbers.length; j++){
				numbers[j] = scanner.nextInt();
			}
			for(int r = 0; r < k; r++){
				numbers = insertCards(numbers, n);
			}
			String result = "";
			for(int item : numbers){
				result += item + " ";
			}
			result.trim();
			if(i == (groups - 1))
				System.out.print(result);
			else
				System.out.println(result);
		}
		scanner.close();
	}
	
	static int[] insertCards(int[] numbers, int n){
		int[] other_numbers = new int[numbers.length];
		int left = n - 1;
		int right = 2*n - 1;
		boolean flag = true;
		for(int i = right; i >= 0; i--){
			if(flag){
				other_numbers[i] = numbers[right];
				right -= 1;
				flag = false;
			}else{
				other_numbers[i] = numbers[left];
				left -= 1;
				flag = true;
			}
		}
		return other_numbers;
	}
}
