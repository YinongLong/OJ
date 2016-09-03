package com.yinong.leetcode;


public class AddTwoNumbers {
	
	public static void main(String[] args){
		
		int[] l1_num = {}, l2_num = {};
		ListNode l1 = new ListNode(5), temp = l1;
		for(int num : l1_num){
			temp.next = new ListNode(num);
			temp = temp.next;
		}
		ListNode l2 = new ListNode(5);
		temp = l2;
		for(int num: l2_num){
			temp.next = new ListNode(num);
			temp = temp.next;
		}
		
		ListNode result = addTwoNumbers(l1, l2);
		while(result != null){
			System.out.print(result.val + " ");
			result = result.next;
		}
	}
	
	static ListNode addTwoNumbers(ListNode l1, ListNode l2){
		ListNode result = null, temp = null, current_point = null;
		int sum;
		boolean add_one = false; // 记录是否进位
		while(l1 != null || l2 != null){
			if(l1 != null && l2 != null){
				// 加和且判断是否进位
				sum = l1.val + l2.val;
				if(add_one){
					sum += 1;
					add_one = false;
				}
				if(sum >= 10){
					add_one = true;
					sum %= 10;
				}
				// 创建新节点
				temp = new ListNode(sum);
				l1 = l1.next;
				l2 = l2.next;
				if(result == null){
					result = temp;
				}else{
					current_point.next = temp;
				}
				current_point = temp;
				if(l1 == null && l2 == null && add_one){
					current_point.next = new ListNode(1);
				}
				continue;
			}
			ListNode no_null = null;
			if(l1 == null){
				no_null = l2;
				l2 = l2.next;
			}else{
				no_null = l1;
				l1 = l1.next;
			}
			temp = new ListNode(no_null.val);
			if(add_one){
				temp.val += 1;
				if(temp.val >= 10){
					temp.val %= 10;
					add_one = true;
				}else{
					add_one = false;
				}
			}
			current_point.next = temp;
			current_point = temp;
			if(no_null.next == null && add_one){
				current_point.next = new ListNode(1);
			}
		}
		return result;
	}
}
