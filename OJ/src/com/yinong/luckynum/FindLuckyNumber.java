package com.yinong.luckynum;

import java.util.Scanner;

public class FindLuckyNumber {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int T;
		while(scan.hasNext()){
			T = scan.nextInt();
			for(int i = 0; i < T; i++){
				System.out.println(getLuckyNumber(scan.nextLong()));
			}
		}
		scan.close();
	}
	
	static String getLuckyNumber(long order){
		//计算所占的位数的前一个的大小
		int num_bits = (int)Math.ceil(Math.log(order+2)/Math.log(2)) - 2;
		//计算在当前位中的次序，base为0
		long sub_order = order - (long)Math.pow(2, num_bits+1) + 1;
		String bin_result = Long.toBinaryString(sub_order);
		StringBuffer result = new StringBuffer();
		char temp;
		for(int i = 0; i < bin_result.length(); i++){
			temp = bin_result.charAt(i);
			if(temp == '0'){
				result.append('4');
			}else{
				result.append('7');
			}
		}
		return result.toString();
	}
}
