package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().sorted().collect(Collectors.toList());
        List<Integer> res = new ArrayList<>();

        for(int num:nums){
            int pos = bSearch(list,num);
            res.add(pos);
            list.remove(pos);
        }
        return res;

    }

    private int bSearch(List<Integer> list, int num){
        int left =0;
        int right = list.size()-1;

        while(left < right){
            int mid = (right - left)/2 + left;
            if(list.get(mid)< num){
                left = mid +1;
            }else{
                right = mid;
            }
        }
        return left;

    }
}
