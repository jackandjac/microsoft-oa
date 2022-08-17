import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LeastNumberOfUnique {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> counter = new HashMap<>();


        for(int i = 0; i < arr.length; i++){
            counter.merge(arr[i], 1, Integer::sum);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> counter.get(a) - counter.get(b));
        for(int key : counter.keySet()){
            pq.offer(key);
        }
        for(int i = 0; i < k; i++){
            int key = pq.poll();
            int res = counter.get(key);
            res--;
            if(res == 0){
                counter.remove(key);
            }else{
                counter.put(key, res);
                pq.offer(key);
            }
        }
        return counter.size();
    }
    public static void main(String args[]){
        LeastNumberOfUnique lnu = new LeastNumberOfUnique();
        int[] arr =new int[]{5,5,4};
        int k = 1;
        lnu.findLeastNumOfUniqueInts(arr,k);
    }
}
