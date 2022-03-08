import java.util.List;

public class MaxNetworkRankTest {
    /**
     An infrastructure consisting of n cities from l to n, and m bidirectional roads between them are given. Roads do not intersect apart from at their start and endpoints (they can pass through underground tunnels to avoid collisions).

     For each pair of cities directly connected by a road, let’s define their network rank as the total number of roads that are connected to either of the two cities.

     Write a function, given two arrays starts, ends consisting of m integers each and an integer n, where starts[i] and ends[i] are cities at the two ends of the i-th road, returns the maximal network rank in the whole infrastructure.

     Example:
     Input:
     starts = [1, 2, 3, 3]
     ends = [2, 3, 1, 4]
     n = 4
     Output:
     4
     Explanation:
     The chosen cities may be 2 and 3, and the four roads connected to them are: (2,1), (2,3), (3,1), (3,4).
     */
    public static int maxNetworkRank(List<Integer> starts, List<Integer> ends, int n) {
        int[] ranks = new int[n];
        int len = starts.size();
        for(int i=0;i<len;i++){
            ranks[starts.get(i)-1]++; // the index inside the list starts from 1, so we need to subtract it to 1
            ranks[ends.get(i)-1]++;
        }
        int max =0;
        for(int i=0;i<len;i++){
            max = Math.max(max, ranks[starts.get(i)-1] + ranks[ends.get(i)-1] -1); // calculate the rank of adjacent nodes, so we need to subtract 1 to remove the duplication calculation
        }
        return max;
    }
}
