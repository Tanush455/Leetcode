// # House Robber (Java)


// A small repository that demonstrates multiple approaches to solve the **House Robber** dynamic programming problem.


// ## Problem
// You are a robber planning to steal from houses along a street. Each house has a certain amount of money. Adjacent houses have security systems connected, so you cannot rob two adjacent houses. Given an array `nums` of non-negative integers representing the amount of money at each house, return the maximum amount you can rob.


// ## Files
// - `Solution.java` — contains implementations and explanations for:
// - Recursive solution
// - Memoized (top-down) solution
// - Tabulation (bottom-up) solution
// - Space-optimized DP solution (recommended)


// ## How to run
// 1. Make sure you have JDK installed (Java 8+). Check with `java -version`.
// 2. Compile: `javac Solution.java`
// 3. Run: `java Solution`

// ## Complexity Summary
// - Recursive (naive): Time O(2^n), Space O(n)
// - Memoization: Time O(n), Space O(n)
// - Tabulation: Time O(n), Space O(n)
// - Space-optimized: Time O(n), Space O(1)


// ## Notes
// - Works for `n >= 0`.
// - For very large sums, consider `long` to avoid integer overflow.


// ## License
// MIT


// ## Contributing
// PRs adding tests (e.g., JUnit), performance variants, or conversions to `long`/`BigInteger` are welcome.

class Solution {

    // Solve using Recursion
    public int solveUsingRecursion(int[] nums, int index, int n) {
        if (index >= n) {
            return 0;
        }

        int house1 = nums[index] + solveUsingRecursion(nums, index + 2, n); // Rob the current house
        int house2 = solveUsingRecursion(nums, index + 1, n);              // Skip the current house

        return Math.max(house1, house2);
    }

    // Solve using Memoization
    public int solveUsingMemo(int[] nums, int index, int[] dp) {
        if (index >= nums.length) {
            return 0;
        }

        if (dp[index] != -1) {
            return dp[index];
        }

        int house1 = nums[index] + solveUsingMemo(nums, index + 2, dp); // Rob the current house
        int house2 = solveUsingMemo(nums, index + 1, dp);              // Skip the current house

        dp[index] = Math.max(house1, house2);
        return dp[index];
    }

    // Solve using Tabulation (Bottom-Up Dynamic Programming)
    public int solveUsingTabulation(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }

    // Solve using Space Optimization (Iterative with Two Variables)
    public int solveUsingSpaceOptimized(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int prev2 = 0; // dp[i-2]
        int prev1 = nums[0]; // dp[i-1]

        for (int i = 1; i < n; i++) {
            int curr = Math.max(prev1, nums[i] + prev2);
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }

    // Wrapper method to test different approaches
    public int rob(int[] nums) {
        int n = nums.length;

        // Recursive Approach
        // return solveUsingRecursion(nums, 0, n);

        // Memoization Approach
        // int[] dp = new int[n];
        // Arrays.fill(dp, -1);
        // return solveUsingMemo(nums, 0, dp);

        // Tabulation Approach
        // return solveUsingTabulation(nums);

        // Space Optimization Approach
        return solveUsingSpaceOptimized(nums);
    }
}
