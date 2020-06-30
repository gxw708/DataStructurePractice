package gxw.project.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MedianNumber
{
    List<Integer> data = new ArrayList<Integer>(MAX_SIZE);
    private PriorityQueue<Integer> smallerHalf = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> largerHalf = new PriorityQueue<>();
    private int size = 0;
    private static int MAX_SIZE = 100;

    public MedianNumber addNumber(final int num)
    {
        if(size >= MAX_SIZE)
        {
            throw new RuntimeException("Full");
        }
        
        Integer iNum = num;
        
        Integer maxInSmaller = smallerHalf.peek() == null ? iNum : smallerHalf.peek();
        Integer minInLarger = largerHalf.peek() == null ? iNum : largerHalf.peek();
        
        if(iNum <= minInLarger)
        {
            smallerHalf.add(iNum);
            if(iNum > maxInSmaller)
            {
                largerHalf.add(smallerHalf.remove());
            }
        } else
        {
            largerHalf.add(iNum);
            if(iNum < minInLarger)
            {
                smallerHalf.add(largerHalf.remove());
            }
        }
        
        if(Math.abs(smallerHalf.size() - largerHalf.size()) > 1)
        {
            if(smallerHalf.size() > largerHalf.size())
            {
                largerHalf.add(smallerHalf.remove());
            } else
            {
                smallerHalf.add(largerHalf.remove());
            }
        }
        
        size++;
        return this;
    }

    public double getMedian()
    {
        if(smallerHalf.size() == largerHalf.size())
        {
            return (smallerHalf.peek()+largerHalf.peek())/2;
        } else if(smallerHalf.size() > largerHalf.size())
        {
            return smallerHalf.peek();
        } else 
        {
            return largerHalf.peek();
        }
    }

    public static void main(String[] args)
    {
        MedianNumber m = new MedianNumber();
        System.out.println(
                m.addNumber(4)
                .addNumber(4)
                .addNumber(5)
                .addNumber(10)
                .addNumber(5)
                .addNumber(7)
                .addNumber(9)
                .getMedian());
    }
}
