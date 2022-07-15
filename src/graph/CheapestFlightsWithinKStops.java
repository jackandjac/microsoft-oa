package graph;

import java.util.Arrays;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MIN_VALUE);
        cost[src] = 0;
        for(int i = 0; i <= k; i++){
            int[] temp = Arrays.copyOf(cost, n);
            for(int[] flight: flights ){
                int cur = flight[0], nxt = flight[1], price = flight[2];
                if(cost[cur] == Integer.MAX_VALUE) continue;
                temp[nxt] = Math.min(temp[nxt], cost[cur] + price);
            }
            cost = temp;
        }
        return cost[dst] == Integer.MAX_VALUE ? -1: cost[dst];
    }
}
