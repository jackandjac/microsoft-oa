public class LongestSubstringWithoutTwo {
    /**

            Given a string str containing only a and b, find the longest substring of str such that str does not contain more than two contiguous occurrences of a and b.

            Example 1:
            Input: aabbaaaaabb
            Output: aabbaa
            Example 2:
            Input: aabbaabbaabbaaa
            Output: aabbaabbaabbaa
     */
    public static String longestValidString(String str) {
        String res = "";
        int count =1;
        int start =0 ;
        for(int i=1;i<str.length();i++){
            if(str.charAt(i) == str.charAt(i-1)){
                count++;
            }else{
                count  =1;
            }
            if(count <=2){
                int len = i - start +1;
                if(res.length()< len ){
                    res = str.substring(start, start + len);
                }else{
                    count =2;
                    start = i-1;
                }
            }
        }

        return res;
    }
}
