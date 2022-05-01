import java.util.*;

public class UserWebSiteVisitPattern {

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
            Map<String, TreeMap<Integer,String>> visits = new HashMap<>();
            Map<String,Integer> patternCounter = new HashMap<>();
            for(int i=0;i<username.length;i++){
                    visits.computeIfAbsent(username[i], k-> new TreeMap<>()).put(timestamp[i],website[i]);
            }

            for(String user: visits.keySet()){
                    List<String> sites = new LinkedList<>(visits.get(user).values());
                    for(String pattern: getPatterns(sites)){
                            patternCounter.merge(pattern,1, Integer::sum);
                    }
            }
            String[] maxPattern = new String[]{"","",""};
            int maxCount =0;
            for(String pattern: patternCounter.keySet()){
                    int count = patternCounter.get(pattern);
                    String[] patt = pattern.split(",");
                    if(count > maxCount || (count == maxCount && Arrays.compare(patt,maxPattern)<0)){
                            maxPattern = patt;
                            maxCount = count;
                    }
            }


             return Arrays.asList(maxPattern);
    }
    public Set<String> getPatterns(List<String> sites){
            Set<String> patterns = new HashSet<>();
            int n = sites.size();
            for(int i=0;i<n-2;i++){
                    for(int j=i+1;j<n-1;j++){
                            for(int k=j+1;k<n;k++){
                                    String pattern = sites.get(i)+ "," + sites.get(j) + "," + sites.get(k);
                                    patterns.add(pattern);
                            }
                    }
            }
            return patterns;
    }

}
