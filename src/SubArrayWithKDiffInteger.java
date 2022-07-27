import java.util.HashMap;
import java.util.Map;

public class SubArrayWithKDiffInteger {
    /**
     * 992. Subarrays with K Different Integers
     *
     * */
    public int subarraysWithKDistinct(int[] nums, int k) {
       return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k){
        int n = nums.length;
        int i = 0, j = 0, res = 0;
        Map<Integer, Integer> counter = new HashMap<>();

        while (j < n) {
            counter.merge(nums[j], 1, Integer::sum);
            while (counter.size() > k) {
                counter.merge(nums[i], -1, Integer::sum);
                if (counter.get((nums[i])) == 0) counter.remove(nums[i]);
                i++;
            }
            j++;
            res += j - i + 1;
        }
        return res;
    }
}
