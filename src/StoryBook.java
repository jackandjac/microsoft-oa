import java.util.HashMap;
import java.util.Map;

public class StoryBook {

    public int findEnding(int[] endings, int[][] choices, int option){
        int n = endings.length;
        int[] visited = new int[endings[n-1]+1];
        int[] endingsIdx = new int[endings[n-1]+1];

        Map<Integer, Integer> jumpIdx = new HashMap<>();
        for(int i = 0; i < choices.length; i++){
            jumpIdx.put(choices[i][0], choices[i][option]);
        }
        for(int end:endings){
            endingsIdx[end]++;
        }
        int idx = 0;
        while(idx <= endings[n-1]){
            if(visited[idx] > 0){
                idx = -1;
                break;
            }
            if(endingsIdx[idx] == 1) break; // find the ending;
            visited[idx]++;
            if(jumpIdx.containsKey(idx)){
                idx = jumpIdx.get(idx);
                continue;
            }else{
                idx++;
            }
        }
        return idx;
    }

    public void test() {
        int[][] choices1 = {{3, 14, 2}};
        int[][] choices2 = {{5, 11, 28}, {9, 19, 29}, {14, 16, 20}, {18, 7, 22}, {25, 6, 30}};
        int[][] choices3 = {};
        int[][] choices4 = {{2, 10, 15}, {3, 4, 10}, {4, 3, 15}, {10, 3, 15}};
        int[] endings =  {6, 15, 21, 30};
        System.out.println(this.findEnding(endings,choices1,1)); // 15
        System.out.println(this.findEnding(endings,choices1,2));
        System.out.println(this.findEnding(endings,choices2,1));
        System.out.println(this.findEnding(endings,choices2,2));
        System.out.println(this.findEnding(endings,choices3,1));
        System.out.println(this.findEnding(endings,choices3,2));
        System.out.println(this.findEnding(endings,choices4,1));
        System.out.println(this.findEnding(endings,choices4,2));

//        find_ending(endings, choices1, 1) => 15
//        find_ending(endings, choices1, 2) => -1
//        find_ending(endings, choices2, 1) => 21
//        find_ending(endings, choices2, 2) => 30
//        find_ending(endings, choices3, 1) => 6
//        find_ending(endings, choices3, 2) => 6
//        find_ending(endings, choices4, 1) => -1
//        find_ending(endings, choices4, 2) => 15

    }
    public static void main(String args[]){
        StoryBook sb = new StoryBook();
        sb.test();
    }
}
