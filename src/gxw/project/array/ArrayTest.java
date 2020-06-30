package gxw.project.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest
{
    private final int[] data;
    
    public ArrayTest(int[] array)
    {
        if(array == null)
        {
            throw new IllegalArgumentException("array is null");
        }
        
        data = array;
    }
    
    public String findInversions()
    {
        int count = 0;
        List<String> inversions = new ArrayList<String>();
        for(int i=0;i<data.length;i++)
        {
            for(int j=i+1;j<data.length;j++)
            {
                if(data[i] > data[j])
                {
                    count++;
                    inversions.add(String.format("(%d,%d)", data[i], data[j]));
                }
            }
        }
        
        return String.format("Given array has %d inversions %s.", count, String.join(",", inversions));
    }
    
    public static void main(String[] args)
    {
        ArrayTest test = new ArrayTest(new int[]{4,3,2,1});
        System.out.println(test.findInversions());
    }
}
