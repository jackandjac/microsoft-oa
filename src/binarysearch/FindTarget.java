package binarysearch;

public class FindTarget {

    public int binarySearch(int[] nums, int target){
        int left = 0, right = nums.length - 1;
        while(left <= right ){
            // left <= right  means when left - 1 = right the loop will end
            // left <  right  means when left = right the loop will end
            int mid = (left + right)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
}
