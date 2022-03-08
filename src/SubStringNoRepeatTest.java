import java.util.HashSet;
import java.util.Set;

public class SubStringNoRepeatTest {

/** Given a string s, find the minimum number of substrings you can create
 *  without having the same letters repeating in each substring.
 *  Example:
 *  world -> 1, as the string has no letters that occur more than once.
    dddd -> 4, as you can only create substring of each character.
    abba -> 2, as you can make substrings of ab, ba.
    cycle-> 2, you can create substrings of (cy, cle) or (c, ycle)
*
*/

    public static int subStringNoRepeat(String str){
        if(null == str || str.length() == 0) return 0;
        int res =0;
        Set<Character> cset = new HashSet<>();

        for(char item: str.toCharArray()){
            if(cset.contains(item)){
                res++;
                cset.clear();
            }
            cset.add(item);
        }
        return res;
    }
}
