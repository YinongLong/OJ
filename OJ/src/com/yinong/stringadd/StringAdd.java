package com.yinong.stringadd;

import java.util.Scanner;

public class StringAdd {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String input;
		String[] eles;
		while(scan.hasNext()){
			input = scan.nextLine().trim();
			eles = input.split(",");
			System.out.println(addition(eles));
		}
		scan.close();
	}
	
	static String addition(String[] args){
		if(args.length != 2){
			return "error";
		}
		int max_i = args[0].length() > args[1].length() ? 0 : 1;
		char[] fir = args[max_i].toCharArray(); //存储较长的字符串
		char[] sec = args[1-max_i].toCharArray();
		
		char temp_a, temp_b;
		int sum = 0;
		boolean farward = false;
		for(int i = 0; i < sec.length; i++){
			temp_a = fir[fir.length-1-i];
			temp_b = sec[sec.length-1-i];
			if(Character.isDigit(temp_a) && Character.isDigit(temp_b)){
				sum = Character.digit(temp_a, 10) + Character.digit(temp_b, 10);
				if(farward){
					sum += 1;
					farward = false;
				}
				if(sum > 9){
					farward = true;
					sum %= 10;
				}
				fir[fir.length-1-i] = Character.forDigit(sum, 10);
			}else{
				return "error";
			}
		}
		String result = String.valueOf(fir);;
		if(farward){
			if(fir.length == sec.length){
				result = "1" + result;
			}else{
				char temp = fir[fir.length-1-sec.length];
				fir[fir.length-1-sec.length] = Character.forDigit(Character.digit(temp, 10)+1, 10);
				result = String.valueOf(fir);
			}
			
		}
		return result;
	}
}
