package backtraccking;

import java.util.*;
import java.util.stream.Collectors;

public class Permutations {
    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        backtrack(nums.length,0,list,result);
        return result;
    }

    private void backtrack(int n, int first, List<Integer> nums, List<List<Integer>> result){
        if(first == n) result.add(nums);

        for(int i=0;i<n;i++){
            Collections.swap(nums,first,i);
            backtrack(n,first +1,nums,result);
            Collections.swap(nums,first,i);
        }
    }
}
