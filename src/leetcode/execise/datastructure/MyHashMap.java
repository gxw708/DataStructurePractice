package leetcode.execise.datastructure;

import java.util.LinkedList;

public class MyHashMap {
    class Pair
    {
        int key;
        int val;
        
        Pair(int key, int val)
        {
            this.key = key;
            this.val = val;
        }
    }
    LinkedList<Pair>[] array = null;
    /** Initialize your data structure here. */
    public MyHashMap() {
        array = new LinkedList[1000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        array[hash(key)] = array[hash(key)] == null ? new LinkedList<Pair>() : array[hash(key)];
        for(Pair pair : array[hash(key)])
        {
            if(pair.key == key)
            {
                pair.val = value;
                return;
            }
        }
        array[hash(key)].offer(new Pair(key, value));
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if(array[hash(key)] == null)
            return -1;
        else
        {
            for(Pair pair : array[hash(key)])
            {
                if(pair.key == key)
                    return pair.val;
            }
            return -1;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        if(array[hash(key)] != null)
        {
            for(Pair pair : array[hash(key)])
            {
                if(pair.key == key)
                {
                    pair.val = -1;
                    return;
                }
            }
        }
    }
    
    private int hash(int val)
    {
        return val % 1000;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyHashMap obj = new MyHashMap();
        obj.put(1,1);
        obj.put(1001, 1001);
        System.out.println(String.format("%d=%d", 1, obj.get(1)));
        System.out.println(String.format("%d=%d", 1001, obj.get(1001)));
        obj.remove(1001);
        System.out.println(String.format("%d=%d", 1001, obj.get(1001)));
        
        int n = 19;
        while(n>0)
        {
            System.out.println(Math.pow(n%10,2));
            n = (n - n%10)/10;
        }
    }

}
