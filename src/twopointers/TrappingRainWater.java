package twopointers;

public class TrappingRainWater {

    public int trap(int[] height){
        int n = height.length;
        int dp[][] = new int[n][2];
        int leftMax =0;
        int rightMax =0;
        for(int i=0;i<n;i++){
            leftMax  = Math.max(leftMax,height[i]);
            dp[i][0] = leftMax;
        }

        for(int i=n-1;i>=0;i--){
            rightMax = Math.max(rightMax, height[i]);
            dp[i][1] = rightMax;
        }

        int total = 0;

        for(int i=0;i<n;i++){
            int max = Math.min(dp[i][0], dp[i][1]);
            int water = max - height[i];
            total += water <0 ? 0 : water;

        }
        return total;
    }
}
