import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LongestHappyString {

    public String longestDiverseStringV1(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int []> queue = new PriorityQueue<>((x, y) ->(y[1] - x[1]));
        queue.offer(new int[]{0, a});
        queue.offer(new int[]{1, b});
        queue.offer(new int[]{2, c});
        while(queue.peek()[1] > 0){
            int []top = queue.poll();
            if(sb.length() < 2 || !(    (sb.charAt(sb.length() -1) == (char)(top[0] + 'a'))
                                     && (sb.charAt(sb.length() -1) == sb.charAt(sb.length() -2))
                                    ) // the situation here is little complex,
                                      // the opposite condition of sb.charAt(last) == top[0]+ 'a' && sb.charAt(last) == sb.charAt(last -1 )
                                      // has 3 possible branch:  1) sb = 'ab' , top[0] = 'a'    2) sb == 'aa' , top[0] = 'b'    3) sb = 'ab' , top = 'b'
               ){
                int count = Math.min(1, top[1]);
                for(int i =0;i< count; i++){
                    sb.append((char)(top[0] + 'a'));
                }
                top[1] = top[1] - count;
            } else {
                int [] second = queue.poll();
                if(0 == second[1]){
                    break;
                }
                int count = Math.min(1, second[1]);
                for(int i =0;i< count; i++){
                    sb.append((char)(second[0] + 'a'));
                }
                second[1] = second[1] -count;
                queue.offer(second);
            }
            queue.offer(top);
        }
        return sb.toString();
    }
    public String practiceLongestHappyString(int a, int b, int c){
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y)-> (x[1] - y[1]));

        pq.offer(new int[]{0,a});
        pq.offer(new int[]{1,b});
        pq.offer(new int[]{2,c});

        while(pq.peek()[1]>0){
            int[] top = pq.poll();
            if(
                    sb.length()<=1 || !(sb.charAt(sb.length()-1) == sb.charAt(sb.length()-2) && sb.charAt(sb.length()-1) == (char)(top[0] + 'a')  )
              )
            {
                int count = Math.min(1, top[1]);
                if(count ==1){
                    sb.append((char)(top[0]+'a') );
                }
                top[1] -=count;
            }else{
                int[] secondary = pq.poll();
                if(secondary[1] == 0) break;

                int count = Math.min(1, secondary[1]);
                if(count ==1){
                    sb.append((char)(secondary[0]+'a') );
                }
                secondary[1] -= count;
                pq.offer(secondary);
            }
            pq.offer(top);

        }
        return sb.toString();
    }

    public static void main(String args[]){
        Map<String, Integer> ccounter = new HashMap<>();

    }

}
