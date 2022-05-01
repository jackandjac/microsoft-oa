import java.util.HashSet;
import java.util.Set;

public class MakeALargeIsland {
    private int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public int largestIsLand(int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        int idx = 2;
        int[] dp = new int[m*n +2];
        int max = 0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 1) {
                    dp[idx] = dfs(i,j,idx,grid);
                    max = Math.max(dp[idx], max);
                    idx++;
                }
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j] == 0){
                    Set<Integer> neighbors = new HashSet<>();
                    for(int k=0;k<dirs.length;k++){
                        int r = i + dirs[k][0];
                        int c = j + dirs[k][1];

                        if(r >=0 && c>=0 && r< m && c< n && grid[r][c] > 1){
                            neighbors.add(grid[r][c]);
                        }
                    }
                    int res =1;
                    for(int item:neighbors){
                        res += dp[item];
                    }
                    max = Math.max(res,max);
                }
            }
        }

        return max;

    }
    private int dfs(int row, int col, int idx, int[][] grid){
        int res =1;
        grid[row][col] = idx;
        for(int i=0;i<dirs.length;i++){
            int r = row + dirs[i][0];
            int c = col + dirs[i][1];

            if(r>=0 && c >=0 && r<grid.length && c <grid[0].length && grid[r][c] == 1){
                res += dfs(r,c, idx, grid);
            }
        }

        return res;
    }
}
