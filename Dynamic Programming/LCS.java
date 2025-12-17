import java.util.Arrays;

class Solution {

    public int solveUsingMemo(String text1, String text2, int n, int m, int i, int j, int[][] dp) {
        if (i == n || j == m) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            dp[i][j] = 1 + solveUsingMemo(text1, text2, n, m, i + 1, j + 1, dp);
        } else {
            dp[i][j] = Math.max(solveUsingMemo(text1, text2, n, m, i + 1, j, dp),
                    solveUsingMemo(text1, text2, n, m, i, j + 1, dp));
        }

        return dp[i][j];
    }

    public int solveUsingTabulation(String text1, String text2, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) { // Corrected: text2.charAt(j - 1)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // Corrected: assignment operator =
                }
            }
        }
        return dp[n][m];
    }

    public int solveUsingSpaceOptimizedTabulation(String text1, String text2, int n, int m) { // Renamed method
        int[] prev = new int[m + 1];

        for (int i = 1; i <= n; i++) {
            int[] curr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) { // Corrected: text1.charAt(i - 1)
                    curr[j] = 1 + prev[j - 1];
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);
                }
            }
            prev = curr;
        }
        return prev[m];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        // Option 1: Memoization (Top-Down DP)
        // int[][] dpMemo = new int[n + 1][m + 1];
        // for (int[] row : dpMemo) {
        // Arrays.fill(row, -1);
        // }
        // return solveUsingMemo(text1, text2, n, m, 0, 0, dpMemo);

        // Option 2: Tabulation (Bottom-Up DP)
        return solveUsingTabulation(text1, text2, n, m);

        // Option 3: Space Optimized Tabulation (Bottom-Up DP)
        // return solveUsingSpaceOptimizedTabulation(text1, text2, n, m);
    }
}