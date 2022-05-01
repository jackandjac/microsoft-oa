package dp;

public class MinimumPathSum {
  public int minPathSum(int[][] grid){
    int row = grid.length;
    int col = grid[0].length;
    int[][] dp = new int[row][col];
    for(int i=0;i<row;i++){
      for(int j=0;j<col;j++){
        if(i==0 && j==0){
          dp[i][j] = grid[i][j];
        }else{
          int selected = Integer.MAX_VALUE;
          if(i>0) selected = Math.min(selected, dp[i-1][j]);
          if(j>0) selected = Math.min(selected, dp[i][j-1]);

          dp[i][j] = selected + grid[i][j];
        }
      }
    }
    return dp[row-1][col -1];

  }

}
