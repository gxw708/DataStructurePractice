package leetcode.execise.datastructure;

import java.util.PriorityQueue;
import java.util.Stack;

public class MyStackQueue {
    Stack<Integer> stack;
    /** Initialize your data structure here. */
    public MyStackQueue() {
        stack = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if(empty())
            stack.push(x);
        else
        {
            Stack<Integer> temp = new Stack<Integer>();
            while(!stack.isEmpty())
            {
                temp.push(stack.pop());
            }
            stack.push(x);
            while(!temp.isEmpty())
            {
                stack.push(temp.pop());
            }
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
    
    public static void main(String[] args)
    {
        MyStackQueue queue = new MyStackQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        System.out.println(queue.peek());
        
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((e1, e2) -> e2-e1);
    }
}
