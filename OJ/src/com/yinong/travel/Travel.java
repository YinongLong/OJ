package com.yinong.travel;

import java.util.Scanner;

public class Travel {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String N_M, view_fir, view_sec;
		
		while(scan.hasNext()){
			N_M = scan.nextLine().trim();
			view_fir = scan.nextLine().trim();
			view_sec = scan.nextLine().trim();
			boolean forward = isValid(N_M, view_fir, view_sec, true);
			
			//对小B看到的进行反转
			view_fir = reverseStr(view_fir);
			view_sec = reverseStr(view_sec);
			boolean backward = isValid(N_M, view_fir, view_sec, false);
			if(forward && backward){
				System.out.println("both");
			}else if(forward){
				System.out.println("forward");
			}else if(backward){
				System.out.println("backward");
			}else{
				System.out.println("invalid");
			}
		}
		scan.close();
	}
	
	static boolean isValid(String N_M, String view_1, String view_2, boolean fr){
		int view_fir_len, view_sec_len, min_len;
		view_fir_len = view_1.length();
		view_sec_len = view_2.length();
		min_len = Math.min(view_fir_len, view_sec_len);
		//进行farward寻找
		boolean fir_found = false, sec_found = false;
		for(int i = 0; i < N_M.length()-min_len+1; i++){
			if(!fir_found && N_M.charAt(i) == view_1.charAt(0)){
				if(N_M.substring(i, i+view_fir_len).equals(view_1)){
					if(fr){
						fir_found = true;
					}else{
						if(sec_found)
							fir_found = true;
					}
				}
			}
			if(!sec_found && N_M.charAt(i) == view_2.charAt(0)){
				if(N_M.substring(i, i+view_sec_len).equals(view_2)){
					if(!fr){
						sec_found = true;
					}else{
						if(fir_found)
							sec_found = true;
					}
				}
			}
			if(fir_found && sec_found){
				break;
			}
		}
		return fir_found && sec_found;
	}
	
	static String reverseStr(String view){
		char[] arr_view = view.toCharArray();
		char temp;
		int i = 0, j = view.length()-1;
		while(i < j){
			temp = arr_view[j];
			arr_view[j] = arr_view[i];
			arr_view[i] = temp;
			i++; j--;
		}
		return String.valueOf(arr_view);
	}
}
