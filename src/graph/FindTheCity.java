package graph;
import java.util.*;
/**
 *   1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * */
public class FindTheCity {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int res = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});

        }
        int idx = -1;
        for (int i = 0; i < graph.size(); i++) {
            int temp = cheapest(i, graph, distanceThreshold);
            if (temp < res) {
                res = temp;
                idx = i;
            } else if (temp == res) {
                idx = i;
            }
        }

        return idx;
    }

    public int cheapest(int src, Map<Integer, List<int[]>> graph, int dist) {
        int n = graph.size();
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[src] = 0;

        for (int i = 0; i < n; i++) {
            int[] temp = Arrays.copyOf(dp, n);
            for (int j = 0; j < n; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                if (graph.containsKey(j)) {
                    for (int[] data : graph.get(j)) {
                        int nxt = data[0];
                        int weight = data[1];
                        temp[nxt] = Math.min(temp[nxt], dp[j] + weight);
                    }
                }
            }
            dp = temp;

        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (i == src) continue;
            if (dp[i] <= dist) res++;
        }
        return res == 0 ? Integer.MAX_VALUE : res;
    }
    public void test(){
        int n = 6;
        int[][] edge = new int[][]{{0,3,7},{2,4,1},{0,1,5},{2,3,10},{1,3,6},{1,2,1}};
        int dst = 417;
        System.out.println("the expected result is: 5, the actuall result is: " + this.findTheCity(n,edge, dst));


    }
    public static void main(String args[]){
        FindTheCity ftc = new FindTheCity();
        ftc.test();
    }
}

