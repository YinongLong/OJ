package com.yinong.genetic;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		HashSet<String> materials = new HashSet<String>();
		Scanner scanner = new Scanner(System.in);
		String line;
		String[] eles;
		while(scanner.hasNextLine()){
			line = scanner.nextLine().trim();
			eles = line.split(" ");
			for(String ele : eles){
				if(!ele.equals(""))
					materials.add(ele);
			}
		}
		scanner.close();
		System.out.print(materials.size());
	}
	
}
