import java.util.HashSet;
import java.util.Set;

public class FindTheLeader {
    public int[] findLeader(int[] nums){
        int max = Integer.MIN_VALUE;
        int[] marray = new int[nums.length];
        Set<Integer> maxSet = new HashSet<>();
        for(int i = nums.length - 1; i >= 0; i--){
            Math.max(max,nums[i]);
            marray[i] = max;
            maxSet.add(max);
        }
        int[] res = new int[maxSet.size()];
        int idx = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == marray[i]){
                res[idx++] = nums[i];
            }
        }
        return res;
    }
}
