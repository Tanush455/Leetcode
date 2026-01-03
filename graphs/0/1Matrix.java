import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> q = new LinkedList<>();
        int[][] vis = new int[rows][cols];
        int[][] ans = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0) {
                    q.add(new int[] { i, j, 0 });
                    vis[i][j] = 1;
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            int[] frontNode = q.poll();

            int row = frontNode[0];
            int col = frontNode[1];
            int dist = frontNode[2];
            ans[row][col] = dist;

            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && vis[newRow][newCol] == 0) {
                    vis[newRow][newCol] = 1;
                    q.add(new int[] { newRow, newCol, dist + 1 });
                }
            }
        }

        return ans;
    }
}