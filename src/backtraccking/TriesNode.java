package backtraccking;

import java.util.HashMap;
import java.util.Map;

public class TriesNode {
    public Map<Character,TriesNode> children = new HashMap<>();
    public String word = null;
    public TriesNode(){}
}
