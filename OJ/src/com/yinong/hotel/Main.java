package com.yinong.hotel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> prices = new ArrayList<Integer>();
		int num;
		while(scan.hasNext()){
			num = scan.nextInt();
			if(num == -1) break;
			prices.add(num);
		}
		System.out.println(getMinDays(prices));
		scan.close();
	}
	
	static int getMinDaysV2(ArrayList<Integer> prices){
		int money = prices.get(prices.size()-1);
		prices.remove(prices.size()-1);
		Collections.sort(prices);
		int temp, price;
		Node root = new Node(Integer.MAX_VALUE, 0, money, 0), child, temp_node;
		ArrayDeque<Node> wait = new ArrayDeque<Node>();
		wait.add(root);
		int level_num = 1, next_level, price_index = prices.size()-1;
		while(!wait.isEmpty()){
			price = prices.get(price_index);
			next_level = 0;
			for(int k = 0; k < level_num; k++){
				temp_node = wait.pop();
				if(price > temp_node.money) continue;
				temp = temp_node.money / price;
				for(int j = 0; j <= temp; j++){
					child = new Node(price, j, temp_node.money - price * j, temp_node.days + j);
					if(child.money == 0){
						return child.days;
					}else{
						wait.add(child);
						next_level += 1;
					}
				}
			}
			level_num = next_level;
			price_index -= 1;
			if(price_index == -1) break;
		}
		return -1;
	}
	
	static int getMinDays(ArrayList<Integer> prices){
		int money = prices.get(prices.size()-1);
		prices.remove(prices.size()-1);
		Collections.sort(prices);
		int temp, price;
		Node root = new Node(Integer.MAX_VALUE, 0, money, 0), child, temp_node;
		ArrayDeque<Node> wait = new ArrayDeque<Node>();
		ArrayList<Node> result = new ArrayList<Node>();
		wait.add(root);
		while(!wait.isEmpty()){
			temp_node = wait.pop();
			for(int i = prices.size()-1; i >= 0; i--){
				price = prices.get(i);
				if(price >= temp_node.price || temp_node.money < price) continue;
				temp = temp_node.money / price;
				for(int j = 0; j <= temp; j++){
					child = new Node(price, j, temp_node.money - price * j, temp_node.days + j);
					if(child.money == 0){
						result.add(child);
					}else{
						wait.add(child);
					}
				}
				
			}
		}
		if(result.size() == 0) return -1;
		int minDays = Integer.MAX_VALUE;
		for(int i = 0; i < result.size(); i++){
			minDays = result.get(i).days < minDays ? result.get(i).days : minDays;
		}
		return minDays;
	}
	
}

class Node{
	// 记录本节点生成时的价格
	int price;
	// 记录在这个价格住的天数
	int nums;
	// 记录住完这个节点还剩余的钱
	int money;
	// 记录住完这个节点总共已经住的天数
	int days;
	
	public Node(int price, int nums, int money, int days) {
		this.price = price;
		this.nums = nums;
		this.money = money;
		this.days = days;
	}
}
