package leetcode.execise.datastructure;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MyQueueStack {
    Queue<Integer> queue = null;
    /** Initialize your data structure here. */
    public MyQueueStack() {
        queue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if(empty())
            queue.offer(x);
        else
        {
            Queue<Integer> temp = new LinkedList<Integer>();
            while(!queue.isEmpty())
            {
                temp.offer(queue.poll());
            }
            queue.offer(x);
            while(!temp.isEmpty())
            {
                queue.offer(temp.poll());
            }
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
    
    public static void main(String[] args)
    {
        MyQueueStack stack = new MyQueueStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top());
        
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((e1, e2) -> e2-e1);
    }
}
