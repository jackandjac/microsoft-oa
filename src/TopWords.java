import java.util.PriorityQueue;

public class TopWords {

    public String[] getTopWords(String[] words, int limit, int minSize){
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)-> Integer.parseInt(b.split(",")[1].trim()) - Integer.parseInt(a.split(",")[1].trim()));
        String[] res = new String[limit];
        for(String word:words){
            pq.offer(word);
        }

        int idx = 0;
        while(!pq.isEmpty()){
            String candidate = pq.poll();
            if(candidate.length() < minSize) continue;
            res[idx++] = candidate;
            if(idx == limit) break;
        }
        return res;
    }

    public void test(){
        String[] s = new String[]{"abc, 500",
                "sadhasjhkgdsak, 230239203",
                "fsgdfssd, 78",
                "sss, 56",
                "ss, 56",
                "sss, 5678",
                "sssdsds, 56",
                "ssssdsd, 56"};
        String[] result = this.getTopWords(s, 3,3);
        for (String item: result){
            System.out.println(item);
        }
    }

    public static void main(String args[]){
        TopWords tw = new TopWords();
        tw.test();
    }
}
