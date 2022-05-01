import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxProfitJobScheduling {
  int[] dp;
  public int jobScheduling(int[] startTime, int endTime[], int[] profit){
    List<int[]> jobs = new ArrayList<>();
    dp = new int[profit.length+1];

    Arrays.fill(dp,-1);
    for(int i=0;i<profit.length;i++){
      int[] job = new int[]{startTime[i], endTime[i], profit[i]};
      jobs.add(job);
    }
    jobs.sort(Comparator.comparingInt(a->a[0]));
    return findMaxProfit(jobs,0);

  }

  private int findMaxProfit(List<int[]> jobs, int pos){
    if(pos == jobs.size() ) return 0;
    if(dp[pos]!=-1) return dp[pos];

    int nxtIdx = findNxtJob(jobs,pos) ;
    int maxProfit = Math.max(
        findMaxProfit(jobs,pos+1),
        jobs.get(pos)[2] + findMaxProfit(jobs,nxtIdx)
    );
    dp[pos] = maxProfit;
    return maxProfit;
  }

  private int findNxtJob(List<int[]> jobs, int idx){
    int endTime = jobs.get(idx)[1];
    int st = 0, end =jobs.size()-1, nxtIdx = jobs.size();
    while(st<=end){
      int mid = (st + end )/2;
      if(jobs.get(mid)[0] >= endTime){
        nxtIdx = mid;
        end = mid -1;
      }else{
        st = mid +1;
      }
    }
    return nxtIdx;
  }

  private int practiceFindNxtJob(List<int[]> jobs, int idx){
    int endTime = jobs.get(idx)[1];
    int st =0, end = jobs.size()-1, nxtIdx = jobs.size();
    while(st<=end){
      int mid = (st + end )/2;
      if(jobs.get(mid)[0] >= endTime ){
        nxtIdx = mid;
        end = mid -1;
      }else{
        st = mid +1;
      }
    }
    return nxtIdx;
  }

}
