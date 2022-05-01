package dp;

public class FlipStringToMonotoneIncreasing {

    public int minFlipsMonoIncr(String s){
        int n = s.length();
        int dp[][] = new int[n+2][2];

        for(int i=1;i<=n;i++){

            if(s.charAt(i-1) == '1' ){
                dp[i][0] = dp[i-1][0] + 1;
            }else{
                dp[i][0] = dp[i-1][0];
            }

            if(s.charAt(n-i) == '0'){
                dp[n-i][1] = dp[n-i+1][1]+1;
            }else{
                dp[n-i][1] = dp[n-i+1][1];
            }
        }
        int res = Integer.MAX_VALUE;
        for(int i=1;i<dp.length;i++){
            res = Math.min(res,dp[i-1][0] + dp[i][1]);
        }
        return res;
    }

}
