import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> wset = new HashSet<>();
        for(String word:words){
            wset.add(word);
        }
        List<String> result = new ArrayList<>();
        for(String item:words){
            wset.remove(item);
            if(backtrack(item,wset,0)){
                result.add(item);
            }
        }
        return result;
    }

    private boolean backtrack(String s, Set<String> word, int idx){
        if(idx == s.length()) return true;

        for(int i = idx + 1; i <= s.length(); i++){
            String sub = s.substring(idx, i);
            if(word.contains(sub) && backtrack(s,word,i)) return true;
        }
        return false;
    }
}
