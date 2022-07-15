package graph;

import java.util.*;

/**
 * 399. Evaluate Division
 * */
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < values.length; i++) {
            List<String> eq = equations.get(i);
            String first = eq.get(0);
            String second = eq.get(1);
            // if(first.length() == 1 && second.length() == 1 ){
            graph.computeIfAbsent(first, k -> new HashMap<>()).put(second, values[i]);
            graph.computeIfAbsent(second, k -> new HashMap<>()).put(first, 1 / values[i]);
            // }else{
            //     String[] reduced = reduceCommon(first, second);
            //     graph.computeIfAbsent(reduced[0], kj-> new HashMap<>()).put(reduced[1], values[i]);
            //     graph.computeIfAbsent(reduced[1], kj-> new HashMap<>()).put(reduced[0], 1/values[i]);
            // }
        }
        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            results[i] = bfs(queries.get(i), graph);
        }
        return results;
    }

    private double bfs(List<String> query, Map<String, Map<String, Double>> graph) {
        double res = -1;

        Queue<Pair<String, Double>> queue = new LinkedList<>();
        queue.offer(new Pair(query.get(0), 1));
        String tar = query.get(1);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair<String, Double> p = queue.poll();
                double factor = p.getValue();
                String key = p.getKey();
                if (key.equals(tar)) {
                    res = factor;
                    break;
                }
                if (graph.containsKey(key)) {
                    Map<String, Double> candidates = graph.get(key);
                    for (String kkey : candidates.keySet()) {
                        queue.offer(new Pair(kkey, factor * candidates.get(kkey)));
                    }
                }
            }
        }
        return res;
    }
}
class Pair<T,R>{
    private T key;
    private R value;
    public Pair(T key, R value){
        this.key = key;
        this.value = value;
    }
    public T getKey(){
        return this.key;
    }

    public R getValue() {
        return this.value;
    }
}
