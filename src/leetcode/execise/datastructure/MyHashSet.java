package leetcode.execise.datastructure;

import java.util.LinkedList;

public class MyHashSet {
    LinkedList<Integer>[] array = null;
    /** Initialize your data structure here. */
    public MyHashSet() {
        array = new LinkedList[1000];
    }
    
    public void add(int key) {
        if(!contains(key))
        {
            if(array[hash(key)] == null)
            {
                array[hash(key)] = new LinkedList<Integer>();
            }
            array[hash(key)].offer(key);
        }
    }
    
    public void remove(int key) {
        if(contains(key))
        {
            LinkedList<Integer> bucket = array[hash(key)];
            bucket.remove(Integer.valueOf(key));
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        LinkedList<Integer> bucket = array[hash(key)];
        return bucket == null ? false : bucket.contains(key);
    }
    
    private int hash(int val)
    {
        return val % 1000;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyHashSet obj = new MyHashSet();
        obj.add(1);
        obj.add(2);
        System.out.println("contains 2 ? " + obj.contains(2));
        obj.remove(2);
        System.out.println("contains 2 ? " + obj.contains(2));
    }

}
