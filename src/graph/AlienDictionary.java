package graph;

import java.util.*;

/**
 * 269. Alien Dictionary
 * */
public class AlienDictionary {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] degree = new int[26];
        this.buildGraph(graph,degree,words);

        Queue<Character> queue = new LinkedList<>();
        for(char c:graph.keySet()){
            if (degree[c - 'a'] == 0) queue.offer(c);
        }
        StringBuilder sb = new StringBuilder();

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                char cc = queue.poll();
                sb.append(cc);
                for(char nc: graph.get(cc)){
                    degree[nc - 'a']--;
                    if(degree[nc - 'a'] == 0) queue.offer(nc);
                }
            }
        }
       return sb.length() == graph.size() ? sb.toString(): "";
    }

    private void buildGraph(Map<Character, Set<Character>> graph, int[] degree,String[] words){
        for(String word: words){
            for(char c:word.toCharArray()){
                graph.putIfAbsent(c,new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++){
            String first = words[i - 1];
            String second = words[i];
            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++ ){
                char u = first.charAt(j);
                char d = second.charAt(j);
                if ( u != d){
                    if (!graph.get(u).contains(d)){
                        graph.get(u).add(d);
                        degree[u - 'a']++;
                    }
                    break;
                }
                if(j + 1 == min && first.length() > second.length()){
                    graph.clear();
                    return;
                }
            }
        }
    }
}
