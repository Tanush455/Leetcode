
// # ClimbStairs (Java)


// A small repository that demonstrates multiple approaches to solve the classic **Climbing Stairs** dynamic programming problem.


// // ## Problem
// // You are climbing a staircase. It takes `n` steps to reach the top. Each time you can either climb `1` or `2` steps. In how many distinct ways can you climb to the top?


// // ## Files
// // - `Solution.java` — contains implementations and explanations for:
// // - Recursive solution
// // - Memoized (top-down) solution
// // - Tabulation (bottom-up) solution
// // - Space-optimized DP solution (recommended)


// // ## How to run
// // 1. Make sure you have JDK installed (Java 8+). Check with `java -version`.
// // 2. Compile: `javac Solution.java`
// // 3. Run: `java Solution`


// // You should see an output like:
// // ## Complexity Summary
// // - Recursive (naive): Time O(2^n), Space O(n)
// // - Memoization: Time O(n), Space O(n)
// // - Tabulation: Time O(n), Space O(n)
// // - Space-optimized: Time O(n), Space O(1)


// // ## Notes
// // - The sequence of answers follows Fibonacci numbers with base values `ways(0) = 1`, `ways(1) = 1`.
// // - For extremely large `n`, results will overflow `int`. Consider using `long` or `BigInteger`.


// // ## License
// // MIT


// // ## Contributing
// // Feel free to open issues or PRs that add more tests, change base cases (e.g., `ways(0) = 0` if you prefer), or extend to k-step climbs.

import java.util.*;
class Solution {
    // Pure Recursion
    public int solveUsingRecursion(int n){
        if(n == 0 || n == 1){
            return 1;
        }
        return solveUsingRecursion(n - 1) + solveUsingRecursion(n - 2);
    }

    // Memoization
    public int solveUsingMemo(int n, int[] dp){
        if(n == 0 || n == 1){
            return 1;
        }
        if(dp[n] != -1){
            return dp[n];
        }
        dp[n] = solveUsingMemo(n - 1, dp) + solveUsingMemo(n - 2, dp);
        return dp[n];
    }

    // Tabulation (Bottom-Up)
    public int solveUsingTabulation(int n){
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Space Optimization
    public int solveUsingSpaceOptimization(int n){
        if(n == 0 || n == 1) return 1;
        int prev = 1; // for (n-1)
        int prev2 = 1; // for (n-2)
        int curr = 0;
        for(int i = 2; i <= n; i++){
            curr = prev + prev2;
            prev2 = prev;
            prev = curr;
        }
        return curr;
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        // Uncomment the desired approach
        // return solveUsingRecursion(n);
        // return solveUsingMemo(n, dp);
        // return solveUsingTabulation(n);
        return solveUsingSpaceOptimization(n);
    }
}

