package graph;
/**
 *
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * Solved it with Floyd Warshall Algorithm.
 * */
import java.util.Arrays;

public class FindTheCityFloydWarshall {
    public int findTheCity(int n, int[][] edges, int dt) {
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
            dp[i][i] = 0;
        }
        for(int[] edge : edges){
            int src = edge[0], dst = edge[1], wet = edge[2];
            dp[src][dst] = wet;
            dp[dst][src] = wet;
        }

        int res = 0;
        int min = Integer.MAX_VALUE /2;

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(dp[i][j] > dp[i][k] + dp[k][j]){
                        dp[i][j] = dp[i][k] + dp[k][j];
                    }
                }
            }
        }

        for(int i = 0; i < n; i++){
            int count = 0;
            for(int j = 0; j < n; j++){
                if(dp[i][j] <= dt) count++;
            }
            if(count <= min){
                min = count;
                res = i;
            }
        }
        return res;
    }
}
