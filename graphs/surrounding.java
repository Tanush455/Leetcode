class Solution {
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        for (int r = 0; r < n; r++) {
            if (board[r][0] == 'O') {
                dfs(board, r, 0);
            }
            if (board[r][m - 1] == 'O') {
                dfs(board, r, m - 1);
            }
        }
        for (int c = 0; c < m; c++) {
            if (board[0][c] == 'O') {
                dfs(board, 0, c);
            }
            if (board[n - 1][c] == 'O') {
                dfs(board, n - 1, c);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'V') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        if (0 <= i && i < board.length && 0 <= j && j < board[0].length && board[i][j] == 'O' && board[i][j] != 'V') {
            board[i][j] = 'V';
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
    }
}