class Solution {
    public int solveUsingRecursion(int m, int n, int down, int right) {
        // Base Case: Reached the bottom-right corner
        if (down == m - 1 && right == n - 1) {
            return 1;
        }

        // Out-of-Bounds Check
        if (down >= m || right >= n) {
            return 0;
        }

        // Recursive calls for moving right and down
        int rightMove = solveUsingRecursion(m, n, down, right + 1);
        int downMove = solveUsingRecursion(m, n, down + 1, right);

        return rightMove + downMove;
    }

    public int solveUsingMemo(int m, int n, int down, int right, int[][] dp) {
        // Base Case: Reached the bottom-right corner
        if (down == m - 1 && right == n - 1) {
            return 1;
        }

        // Out-of-Bounds Check
        if (down >= m || right >= n) {
            return 0;
        }

        // Check if already computed
        if (dp[down][right] != -1) {
            return dp[down][right];
        }

        // Recursive calls for moving right and down
        int rightMove = solveUsingMemo(m, n, down, right + 1, dp);
        int downMove = solveUsingMemo(m, n, down + 1, right, dp);

        // Store the result in dp table
        return dp[down][right] = rightMove + downMove;
    }

    public int solveUsingTabulation(int m, int n) {
        // Create a DP table
        int[][] dp = new int[m][n];

        // Base case: The bottom-right cell has only one way (itself)
        dp[m - 1][n - 1] = 1;

        // Fill the table bottom-up
        for (int row = m - 1; row >= 0; row--) {
            for (int col = n - 1; col >= 0; col--) {
                if (row == m - 1 && col == n - 1) {
                    continue; // Skip the bottom-right cell
                }

                // Add paths from the right and below cells
                int rightMove = (col + 1 < n) ? dp[row][col + 1] : 0;
                int downMove = (row + 1 < m) ? dp[row + 1][col] : 0;

                dp[row][col] = rightMove + downMove;
            }
        }

        // The result is stored in the top-left cell
        return dp[0][0];
    }

    public int solveUsingTabulationSO(int m, int n) {

        int[] curr = new int[n];

        for (int i = 0; i < n; i++) {
            curr[i] = 1;
        }

        // Fill the table bottom-up
        for (int row = m - 2; row >= 0; row--) {

            for (int col = n - 2; col >= 0; col--) {
                curr[col] = curr[col] + curr[col + 1];
            }
        }

        // The result is stored in the top-left cell
        return curr[0];
    }

    public int uniquePaths(int m, int n) {
        // Use Tabulation
        // return solveUsingTabulation(m, n);
        return solveUsingTabulationSO(m, n);

        // Uncomment the following line to use memoization:
        // int[][] dp = new int[m][n];
        // for (int[] row : dp) {
        // Arrays.fill(row, -1);
        // }
        // return solveUsingMemo(m, n, 0, 0, dp);

        // Uncomment the following line to use plain recursion:
        // return solveUsingRecursion(m, n, 0, 0);
    }
}