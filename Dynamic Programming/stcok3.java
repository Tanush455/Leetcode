class Solution {
    // Memoization (Top-Down DP) approach.
    // 'buy' indicates whether we are allowed to buy (true) or we need to sell
    // (false).
    // 'cap' is the remaining number of transactions allowed.
    public int solveUsingMemo(int[] prices, int index, boolean buy, int cap, int[][][] dp) {
        if (cap == 0 || index >= prices.length)
            return 0;

        // Convert the boolean to an index: 1 for true (allowed to buy) and 0 for false
        // (need to sell)
        int buyIndex = buy ? 1 : 0;

        // If the state is already solved, return the answer.
        if (dp[index][buyIndex][cap] != -1)
            return dp[index][buyIndex][cap];

        if (buy) {
            // Two choices:
            // 1. Buy at the current index: subtract prices[index] and switch to selling
            // state.
            // 2. Do nothing: remain in the buying state.
            dp[index][buyIndex][cap] = Math.max(
                    -prices[index] + solveUsingMemo(prices, index + 1, false, cap, dp),
                    solveUsingMemo(prices, index + 1, true, cap, dp));
        } else {
            // Two choices:
            // 1. Sell at the current index: add prices[index], complete one transaction,
            // and switch to buying state.
            // 2. Do nothing: remain in the selling state.
            dp[index][buyIndex][cap] = Math.max(
                    prices[index] + solveUsingMemo(prices, index + 1, true, cap - 1, dp),
                    solveUsingMemo(prices, index + 1, false, cap, dp));
        }

        return dp[index][buyIndex][cap];
    }

    // Tabulation (Bottom-Up DP) approach.
    public int solveUsingTabulation(int[] prices) {
        int n = prices.length;
        int capLimit = 2; // Maximum number of allowed transactions.
        // dp[i][buy][cap] represents the maximum profit from index i with state 'buy'
        // and remaining transactions 'cap'
        int[][][] dp = new int[n + 1][2][capLimit + 1];

        // Base Cases:
        // 1. When index == n, no further profit can be made, so dp[n][buy][cap] remains
        // 0 (default value).
        // 2. When cap == 0, no transactions left, so profit is 0. (Already 0 by
        // default.)

        // Fill the table in reverse order
        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy < 2; buy++) {
                for (int cap = 1; cap <= capLimit; cap++) {
                    if (buy == 1) { // Allowed to buy.
                        dp[i][buy][cap] = Math.max(
                                -prices[i] + dp[i + 1][0][cap], // Option to buy.
                                dp[i + 1][1][cap] // Option to skip.
                        );
                    } else { // Must sell.
                        dp[i][buy][cap] = Math.max(
                                prices[i] + dp[i + 1][1][cap - 1], // Option to sell.
                                dp[i + 1][0][cap] // Option to skip.
                        );
                    }
                }
            }
        }
        // Starting at index 0, with the ability to buy (buy = 1) and 2 transactions
        // allowed.
        return dp[0][1][capLimit];
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Initialize memoization table: dimensions are [n][2][3]
        int[][][] dp = new int[n][2][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // You can use either the memoization approach or the tabulation approach.
        int memoResult = solveUsingMemo(prices, 0, true, 2, dp);
        int tabResult = solveUsingTabulation(prices);

        // Both results should be the same; here we return the memoization result.
        return tabResult; // Alternatively, return tabResult;
    }
}