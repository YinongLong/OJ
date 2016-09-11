package com.yinong.stringadd;

import java.util.Scanner;

public class StringAddV2 {
	
	/**
	 * 全部用字符串实现的大数加法
	 * @param args
	 */
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String intput, result;
		String[] eles;
		while(scan.hasNext()){
			result = "";
			intput = scan.nextLine();
			eles = intput.trim().split(",");
			if(eles.length == 2){
				//获取较长的数字的位置
				int max_in = eles[0].length() > eles[1].length() ? 0 : 1;
				int count = eles[1-max_in].length(), fir_in, sec_in, sum;
				//标志是否向前进位
				boolean farward = false;
				for(int i = 0; i < count; i++){
					sum = 0;
					fir_in = eles[0].length()-1-i;
					sec_in = eles[1].length()-1-i;
					String temp_a = eles[0].substring(fir_in, fir_in+1);
					String temp_b = eles[1].substring(sec_in, sec_in+1);
					sum = Integer.parseInt(temp_a) + Integer.parseInt(temp_b);
					if(farward){ //加上进位的1
						sum += 1;
						farward = false;
					}
					if(sum >= 10){
						sum %= 10;
						farward = true;
					}
					result = sum + result;
				}
				int posi = eles[max_in].length()-1-count;
				//检测是否还有进位值，有的话继续进行处理
				if(farward){
					if(eles[0].length() == eles[1].length()){
						result = "1" + result;
					}else{
						String middle = eles[max_in].substring(posi, posi+1);
						result = eles[max_in].substring(0, posi) +
								(Integer.parseInt(middle)+1) + result;
					}
				}else{
					result = eles[max_in].substring(0, posi+1) + result;
				}
			}else{
				result = "error";
			}
			System.out.println(result);
		}
		scan.close();
	}
}
