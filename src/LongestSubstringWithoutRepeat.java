import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeat {
    public int lengthOfLongestSubstring(String s){
        int max =0;
        int idx =0;

        Map<Character, Integer> map= new HashMap<>();
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);

            if(map.containsKey(ch)){
                idx = map.get(ch);
            }
            max = Math.max(max, i-idx+1);
            map.put(ch, i+1);
        }
        return max;
    }
}
