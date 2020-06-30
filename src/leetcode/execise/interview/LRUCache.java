package leetcode.execise.interview;

import java.util.ArrayDeque;
import java.util.HashMap;

class LRUCache {
    private int capacity;
    private ArrayDeque<Integer> usedOrderArr;
    private HashMap<Integer, Integer> map;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.usedOrderArr = new ArrayDeque<Integer>(capacity);
        this.map = new HashMap<Integer, Integer>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key))
        	return -1;
        
    	getEvictElement(key);
    	return map.get(key);
    }
    
    public void put(int key, int value) {
		map.remove(getEvictElement(key));
        map.put(key, value);
    }
    
    private Integer getEvictElement(int key)
    {
    	Integer evictElement = null;
    	if(map.containsKey(key))
    	{
    		usedOrderArr.remove(key);
    		usedOrderArr.offerLast(key);
    	}
    	else
    	{
    		if(this.capacity == this.usedOrderArr.size())
            {
        		evictElement = usedOrderArr.pollFirst();
            }
        	usedOrderArr.offerLast(key);
    	}
    	
    	return evictElement;
    }
    
    public static void main(String[] args)
    {
    	LRUCache cache = new LRUCache(2);
    	System.out.println(cache.get(2));
    	cache.put(2, 6);
    	System.out.println(cache.get(1));
    	cache.put(1, 5);
    	cache.put(1, 2);
    	System.out.println(cache.get(1));
    	System.out.println(cache.get(2));
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
