class Solution {
    public int solveUsingSpaceOptimization(int[] prices, int n) {
        // Variables to store the next state's values
        int nextBuy = 0; // dp[i + 1][1]
        int nextNotBuy = 0; // dp[i + 1][0]

        // Iterate from the last day to the first day
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the current day's values for buy and not buy
            int currentBuy = Math.max(
                    -prices[i] + nextNotBuy, // Buy and move to next state
                    nextBuy // Skip buying
            );
            int currentNotBuy = Math.max(
                    prices[i] + nextBuy, // Sell and move to next state
                    nextNotBuy // Skip selling
            );

            // Update the next state variables
            nextBuy = currentBuy;
            nextNotBuy = currentNotBuy;
        }

        return nextBuy; // Starting with the option to buy
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // Use space-optimized solution
        return solveUsingSpaceOptimization(prices, n);
    }
}