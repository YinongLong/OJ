package com.yinong.leetcode;

import java.util.ArrayList;

public class Solution {
	
	static ListNode removeNthFromEnd(ListNode head, int n){
		ArrayList<ListNode> references = new ArrayList<ListNode>();
		references.add(head);
		while(head.next != null){
			head = head.next;
			references.add(head);
		}
		int index = references.size()-1-n;
		if(index >= 0){
			ListNode temp = references.get(index);
			temp.next = temp.next.next;
		}else{
			return references.get(0).next;
		}
		return references.get(0);
	}
}
