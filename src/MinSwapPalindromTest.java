public class MinSwapPalindromTest {
    /**

    Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome. If not possible, return -1.

    Example 1:
    Input: mamad
    Output: 3
    Explanation:
    swap m with a => maamd

    swap m with d => maadm

    swap a with d => madam

    Example 2:
    Input: asflkj
    Output: -1
    Example 3:
    Input: mideld
    Output: 3
    Explanation:
    swap e with l => midled

    swap e with d => midlde

    swap l with d => middle

     */

    public static int minSwap(String str){
       if(null == str || str.length() ==0) return -1;
       int ms =0;
       if(isValid(str)){
           char[] words = str.toCharArray();
           int p1 =0, p2 =words.length-1;
           while(p1 < p2){
               if(words[p1] != words[p2]){
                   int cur = p2;
                   while(p1<cur && words[p1] != words[cur]) cur--;
                   if(cur == p1) {
                       swap(words, p1, p1+1);
                       ms++;
                   }else{
                       while(cur < p2){
                           swap(words, cur, cur+1);
                           cur++;
                           ms++;
                       }
                   }

               }else{
                   p1++;
                   p2--;
               }
           }
          return ms;
       }

       return -1;
    }

    public static boolean isValid(String str){
        int oddCount =0;
        int[] lettcount = new int[26];
        for(char item:str.toCharArray()){
            lettcount[item -'a']++;
        }
        for(int i=0;i<lettcount.length;i++){
            if(lettcount[i] %2 ==1) oddCount++;
        }
        return oddCount<=1;
    }

    public static void swap(char[] array, int pos1, int pos2){
        char temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }
}
