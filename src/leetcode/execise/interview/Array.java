package leetcode.execise.interview;

import java.util.Arrays;

public class Array {
    public int maxProfit(int[] prices) {
    	int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
    
	public static void main(StringTest[] args) {
		Array test = new Array();
		int[] nums = new int[]{1,2,3,4,5,6,7};
		System.out.println(Arrays.toString(nums));
	}

}
