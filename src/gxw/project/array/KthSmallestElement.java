package gxw.project.array;

import java.util.PriorityQueue;

public class KthSmallestElement
{
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private final int[] data;
    
    public KthSmallestElement(int[] array)
    {
        if(array == null)
        {
            throw new IllegalArgumentException("array is null");
        }
        
        data = array;
    }
    
    public int query(int startPos, int endPos, int k)
    {
        if(endPos <= startPos)
        {
            throw new IllegalArgumentException("start pos must be larger than end pos");
        }
        
        int[] subArr = new int[endPos - startPos + 1];
        System.arraycopy(data, startPos - 1, subArr, 0, endPos - startPos + 1);
        for(int i : subArr)
        {
            minHeap.add(i);
        }
        
        int smallestElement = 0;
        for(int i=0; i<k; i++)
        {
            smallestElement = minHeap.remove();
        }
        
        return smallestElement;
    }
    
    public static void main(String[] args)
    {
        KthSmallestElement test = new KthSmallestElement(new int[]{2, 3, 4, 1, 6, 5, 8});
        System.out.println(test.query(1, 5, 2));
    }
}
