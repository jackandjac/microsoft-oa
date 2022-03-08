public class FindLargeNum {
/**
//    Given an integer(+ve or -ve) consisting of at least one 5 in its digits(can have more than one 5). Return the maximum value by deleting exactly one 5 from its digit.
//            Ex: N = 1598 => result = 198(remove the only 5 from the sequence)
//    N = 150,958 => result = 15,098(we wanna return the maximum value 15,098 > 10,958)
//    N = -5859 => result = -589 ( -589>- 859)
 */
    public static int findLargeNum(int num){
        if(num > -10 && num < 10) return num;
        int max = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder(""+num);
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i) == '5'){
                sb.deleteCharAt(i);
                max = Math.max(max, Integer.valueOf(sb.toString()));
                sb.insert(i,'5');
            }
        }
        
        return max == Integer.MIN_VALUE? num : max;
    }
}
