package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxProfitJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<int[]> jobs = new ArrayList<>();
        for (int i = 0; i < startTime.length; i++ ){
            jobs.add(new int[]{startTime[i], endTime[i], profit[i]});
        }
        jobs.sort(Comparator.comparingInt(a-> a[0]));
        int[] dp = new int[startTime.length];
        Arrays.fill(dp, -1);
        return dfs(0, jobs, dp);
    }

    private int dfs(int idx, List<int[]> jobs, int[] dp){
        if( idx == jobs.size()) return 0;
        if( dp[idx] != -1) return dp[idx];

        int nextIdx = nexIdx(idx, jobs);

        dp[idx] = Math.max(dfs(idx +1, jobs, dp), jobs.get(idx)[2] + dfs(nextIdx,jobs,dp));
        return dp[idx];
    }

    private int nexIdx(int idx, List<int[]> jobs){
        int endTime = jobs.get(idx)[1];
        int left = 0, right = jobs.size() -1, nexIdx = jobs.size();

        while (left <= right){
            int mid = (left + right ) /2;
            if(jobs.get(mid)[0] >= endTime){
                right = mid - 1;
                nexIdx = mid;
            }else{
                left = mid + 1;
            }
        }
        return nexIdx;
    }
}
