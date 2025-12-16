

class Solution {
    
    public static Boolean solveUsingRecursion(int[] arr, int sum, int index, int total, int n) {
        if (total == sum) {
            return true;
        }
        if (index >= n || total > sum) {
            return false;
        }
        
        boolean include = solveUsingRecursion(arr, sum, index + 1, total + arr[index], n);
        boolean exclude = solveUsingRecursion(arr, sum, index + 1, total, n);
        
        return include || exclude;
    }
    
    public static Boolean solveUsingMemo(int[] arr, int sum, int index, int total, int n, Boolean[][] dp) {
        if (total == sum) {
            return true;
        }
        if (index >= n || total > sum) {
            return false;
        }
        
        if (dp[index][total] != null) {
            return dp[index][total];
        }
        
        boolean include = solveUsingMemo(arr, sum, index + 1, total + arr[index], n, dp);
        boolean exclude = solveUsingMemo(arr, sum, index + 1, total, n, dp);
        
        return dp[index][total] = include || exclude;
    }
    
    public static Boolean solveUsingTabulation(int[] arr, int sum, int n) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean exclude = dp[i - 1][j];
                boolean include = (j >= arr[i - 1]) ? dp[i - 1][j - arr[i - 1]] : false;
                
                dp[i][j] = include || exclude;
            }
        }
        
        return dp[n][sum];
    }

    public static Boolean solveUsingSpaceOptimization(int[] arr, int sum, int n) {
        boolean[] prev = new boolean[sum + 1];
        boolean[] curr = new boolean[sum + 1];
        
        prev[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                boolean exclude = prev[j];
                boolean include = (j >= arr[i - 1]) ? prev[j - arr[i - 1]] : false;
                
                curr[j] = include || exclude;
            }
            prev = curr.clone();
        }
        
        return prev[sum];
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;

        // Recursion
        // return solveUsingRecursion(arr, sum, 0, 0, n);
        
        // Memoization
        // Boolean[][] dp = new Boolean[n][sum + 1];
        // return solveUsingMemo(arr, sum, 0, 0, n, dp);
        
        // Tabulation
        // return solveUsingTabulation(arr, sum, n);

        // Space Optimization
        return solveUsingSpaceOptimization(arr, sum, n);
    }
}