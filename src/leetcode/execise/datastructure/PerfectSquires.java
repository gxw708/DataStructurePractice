package leetcode.execise.datastructure;

import java.util.Arrays;

public class PerfectSquires {
    int[] isPsn;

    public int numSquares(int n) {
        isPsn = new int[n + 1];
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (isPSN(i - j)) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private boolean isPSN(int num) {
        if (isPsn[num] != 0)
            return isPsn[num] == 1 ? true : false;
        else {
            if (Math.sqrt(num) % 1.0 == 0.0) {
                isPsn[num] = 1;
                return true;
            } else {
                isPsn[num] = 2;
                return false;
            }
        }
    }
    
    public static void main(String[] args)
    {
        PerfectSquires ps = new PerfectSquires();
        ps.numSquares(0);
    }
}
