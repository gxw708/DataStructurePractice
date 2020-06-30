package leetcode.execise.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyCircularQueue {
    private enum Status{EMPTY, NORMAL, FULL};
    int size = -1;
    int start = -1;
    int end = -1;
    Status status = null;
    Integer[] array = null;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        size = k;
        array = new Integer[size];
        status = Status.EMPTY;
    }

    /**
     * Insert an element into the circular queue. Return true if the operation
     * is successful.
     */
    public boolean enQueue(int value) {
        if (isFull())
            return false;
        else {
            if (isEmpty())
                start = 0;
            end = end == size - 1 ? 0 : end + 1;
            array[end] = value;
            status = getStatus();
            return true;
        }
    }

    /**
     * Delete an element from the circular queue. Return true if the operation
     * is successful.
     */
    public boolean deQueue() {
        if (isEmpty())
            return false;
        else {
            array[start] = null;
            if(start == end)
            {
                start = -1;
                end = -1;
            }
            else
                start = start == size - 1 ? 0 : start + 1;
            status = getStatus();
            return true;
        }
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty())
            return -1;
        else {
            return array[start];
        }
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty())
            return -1;
        else {
            return array[end];
        }
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return Status.EMPTY.equals(status);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return Status.FULL.equals(status);
    }
    
    private Status getStatus() {
        if(start == -1 || end == -1)
            return Status.EMPTY;
        else if(end == start - 1 ||
                end - start == size - 1)
            return Status.FULL;
        else
            return Status.NORMAL;
    }
    
    public String toString()
    {
        return String.format("Queue[%d]: %s", size, Arrays.toString(array));
    }

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(2);
        queue.enQueue(4);
        System.out.println("Rear: " + queue.Rear());
        queue.enQueue(9);
        queue.deQueue();
        System.out.println("Front: " + queue.Front());
        queue.deQueue();
        queue.deQueue();
        System.out.println("isEmpty: " + queue.isEmpty());
        queue.deQueue();
        queue.enQueue(6);
        queue.enQueue(4);
        
        Random random = new Random();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        ArrayList<Integer> list = new ArrayList(map.keySet());
        System.out.println(map.get(list.get(random.nextInt(map.keySet().size()))));
    }
    
    private static String getKey(final String s)
    {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    enum Color{RED, YELLO};
}
