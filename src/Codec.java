import java.util.*;

public class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<Node[]> queue = new LinkedList<>();

        queue.offer(new Node[]{root, null});
        int p = -1;
        int idx = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node[] cur = queue.poll();
                sb.append(cur[0].val);
                sb.append(',');
                sb.append(cur[1] == null ? "-1" : ""+cur[1].val);
                sb.append(',');
                sb.append('0' + idx++);
                sb.append(' ');
                if(cur[0].children.size() > 0){
                    List<Node> children = cur[0].children;
                    for(int j = 0; j < children.size(); j++){
                        queue.offer(new Node[]{children.get(j), cur[0]});
                    }
                }
            }
            sb.append('#');
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data == null) return null;
        int ldx = -1;
        Node root = null ;
        Map<Integer, Node> map = new HashMap<>();
        while((ldx = data.indexOf("#"))!= -1){
            PriorityQueue<String[]> pq = new PriorityQueue<>((a,b)-> Integer.parseInt(a[2]) - Integer.parseInt(b[2]));
            String line = data.substring(0, ldx);
            int idx = -1;
            while((idx = line.indexOf(" ")) != -1 ){
                String item = line.substring(0, idx);
                String[] nv = item.split(",");
                if(nv[1].equals("-1")){
                    root = new Node(Integer.parseInt(nv[0]), new ArrayList<>());
                    map.put(root.val, root);
                }else{
                    pq.offer(nv);
                }
                line = line.substring(idx + 1);
            }
            while(!pq.isEmpty()){
                String[] nv = pq.poll();
                Node p = map.get(Integer.parseInt(nv[1]));
                Node nd = new Node(Integer.parseInt(nv[0]), new ArrayList<>());
                p.children.add(nd);
                map.put(nd.val, nd);
            }
            data = data.substring(ldx +1);
        }
        return root;
    }

}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}