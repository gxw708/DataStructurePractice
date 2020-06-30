package leetcode.execise.interview;

import java.util.Arrays;

public class SortingTest {

	public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0)
            return;
        
        if(m == 0)
        {
            for(int i=0;i<n;i++)
            {
                nums1[i] = nums2[i];
            }
            return;
        }
        
        int[] all = new int[m+n];
        int idx1 = 0;
        int idx2 = 0;
        int idxa = 0;
        
        while(idx1<m && idx2<n)
        {
        	if(nums1[idx1] <= nums2[idx2])
        	{
        		all[idxa++] = nums1[idx1++];
        	}
        	else
        	{
        		all[idxa++] = nums2[idx2++];
        	}
        }
        
        while(idx1<m)
        {
        	all[idxa++] = nums1[idx1++];
        }
        
        while(idx2<n)
        {
        	all[idxa++] = nums2[idx2++];
        }
        
        for(int i=0;i<all.length;i++)
        {
        	nums1[i] = all[i];
        }
    }
	
	public static void main(String[] args) {
		SortingTest test = new SortingTest();
		int[] nums1 = new int[]{2,0};
		int[] nums2 = new int[]{1};
		
		test.merge(nums1, 1, nums2, 1);
		System.out.println(Arrays.toString(nums1));
	}
}
