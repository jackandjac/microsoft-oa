import java.util.Collections;
import java.util.List;

public class MinStepsPilesEqualHeight {
    /**
     * Given N piles of equal or unequal heights. In one step, You can remove any number of boxes from the pile which has the maximum height and try to make it equal to the one which is just lower than the maximum height of the stack. Determine the minimum number of steps required to make all of the piles equal in height.
     *
     * Example 1:
     * Input: [5, 2, 1]
     * Output: 3
     * Explanation:
     * Step 1: reducing 5 -> 2 = [2, 2, 1] Step 2: reducing 2 -> 1 = [2, 1, 1] Step 3: reducing 2 -> 1 = [1, 1, 1]
     */
    public static int minSteps(List<Integer> nums) {
        if(null == nums || nums.size() ==0) return 0;
        int minStep =0;
        nums.sort(Collections.reverseOrder());
        for(int i=1;i<nums.size();i++ ){
            if(nums.get(i)!= nums.get(i-1)) minStep +=i;
        }

        return minStep;
    }
}
