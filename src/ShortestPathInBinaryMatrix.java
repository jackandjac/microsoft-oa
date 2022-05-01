import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    private int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        if(grid[0][0] != 0 || grid[r-1][c-1] != 0) return -1;

        Queue<int[]> queue = new LinkedList<>();
        grid[0][0] =1;
        queue.offer(new int[]{0,0});

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int row = pos[0];
            int col = pos[1];
            int dst = grid[row][col];
            if(row == r-1 && col == c-1) return dst;

            for(int i=0;i<dirs.length;i++){
                int rr = row + dirs[i][0];
                int cc = col + dirs[i][1];


                if(rr>=0 && cc >=0 && rr<r && cc< c && grid[rr][cc] == 0){
                    grid[rr][cc] = grid[row][col] +1;
                    queue.offer(new int[]{rr,cc});
                }
            }
        }
       return -1;
    }
}
