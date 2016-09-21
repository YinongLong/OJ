package com.yinong.algorithms_analysis;

import java.util.Scanner;

public class GoStep {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n;
		while(scan.hasNext()){
			n = scan.nextInt();
			for(int i = 0; i < n; i++){
				System.out.println(numOfMethods(scan.nextInt()));
			}
		}
		scan.close();
	}
	
	/**
	 * 利用动态规划计算总共上楼梯的办法数
	 * @param m	指定的上的楼梯的阶数
	 * @return
	 */
	static int numOfMethods(int m){
		int result;
		if(m == 1){
			result = 0;
		}else if(m == 2){
			result = 1;
		}else if(m == 3){
			result = 2;
		}else{
			int m_2 = 1;
			int m_1 = 2;
			for(int i = 4; i < m; i++){
				m_1 += m_2;
				m_2 = m_1 - m_2;
			}
			result = m_1 + m_2;
		}
		return result;
	}
}
