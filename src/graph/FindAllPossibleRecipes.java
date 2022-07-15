package graph;

import java.util.*;

/**
 * 2115. Find All Possible Recipes from Given Supplies
 */

public class FindAllPossibleRecipes {

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> sup = new HashSet<>();
        Map<String, Integer> idx = new HashMap<>();
        Map<String, List<String>> graph = new HashMap<>();
        int[] degree = new int[recipes.length];

        for (String item : supplies) {
            sup.add(item);
        }

        for (int i = 0; i < recipes.length; i++) {
            idx.put(recipes[i], i);
        }
        for (int i = 0; i < recipes.length; i++) {
            for (String ingredient : ingredients.get(i)) {
                if (sup.contains(ingredient)) continue;
                graph.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipes[i]);
                degree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0){
                queue.offer(i);
            }
        }

        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int id = queue.poll();
                result.add(recipes[id]);

                if(!graph.containsKey(id)){
                    continue;
                }
                for(String r: graph.get(recipes[id])){
                    if(--degree[idx.get(r)] == 0){
                        queue.offer(idx.get(r));
                    }
                }
            }
        }
        return result;
    }
}
