public class StringWithout3IdenticalConsecutiveLetters {
    /**
     * Given a string S having lowercase English letters, returns a string with no instances of three identical consecutive letters, obtained from S by deleting the minimum possible number of letters.
     *
     * Example 1:
     * Input: eedaaad
     * Output: eedaad
     * Explanation:
     * One occurrence of letter a is deleted.
     *
     * Example 2:
     * Input: xxxtxxx
     * Output: xxtxx
     * Explanation:
     * Note that letter x can occur more than three times in the returned string if the occurrences are not consecutive.
     *
     * Example 3:
     * Input: uuuuxaaaaxum
     * Output: uuxaaxum
     */
    public static String filterString(String s) {
        StringBuilder sb = new StringBuilder("");
        int count =1;
        sb.append(s.charAt(0));
        for(int i=1;i<s.length();i++){
            if(s.charAt(i) == s.charAt(i-1)){
                count++;
            }else{
                count =1;
            }
            if(count <=2){
                sb.append(s.charAt(i));
            }else{
                count=2;
            }
        }
        return sb.toString();
    }
}
