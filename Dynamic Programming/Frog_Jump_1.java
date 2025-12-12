/**
 * Solution.java
 *
 * Multiple approaches to the Frog Jump Minimum Energy problem:
 * - Pure recursion (exponential time)
 * - Memoization (top-down dynamic programming)
 * - Tabulation (bottom-up dynamic programming)
 * - Space-optimized DP (reduces space to O(1))
 *
 * Problem (short):
 * You are given an array 'height' where height[i] represents the height of stone i.
 * A frog starts at index 0 and wants to reach the last index.
 * The frog may jump either:
 *   - from i to i+1, or
 *   - from i to i+2.
 *
 * The cost of jumping from i to j is:
 *      abs(height[i] - height[j])
 *
 * Goal:
 * Find the minimum total energy required for the frog to reach the last stone.
 */


import java.util.Arrays;

class Solution {
    public int solveUsingRecursion(int[] height, int index) {
        if (index >= height.length) {
            return 0;
        }
        if (index == height.length - 1) {
            return 0;
        }

        int oneStepCost = Integer.MAX_VALUE;
        if (index + 1 < height.length) {
            oneStepCost = Math.abs(height[index] - height[index + 1]) + 
                         solveUsingRecursion(height, index + 1);
        }

        int twoStepCost = Integer.MAX_VALUE;
        if (index + 2 < height.length) {
            twoStepCost = Math.abs(height[index] - height[index + 2]) + 
                         solveUsingRecursion(height, index + 2);
        }
        
        return Math.min(oneStepCost, twoStepCost);
    }
    
    public int solveUsingMemo(int[] height, int[] dp, int index) {
        if (index >= height.length) {
            return 0;
        }
        if (index == height.length - 1) {
            return 0;
        }
        
        if (dp[index] != -1) {
            return dp[index];
        }
        
        int oneStepCost = Integer.MAX_VALUE;
        if (index + 1 < height.length) {
            oneStepCost = Math.abs(height[index] - height[index + 1]) + 
                         solveUsingMemo(height, dp, index + 1);
        }

        int twoStepCost = Integer.MAX_VALUE;
        if (index + 2 < height.length) {
            twoStepCost = Math.abs(height[index] - height[index + 2]) + 
                         solveUsingMemo(height, dp, index + 2);
        }
        
        return dp[index] = Math.min(oneStepCost, twoStepCost);
    }
    
    public int solveUsingTabulation(int[] height) {
        int n = height.length;
        if (n <= 1) return 0;
        
        int[] dp = new int[n];
        dp[n - 1] = 0; 
        
        for (int i = n - 2; i >= 0; i--) {
            int oneStepCost = Integer.MAX_VALUE;
            if (i + 1 < n) {
                oneStepCost = Math.abs(height[i] - height[i + 1]) + dp[i + 1];
            }

            int twoStepCost = Integer.MAX_VALUE;
            if (i + 2 < n) {
                twoStepCost = Math.abs(height[i] - height[i + 2]) + dp[i + 2];
            }
            
            dp[i] = Math.min(oneStepCost, twoStepCost);
        }
        // Return MIN of starting from index 0 or 1
        return Math.min(dp[0], dp[1]);
    }
    
    // CORRECTED Space Optimization (O(1) space)
    public int solveUsingSpaceOptimization(int[] height) {
        int n = height.length;
        if (n <= 1) return 0;
        
        int prev2 = 0;  // dp[i+2]
        int prev1 = 0;  // dp[i+1]  
        int curr = 0;   // dp[i]
        
        for (int i = n - 2; i >= 0; i--) {
            int oneStepCost = Integer.MAX_VALUE;
            if (i + 1 < n) {
                oneStepCost = Math.abs(height[i] - height[i + 1]) + prev1;
            }

            int twoStepCost = Integer.MAX_VALUE;
            if (i + 2 < n) {
                twoStepCost = Math.abs(height[i] - height[i + 2]) + prev2;
            }
            
            curr = Math.min(oneStepCost, twoStepCost);
            prev2 = prev1;
            prev1 = curr;
        }
        return Math.min(prev1, curr); // min(dp[1], dp[0])
    }

    int minCost(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        return solveUsingSpaceOptimization(height);
    }
}
