public class LongestSemiAlternatingSubstring {
    /**
     *
     * Given a string S containing only characters a and b. A substring (contiguous fragment) of S is called a semi-alternating substring if it does not contain three identical consecutive characters. In other words, it does not contain either 'aaa' or 'bbb' substrings. Note that the whole S is its own substring.
     *
     * Example 1:
     * Input: baaabbabbb
     * Output: 7
     * Explanation:
     * the longest semi-alternating substring is aabbabb
     *
     * Example 2:
     * Input: babba
     * Output: 5
     * Explanation:
     * Whole S is semi-alternating.
     *
     * Example 3:
     * Input: abaaaa
     * Output: 4
     * Explanation:
     * The first four letters of S create a semi-alternating substring.
     */
    public static int semiSubstring(String s) {
        if (null == s || s.length() ==0 ) return 0;
        int longest=0;
        int count =1;
        int start =0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i) == s.charAt(i-1)){
                count++;
            }else{
                count=1;
            }
            if(count <=2){
                longest = Math.max(longest, i- start +1);
            }else{
                count =2;
                start = i-1;
            }
        }

        return longest;
    }
}
