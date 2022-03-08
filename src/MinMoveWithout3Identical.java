public class MinMoveWithout3Identical {

    /**
     *
     * Given a string S consisting of N letters a and b. In one move you can replace one letter by the other (a by b or b by a).
     *
     * Write a function solution that given such a string S, returns the minimum number of moves required to obtain a string containing no instances of three identical consecutive letters.
     *
     * Example 1
     * Input: baaaaa
     *
     * Output: 1
     *
     * Explanation: The string without three identical consecutive letters which can be obtained is one move is "baabaa".
     *
     * Example 2
     * Input: baaabbaabbba
     *
     * Output: 1
     *
     * Explanation: The string without three identical consecutive letters which can be obtained is one move is "baabaa"
     *
     * Example 3
     * Input: baabab
     *
     * Output: 0
     */

    public static int minMoves(String s) {
        if(null == s || s.length()==0) return 0;
        int minMove =0;
        int idx =0;

        while(idx < s.length()){
            int cur = idx+1;
            while(cur<s.length() && s.charAt(idx) == s.charAt(cur)) cur++;
            int lett = cur - idx;
            if(lett >=3){
                while(lett>5){
                    lett -=3;
                    minMove++;
                }
                if(lett<=5) minMove++;
            }
            idx = cur;
        }

        return minMove;
    }
}
