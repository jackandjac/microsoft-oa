import java.util.*;

public class CodecII
{
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if(root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<Node[]> queue = new LinkedList<>();

        queue.offer(new Node[]{root, null});

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                Node[] nds = queue.poll();
                Node p = nds[1];
                sb.append("" + nds[0].val);
                sb.append(',');
                sb.append(p == null ? "x" : "" + p.val);
                sb.append(" ");

                if(nds[0].children.size() > 0){
                    for(int j = 0; j < nds[0].children.size(); j++){
                        queue.offer(new Node[]{nds[0].children.get(j), nds[0]});
                    }
                }
            }
            sb.append('#');
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        Node root = null;
        if(data == null) return root;
        int ldx = -1;
        Map<Integer, Node> map = new HashMap<>();
        while((ldx = data.indexOf("#")) != -1){
            String line = data.substring(0, ldx);

            int idx = -1;
            while(( idx = line.indexOf(" ")) != -1){
                String item = line.substring(0, idx);
                String[] ndata = item.split(",");
                if(root == null && ndata[1].equals("x")){
                    root = new Node(Integer.parseInt(ndata[0]), new ArrayList<>());
                    map.put(root.val, root);
                }else{
                    Node p = map.get(Integer.parseInt(ndata[1]));
                    Node cur = new Node(Integer.parseInt(ndata[0]), new ArrayList<>());
                    p.children.add(cur);
                    map.put(cur.val, cur);
                }

                line = line.substring(idx + 1);
            }

            data = data.substring(ldx + 1);
        }
        return root;
    }
}