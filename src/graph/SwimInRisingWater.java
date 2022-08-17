package graph;

import java.util.PriorityQueue;

public class SwimInRisingWater {

    public int swimInWater(int[][] grid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        pq.offer(new int[]{grid[0][0], 0, 0});
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            int[] data = pq.poll();

            int acc = data[0], row = data[1], col = data[2];
            if (row == n - 1 && col == n - 1) return acc;

            for (int i = 0; i < dirs.length; i++) {
                int r = row + dirs[i][0], c = col + dirs[i][1];
                if (r >= 0 && c >= 0 && r < n && c < n && !visited[r][c]) {
                    pq.offer(new int[]{Math.max(acc, grid[r][c]), r, c});
                    visited[r][c] = true;
                }
            }

        }
        return -1;
    }
}
