package backtraccking;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0, -1} };
    public List<String> findWord(char[][] board, String[] words){
        TriesNode root = new TriesNode();
        List<String> results = new ArrayList<>();
        for (String word : words) {
            TriesNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children.containsKey(c)) {
                    node = node.children.get(c);
                } else {
                    TriesNode nd = new TriesNode();
                    node.children.put(c, nd);
                    node = nd;
                }
            }
            node.word = word;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (root.children.containsKey(board[i][j])) {
                    backtracking(i, j, root, board, results);
                }
            }
        }
        return results;
    }

    private void backtracking(int row, int col, TriesNode parent, char[][] board, List<String> results){
        char curc = board[row][col];
        TriesNode curNode  = parent.children.get(curc);
        if(curNode.word != null){
            results.add(curNode.word);
            curNode.word = null;
        }

        board[row][col] = '?';
        for(int i=0;i<dirs.length;i++){
            int r = row + dirs[i][0];
            int c = col + dirs[i][1];
            if(r>=0 && c>=0 && r<board.length && c< board[0].length && curNode.children.containsKey(board[r][c])){
                backtracking(r,c, curNode,board,results);
            }
        }
        board[row][col] = curc;
        if(curNode.children.isEmpty()){
            parent.children.remove(curNode);
        }
    }
}
