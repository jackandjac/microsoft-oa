import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumDegreeTrios {
    public int[][] samples = null;
    public void loadSamples() throws IOException {
      Path filename = Path.of("/Users/junleili/Documents/workspace/microsoft-oa/src/triosample.txt");
      String content = Files.readString(filename);
      String[] numbers = content.split(",");
      int len = numbers.length /2;
      int[][] res = new int[len][2];
      for(int i=0;i<numbers.length;i++){
        String tempstr =numbers[i].trim();
        res[i/2][i%2] = Integer.parseInt(tempstr);
      }
     samples = res;
    }
    public int minTrioDegree(int n, int[][] edges) {
      Map<Integer, Set<Integer>> graph = new HashMap<>();

      for(int[] edge: edges){
        graph.computeIfAbsent(edge[0], k-> new HashSet<>()).add(edge[1]);
        // if(graph.containsKey(edge[0]) ){
        //     graph.get(edge[0]).add(edge[1]);
        // }else{
        //     HashSet<Integer> hset = new HashSet<>();
        //     hset.add(edge[1]);
        //     graph.put(edge[0],hset);
        // }
        graph.computeIfAbsent(edge[1], k-> new HashSet<>()).add(edge[0]);
        // if(graph.containsKey(edge[1]) ){
        //      graph.get(edge[1]).add(edge[0]);
        //  }else{
        //      HashSet<Integer> hset = new HashSet<>();
        //      hset.add(edge[0]);
        //      graph.put(edge[1],hset);
        //  }
      }

      int min = edges.length+1;


      for(int[] edge: edges){
        Set<Integer> na = graph.get(edge[0]);
        Set<Integer> nb = graph.get(edge[1]);

        for(Integer aa : na){
          if(nb.contains(aa) ){
            min = Math.min(min, na.size() + nb.size() + graph.get(aa).size() -6);
          }
        }

      }
      return min == edges.length +1 ? -1: min;
    }
  public int minTrioDegreeQuick(int n, int[][] edges) {
    Map<Integer, Set<Integer>> graph = new HashMap<>();

    for(int[] edge: edges){
      //graph.computeIfAbsent(edge[0], k-> new HashSet<>()).add(edge[1]);
       if(graph.containsKey(edge[0]) ){
           graph.get(edge[0]).add(edge[1]);
       }else{
           HashSet<Integer> hset = new HashSet<>();
           hset.add(edge[1]);
           graph.put(edge[0],hset);
       }
      //graph.computeIfAbsent(edge[1], k-> new HashSet<>()).add(edge[0]);
       if(graph.containsKey(edge[1]) ){
            graph.get(edge[1]).add(edge[0]);
        }else{
            HashSet<Integer> hset = new HashSet<>();
            hset.add(edge[0]);
            graph.put(edge[1],hset);
        }
    }

    int min = edges.length+1;


    for(int[] edge: edges){
      Set<Integer> na = graph.get(edge[0]);
      Set<Integer> nb = graph.get(edge[1]);

      for(Integer aa : na){
        if(nb.contains(aa) ){
          min = Math.min(min, na.size() + nb.size() + graph.get(aa).size() -6);
        }
      }

    }
    return min == edges.length +1 ? -1: min;
  }
    public static void main(String args[]) throws IOException {
      MinimumDegreeTrios trio = new MinimumDegreeTrios();
      trio.loadSamples();
      long ts1 = System.currentTimeMillis();
      trio.minTrioDegree(300, trio.samples);
      long te1 = System.currentTimeMillis();
      System.out.println("time spent:" +(te1 - ts1));

      long ts2 = System.currentTimeMillis();
      trio.minTrioDegreeQuick(300,trio.samples);
      long te2 = System.currentTimeMillis();
      System.out.println("quick trio time spent:" +(te2 - ts2));
    }
}
