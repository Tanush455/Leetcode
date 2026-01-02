class Pair {
    int first;
    int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int initialColor = image[sr][sc];
        if (initialColor == color)
            return image;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(sr, sc));
        image[sr][sc] = color;

        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { -1, 1, 0, 0 };

        while (!q.isEmpty()) {
            Pair frontNode = q.poll();
            int row = frontNode.first;
            int col = frontNode.second;

            for (int i = 0; i < 4; i++) {
                int newRow = row + dx[i];
                int newCol = col + dy[i];

                if (newRow >= 0 && newRow < image.length && newCol >= 0 && newCol < image[0].length
                        && image[newRow][newCol] == initialColor) {
                    image[newRow][newCol] = color;
                    q.add(new Pair(newRow, newCol));
                }
            }
        }

        return image;
    }
}