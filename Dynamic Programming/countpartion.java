import java.util.Arrays;

class Solution {
    public int solveUsingRecursion(int[] arr, int index, int target) {
        if (target == 0) {
            return 1;
        }

        if (index >= arr.length || target < 0) {
            return 0;
        }

        int take = 0;
        if (arr[index] <= target) {
            take = solveUsingRecursion(arr, index + 1, target - arr[index]);
        }

        int notTake = solveUsingRecursion(arr, index + 1, target);

        return take + notTake;
    }

    public int solveUsingMemo(int[] arr, int index, int target, Integer[][] dp) {
        if (target == 0) {
            return 1;
        }

        if (index >= arr.length || target < 0) {
            return 0;
        }

        if (dp[index][target] != null) {
            return dp[index][target];
        }

        int take = 0;
        if (arr[index] <= target) {
            take = solveUsingMemo(arr, index + 1, target - arr[index], dp);
        }

        int notTake = solveUsingMemo(arr, index + 1, target, dp);

        dp[index][target] = take + notTake;
        return dp[index][target];
    }

    public int solveUsingTabulation(int[] arr, int target) {
        int n = arr.length;
        int[][] dp = new int[n + 1][target + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int sum = 1; sum <= target; sum++) {
                int take = 0;
                if (arr[i] <= sum) {
                    take = dp[i + 1][sum - arr[i]];
                }

                int notTake = dp[i + 1][sum];
                dp[i][sum] = take + notTake;
            }
        }

        return dp[0][target];
    }

    public int solveUsingSpaceOptimization(int[] arr, int target) {
        int n = arr.length;
        int[] prev = new int[target + 1];
        int[] curr = new int[target + 1];

        prev[0] = 1;
        curr[0] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int sum = 1; sum <= target; sum++) {
                int take = 0;
                if (arr[i] <= sum) {
                    take = prev[sum - arr[i]];
                }

                int notTake = prev[sum];
                curr[sum] = take + notTake;
            }
            prev = curr.clone();
        }

        return prev[target];
    }

    public int solveUsingSpaceOptimizationSingleArray(int[] arr, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 0; i < arr.length; i++) {
            for (int sum = target; sum >= arr[i]; sum--) {
                dp[sum] += dp[sum - arr[i]];
            }
        }

        return dp[target];
    }

    int countPartitions(int[] arr, int d) {
        int sum = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        if ((sum + d) % 2 != 0 || sum < d) {
            return 0;
        }

        int target = (sum + d) / 2;

        return solveUsingSpaceOptimizationSingleArray(arr, target);
    }
}