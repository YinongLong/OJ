package com.yinong.gene_number;

public class Main {
	
	public static void main(String[] args){
		int n = 100;
		
		int[] numbers = new int[n*n];
		String[] re = new String[n];
		for(int i=1; i <= n*n; i++){
			numbers[i-1] = i;
		}
		
		boolean down = true;
		int count_top = 0, count_bot = n-1;
		String line = "";
		for(int i = 0; i < (n*n); i++){
			if((i+1) % n == 0){
				line += numbers[i] + "*";
				line = line.substring(0, line.length()-1);
				if(down){
					re[count_top++] = line;
					down = false;
				}else{
					re[count_bot--] = line;
					down = true;
				}
				line = "";
			}else{
				line += numbers[i] + "*";
			}
		}
		for(int i = 0; i < re.length; i++){
			if(i == re.length-1){
				System.out.print(re[i]);
			}else{
				System.out.println(re[i]);
			}
		}
	}
}
