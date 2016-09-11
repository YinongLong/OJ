package com.yinong.finishtask;

import java.util.Scanner;

public class FinishTask {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int[] flags = new int[32];
		int id_1, id_2, posi_id_1, posi_id_2; //记录任务的ID
		while(scan.hasNext()){
			//读入两个任务的ID，[1,1024]
			id_1 = scan.nextInt(); //需要设置为已经完成
			id_2 = scan.nextInt(); //检查是否已经完成
			if(id_1 > 1024 || id_1 < 1 || id_2 > 1024 || id_2 < 1){
				System.out.println(-1);
			}
			posi_id_1 = id_1 % 32; //计算在一个整数中的具体位的位置
			id_1 /= 32; //计算需要设置完成的整数位置
			posi_id_2 = id_2 % 32;
			id_2 /= 32;
			
			//对ID1进行设置为完成
			long check = (long)Math.pow(2, posi_id_1-1);
			flags[id_1] |= check;
			
			check = (long)Math.pow(2, posi_id_2-1);
			long re = flags[id_2] & check;
			if(re == 0){
				System.out.println(0);
			}else{
				System.out.println(1);
			}
		}
		scan.close();
	
	}
}
