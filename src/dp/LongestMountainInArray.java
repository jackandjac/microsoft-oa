package dp;
/**
 * 845. Longest Mountain in Array
 *
 * */
public class LongestMountainInArray {

    public int longestMountain(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][2];
        int res = 1;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 1;
            } else {
                if (arr[i] > arr[i - 1]) {
                    dp[i][0] = dp[i - 1][0] + 1;
                } else {
                    dp[i][0] = 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1) {
                dp[i][1] = 1;
            } else {
                if (arr[i] > arr[i + 1]) {
                    dp[i][1] = dp[i + 1][1] + 1;
                } else {
                    dp[i][1] = 1;
                }
            }
            if (dp[i][0] != 1 && dp[i][1] != 1) {
                res = Math.max(res, dp[i][0] + dp[i][1] - 1);
            }
        }
        return res == 1 ? 0: 1;
    }
}
