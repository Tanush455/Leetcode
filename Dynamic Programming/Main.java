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




// Bottom up approach
public class Solution {
    public int matrixMultiplication(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        // Initialize the dp array with large values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // A single matrix doesn't require any multiplication
        for (int i = 1; i < n; i++) {
            dp[i][i] = 0;
        }

        // Filling the dp array in a bottom-up manner
        for (int length = 2; length < n; length++) {
            for (int i = 1; i <= n - length; i++) {
                int j = i + length - 1;
                for (int k = i; k < j; k++) {
                    // Calculate cost
                    int cost = dp[i][k] + dp[k + 1][j] + nums[i - 1] * nums[k] * nums[j];
                    // Take the minimum cost
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                    }
                }
            }
        }

        // The result is in dp[1][n-1] (multiplying from matrix 1 to n-1)
        return dp[1][n - 1];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {10, 15, 20, 25};

        // Output should be 8000
        System.out.println("Minimum number of multiplications: " + sol.matrixMultiplication(nums));
    }
}
