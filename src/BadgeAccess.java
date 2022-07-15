import java.util.*;

public class BadgeAccess {
    List<List<String>> analyzeLog(List<String[]> logs){
        Map<String, Integer> entryLogs = new HashMap<>();
        Set<String> eSet = new HashSet<>();
        Set<String> exSet = new HashSet<>();
        for(String[] log: logs){
            String key = log[0];
            int val = log[1].equals("enter") ? 1 : -1;
            entryLogs.merge(key, val, Integer::sum);
            if(entryLogs.get(key) < 0 ) {
                if(!exSet.contains(key)) exSet.add(key);
            }else if(entryLogs.get(key) >= 2){
                if(!eSet.contains(key)) eSet.add(key);
            }

        }

        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(eSet));
        result.add(new ArrayList<>(exSet));
        return result;
    }
}
