class Pair {
    int first;
    int second;
    int time;

    Pair(int first, int second, int time) {
        this.first = first;
        this.second = second;
        this.time = time;
    }
}

class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        int n = grid.length;
        int m = grid[0].length;
        int freshOranges = 0;
        int maxTime = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j, 0));
                } else if (grid[i][j] == 1) {
                    freshOranges++;
                }
            }
        }

        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        while (!q.isEmpty()) {
            Pair frontNode = q.poll();
            int row = frontNode.first;
            int col = frontNode.second;
            int timeSpan = frontNode.time;
            maxTime = Math.max(maxTime, timeSpan);

            for (int i = 0; i < 4; i++) {
                int newX = row + dx[i];
                int newY = col + dy[i];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m && grid[newX][newY] == 1) {
                    grid[newX][newY] = 2;
                    freshOranges--;
                    q.add(new Pair(newX, newY, timeSpan + 1));
                    System.out.println(freshOranges);
                }
            }
        }

        return freshOranges > 0 ? -1 : maxTime;
    }
}