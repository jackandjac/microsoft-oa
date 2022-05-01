import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    // best solution
    public boolean checkSubarraySum(int[] nums, int k){
        Map<Integer, Integer> remainIdx = new HashMap<>();
        remainIdx.put(0,-1);
        int total = 0;
        for(int i=0;i< nums.length;i++){
            total += nums[i];
            int remain = total % k;
            if(remainIdx.containsKey(remain)){
                int j = remainIdx.get(remain);
                if(i-j >=2 ) return true;
            }else{
                remainIdx.put(remain,i);
            }
        }
        return false;
    }
   // time limited exceeded
    public boolean checkSubArrSum(int[] nums, int k){
        int n = nums.length;
        int[] totals = new int[n];

        for(int i=0;i<n;i++){
            totals[i] = nums[i];
            if(i>0){
                totals[i] += totals[i-1];
            }
        }

        for(int i=2;i<=n;i++){
            int sum = totals[i-1];
            for(int j=i-1;j<n;j++){
                if(j>i-1) sum -= totals[j -i];
                if(sum % k == 0) return true;
            }
        }
        return false;
    }
}
