package dp;

public class UniquePathII {
    public int uniquePathWithObstacle(int[][] obg){
        int r = obg.length;
        int c = obg[0].length;

        int[][] dp = new int[r][c];

        for (int i = 0; i < r; i++){
            for (int j = 0; j < c; j++){
                if(obg[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    if(i == 0 && j == 0){
                        dp[i][j] = 1;
                    }else if(i ==  0){
                        dp[i][j] = dp[i][j-1];
                    }else if(j == 0){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = dp[i-1][j] + dp[i][j-1];
                    }
                }
            }
        }
        return dp[r-1][c-1];
    }
}
