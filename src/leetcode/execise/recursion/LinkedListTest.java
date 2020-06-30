package leetcode.execise.recursion;

public class LinkedListTest {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
		
		public String toString() {
			return val + (next == null ? "" : "," + next.toString());
		}
	}
	
	public ListNode swapPairs(ListNode head) {
        return swapPairNodes(head);
    }
    
    private ListNode swapPairNodes(ListNode node)
    {
    	if(node.next == null)
        {
        	return node;
        }
    	
    	ListNode temp = node.next;
    	node.next = temp.next;
    	temp.next = node;
    	node = temp;
    	
    	if(node.next.next != null)
    	{
    		node.next.next = swapPairNodes(node.next.next);
    	}
    	
    	return node;
    }
    
    public ListNode reverseList(ListNode head) {
    	return reverse(head);
    }
    
    private ListNode reverse(ListNode head) {
    	if(head == null || head.next == null)
    	{
    		return head;
    	}
    	
    	ListNode newHead = reverse(head.next);
    	head.next.next = head; 
        head.next = null; 
        
        return newHead;
    }

	public static void main(String[] args) {
		LinkedListTest test = new LinkedListTest();
		ListNode head = test.buildTestData(new int[]{1,2,3,4});
//		head = test.swapPairs(head);
//		System.out.println(head);
		System.out.println(test.reverseList(head));
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
