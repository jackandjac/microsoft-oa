package dp;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int max = 1;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        String res = "";
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            res = s.substring(i, i + 1);
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                res = s.substring(i, i + 2);
                max = 2;
            }
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 0, k = j + i - 1; k < n; j++, k++) {
                dp[j][k] = dp[j + 1][k - 1] && (s.charAt(j) == s.charAt(k));
                if (dp[j][k]) {
                    int len = k - j + 1;
                    if (len > max) {
                        max = len;
                        res = s.substring(j, k + 1);
                    }
                }
            }
        }
        return res;

    }
}
