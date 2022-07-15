import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII {

    public List<String> findWords(char[][] board, String[] words){
        TriesNode root = new TriesNode();
        List<String> result = new ArrayList<>();

        for(String word: words){
            TriesNode node = root;
            for(char c: word.toCharArray()){
                if(node.children.containsKey(c)){
                    node = node.children.get(c);
                }else{
                    TriesNode nd = new TriesNode();
                    node.children.put(c, nd);
                }
            }
            node.content = word;
        }

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(root.children.containsKey(board[i][j])){
                    backtrack(i,j,root, result,board);
                }
            }
        }
        return result;
    }

    public void backtrack(int row, int col, TriesNode node, List<String> res, char[][] board){
        int[][] dirs = new int[][]{{1,0},{-1,0}, {0,1}, {0, -1}};
        char cur = board[row][col];
        TriesNode curNode = node.children.get(cur);
        if(curNode.content != null){
            res.add(curNode.content);
            curNode.content = null;
        }

        board[row][col] = '?';
        for(int i = 0; i < dirs.length; i++){
            int r = row + dirs[i][0];
            int c = col + dirs[i][1];
            if(r >= 0 && c >= 0 && r < board.length && c < board[0].length && curNode.children.containsKey(board[r][c])){
                backtrack(r,c, curNode, res, board);
            }
        }
        board[row][col] = cur;
        if(curNode.children.isEmpty()){
            node.children.remove(curNode);
        }
    }
}

class TriesNode {
    public Map<Character, TriesNode> children = new HashMap<>();
    public String content = null;
    public TriesNode(){

    }
}
