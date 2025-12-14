class Solution {
    public int solveUsingRecursion(int[][] grid, int n, int m, int i, int j) {
        if (i == n - 1 && j == m - 1) {
            return grid[n - 1][m - 1];
        }

        if (i >= n || j >= m) {
            return Integer.MAX_VALUE;
        }

        int right = solveUsingRecursion(grid, n, m, i + 1, j);
        int down = solveUsingRecursion(grid, n, m, i, j + 1);

        return grid[i][j] + Math.min(right, down);
    }

    public int solveUsingMemo(int[][] grid, int n, int m, int i, int j, int[][] dp) {
        if (i == n - 1 && j == m - 1) {
            return grid[i][j];
        }

        if (i >= n || j >= m) {
            return Integer.MAX_VALUE;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int right = solveUsingMemo(grid, n, m, i, j + 1, dp);
        int down = solveUsingMemo(grid, n, m, i + 1, j, dp);

        dp[i][j] = grid[i][j] + Math.min(right, down);
        return dp[i][j];
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        return solveUsingRecursion(grid, n, m, 0, 0);
    }
}