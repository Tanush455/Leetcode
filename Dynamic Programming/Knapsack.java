
// User function Template for Java

class Solution {
    public static int solveUsingTabulation(int[] val, int[] wt, int capacity) {
        int n = val.length;
        int[] dp = new int[capacity + 1];

        // Fill the DP array
        for (int i = 0; i < n; i++) { // Iterate over all items
            for (int w = wt[i]; w <= capacity; w++) { // Start from wt[i] to avoid unnecessary checks
                dp[w] = Math.max(dp[w], val[i] + dp[w - wt[i]]);
            }
        }
        return dp[capacity];
    }

    static int knapSack(int[] val, int[] wt, int capacity) {
        return solveUsingTabulation(val, wt, capacity);
    }

}