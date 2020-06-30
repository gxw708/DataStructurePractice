package leetcode.execise.datastructure;

class MyLinkedList {
    public class LinkedListNode
    {
        LinkedListNode prev = null;
        LinkedListNode next = null;
        int val = -1;
        
        public LinkedListNode(int val)
        {
            this.val = val;
        }
        
        public String toString()
        {
            return String.format("%d > %s", val, next);
        }
    }
    
    LinkedListNode head = null;
    LinkedListNode tail = null;
    int size = 0;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(size == 0)
            return -1;
        
        LinkedListNode exist = getNode(index);
        if(exist != null)
            return exist.val;
        else
            return -1;
    }
    
    private LinkedListNode getNode(int index)
    {
        if(index > size - 1)
            return null;
        
        if(index == 0)
            return head;
        else if(index == size-1)
            return tail;
        
        if(index > size/2)
        {
            int pos = size;
            LinkedListNode curr = tail;
            while(--pos != index)
            {
                curr = curr.prev;
            }
            
            return curr;
        }
        else
        {
            int pos = -1;
            LinkedListNode curr = head;
            while(++pos != index)
            {
                curr = curr.next;
            }
            
            return curr;
        }
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        LinkedListNode insert = new LinkedListNode(val);
        if(head == null)
        {
            head = insert;
            tail = head;
        }
        else
        {
            head.prev = insert;
            insert.next = head;
            head = insert;
        }
        size++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        LinkedListNode insert = new LinkedListNode(val);
        if(head == null)
        {
            head = insert;
            tail = head;
        }
        else
        {
            tail.next = insert;
            insert.prev = tail;
            tail = insert;
        }
        size++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size || size < 0)
            return;
        
        if(index == 0)
            addAtHead(val);
        else if(index == size)
            addAtTail(val);
        else
        {
            LinkedListNode insert = new LinkedListNode(val);
            if(index == size)
            {
                tail.next = insert;
                insert.prev = tail;
                tail = insert;
            }
            else{
                LinkedListNode exist = getNode(index);
                exist.prev.next = insert;
                insert.prev = exist.prev;
                insert.next = exist;
                exist.prev = insert;
            }
            size++;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        LinkedListNode exist = getNode(index);
        if(exist != null)
        {
            if(head == exist)
                head = exist.next;
            else if(tail == exist)
            {
                tail = exist.prev;
                tail.next = null;
            }
            else
            {
                exist.prev.next = exist.next;
                if(exist.next != null)
                    exist.next.prev = exist.prev;
                
            }
            size--;
        }
    }
    
    public static void main(String[] args)
    {
        MyLinkedList linkedList = new MyLinkedList(); // Initialize empty LinkedList
        linkedList.addAtIndex(0, 10);
        linkedList.addAtIndex(0, 20);
        linkedList.addAtIndex(1, 30);
        System.out.println(linkedList.get(0));
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
