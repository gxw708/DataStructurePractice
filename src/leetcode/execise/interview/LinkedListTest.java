package leetcode.execise.interview;

import java.util.ArrayList;
import java.util.List;

public class LinkedListTest {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null)
            return true;
        
        List<Integer> vals = new ArrayList<Integer>();
        ListNode curr = head;
        while(curr != null)
        {
            vals.add(curr.val);
            curr = curr.next;
        }
        
        int start = 0;
        int end = vals.size()-1;
        
        while(start <= end)
        {
            if(vals.get(start++).intValue() != vals.get(end--).intValue())
                return false;
        }
        
        return true;
    }
	
	public boolean hasCycle(ListNode head) {
        List<Integer> vals = new ArrayList<Integer>();
        ListNode curr = head;
        while(curr != null)
        {
        	if(vals.contains(curr.val))
        		return true;
        	
        	vals.add(curr.val);
        	curr = curr.next;
        }
        
        return false;
    }
	
	public static void main(String[] args) {
		LinkedListTest test = new LinkedListTest();
		ListNode head = test.buildTestData(new int[]{-129,-129});
		System.out.println(test.isPalindrome(head));
	}

	private ListNode buildTestData(int[] data)
	{
		ListNode head = null;
		ListNode end = null;
		for(int d : data)
		{
			if(head == null)
			{
				head = new ListNode(d);
				end = head;
			}
			else
			{
				end.next = new ListNode(d);
				end = end.next;
			}
		}
		
		return head;
	}
}
