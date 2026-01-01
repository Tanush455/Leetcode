import.java.util.*;
class Solution {
    // Recursive function with memoization
    public int solve(int[] arr, int i, int j, int[][] dp) {
        // Base case: only one matrix, no multiplication
        if (i == j) return 0;

        // If already computed, return from dp
        if (dp[i][j] != -1) return dp[i][j];

        int minCost = Integer.MAX_VALUE;

        // Try every partition
        for (int k = i; k < j; k++) {
            int cost1 = solve(arr, i, k, dp);
            int cost2 = solve(arr, k + 1, j, dp);
            int costMultiply = arr[i - 1] * arr[k] * arr[j];

            int total = cost1 + cost2 + costMultiply;
            minCost = Math.min(minCost, total);
        }

        return dp[i][j] = minCost;
    }

    public int matrixChainOrder(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // Initialize dp with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        return solve(arr, 1, n - 1, dp);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {40, 20, 30, 10, 30};
        System.out.println("Minimum number of multiplications is: "
                + sol.matrixChainOrder(arr));
    }
}
