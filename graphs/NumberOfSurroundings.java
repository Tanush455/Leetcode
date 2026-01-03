class Solution {
    public void dfs(int[][] grid, int m, int n, int row, int col) {
        if (row >= m || col >= n || row < 0 || col < 0 || grid[row][col] == 0) {
            return;
        }

        grid[row][col] = 0; // Mark visited
        dfs(grid, m, n, row + 1, col); // Down
        dfs(grid, m, n, row, col + 1); // Right
        dfs(grid, m, n, row - 1, col); // Up
        dfs(grid, m, n, row, col - 1); // Left
    }

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Eliminate boundary-connected 1s
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1) {
                dfs(grid, m, n, i, 0);
            }
            if (grid[i][n - 1] == 1) {
                dfs(grid, m, n, i, n - 1);
            }
        }

        for (int j = 0; j < n; j++) {
            if (grid[0][j] == 1) {
                dfs(grid, m, n, 0, j);
            }
            if (grid[m - 1][j] == 1) {
                dfs(grid, m, n, m - 1, j);
            }
        }

        // Count remaining 1s (enclaves)
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}