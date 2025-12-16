import java.util.Arrays;

class Solution {

    public int solveRecursion(int[] coins, int amount, int n, int sum) {

        if (sum > amount) {
            return Integer.MAX_VALUE;
        }

        if (sum == amount) {
            return 0;
        }

        int minCoins = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int result = solveRecursion(coins, amount, n, sum + coins[i]);
            if (result != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, 1 + result);
            }
        }

        return minCoins;
    }

    public int solveUsingMemo(int[] dp, int[] coins, int amount) {

        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[amount] != -1) {
            return dp[amount];
        }

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            int result = solveUsingMemo(dp, coins, amount - coin);
            if (result != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, 1 + result);
            }
        }

        dp[amount] = minCoins;
        return dp[amount];
    }

    public int solveUsingTabulation(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int value = 1; value <= amount; value++) {
            for (int coin : coins) {
                if (value - coin >= 0 && dp[value - coin] != Integer.MAX_VALUE) {
                    dp[value] = Math.min(dp[value], 1 + dp[value - coin]);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;

        if (amount == 0) {
            return 0;
        }

        if (coins == null || n == 0) {
            return -1;
        }

        // Solve using Recursion
        // int result = solveRecursion(coins, amount, n, 0);
        // return result == Integer.MAX_VALUE ? -1 : result;

        // Solve using Memoization
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int result = solveUsingMemo(dp, coins, amount);

        // Solve using Tabulation
        // int result = solveUsingTabulation(coins, amount);

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}