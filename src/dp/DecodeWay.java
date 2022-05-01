package dp;

public class DecodeWay {

    public int numDecoding(String s){
        int n = s.length();
        int[] dp = new int[n+1];

        for(int i=0;i<=n;i++){
            if(i ==0) {
                dp[i] =1;
            }else{
                dp[i] = 0;
                int temp = s.charAt(i-1) -'0';
                if(temp>0 && temp <10) dp[i] += dp[i-1];

                if(i>1){
                    temp = (s.charAt(i-2) - '0') *10 + temp;
                    if(temp >=10 && temp <=26) dp[i] += dp[i-2];
                }
            }
        }
        return dp[n];
    }
}
