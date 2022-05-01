import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {

        PriorityQueue<Integer> index;
        Map<Integer, Integer> kcounter = new HashMap<>();
        Map<Integer, Integer> cache = new HashMap<>();
        int capacity;
        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.index = new PriorityQueue<>((Integer a,Integer b)-> kcounter.get(a) == kcounter.get(b) ? a -b : kcounter.get(a) - kcounter.get(b) );
        }

        public int get(int key) {
            if(capacity == 0) return -1;
            if(cache.containsKey(key) ){
                this.refresh(key);
                return cache.get(key);
            }
            return -1;

        }

        public void put(int key, int value) {
            if(capacity == 0) return;
            if(capacity == index.size() ){
                if(cache.containsKey(key) ){
                    this.refresh(key);
                    cache.put(key,value);
                    return;
                }else{
                    evict();

                }
            }
            cache.put(key,value);
            kcounter.merge(key,1, Integer::sum);
            index.offer(key);



        }

        private void evict(){
            if(capacity == 0) return;
            Integer key = index.poll();
            cache.remove(key);
            kcounter.remove(key);
        }

        private void refresh(Integer key){
            index.remove(key);
            kcounter.merge(key,1, Integer::sum);
            index.add(key);
        }

        public static void main(String args[]){
            /**
             * Input
             * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
             * [[2],      [1, 1], [2, 2], [1],   [3, 3], [2],  [3],   [4, 4], [1],  [3],   [4]]
             * Output
             * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
             * */
            LFUCache cache = new LFUCache(2);
            cache.put(1,1);
            cache.put(2,2);
            System.out.println(cache.get(1));
            cache.put(3,3);
            System.out.println(cache.get(2));
            System.out.println(cache.get(3));
            cache.put(4,4);
            System.out.println(cache.get(1));
            System.out.println(cache.get(3));
            System.out.println(cache.get(4));



        }
}


