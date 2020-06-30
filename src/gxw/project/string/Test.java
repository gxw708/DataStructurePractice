package gxw.project.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test {
	static class DMO {
		StorageLocator sl;
		
		DMO(StorageLocator sl) {
			this.sl = sl;
		}
	}
	
	static class StorageLocator {
		String fsid;
		StorageLocator(String fsid) {
			this.fsid = fsid;
		}
	}
	
	
	public static void main(String args[]) {
//		DMO dmo = new DMO(new StorageLocator("fsid1"));
//		test(dmo.sl);
//		System.out.println(dmo.sl.fsid);
//		
//		System.out.println(String.format("%s is null", Boolean.FALSE ? "connection name" : "provider name"));
		
		Queue<Integer> queue = new PriorityQueue<Integer>((e1, e2) -> e1-e2);
		queue.offer(5);
		queue.offer(4);
		queue.offer(3);
		System.out.println(Arrays.toString(findDisappearedNumbers(new int[]{2, 2}).toArray()));
		
		System.out.println(Test.decode("abc"));
		System.out.println(Test.decode("abu#c"));
		
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("world");
		
		if(!list.stream().anyMatch(s -> s.equals("world")))
			System.out.println("xx is not found");
	}
	
	private static String decode(String s)
    {
        StringBuffer sb = new StringBuffer();
        for(char c : s.toCharArray())
        {
            if(c == '#')
            {
            	if(sb.length() > 0)
            	{
            		sb.deleteCharAt(sb.length()-1);
            	}
            }
            else
            {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
	
	public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++)
        {
        	if(nums[i]-1 > 0)
        		nums[nums[i]-1] = -1;
        }
        
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i] > 0)
                ret.add(i+1);
        }
        
        return ret;
    }
	
	private static void test(StorageLocator sl) {
		StorageLocator nsl = sl;
		DMO dmo1 = new DMO(new StorageLocator("fsid2"));
		nsl = dmo1.sl;
		System.out.println(nsl.fsid);
	}
}
