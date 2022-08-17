package backtraccking;
/**
 * Leetcode 37. Sudoku Solver
 * https://leetcode.com/problems/sudoku-solver/
 * Using backtrack to solve this challenge.
 * */
public class SudokuSolver {
    private boolean found = false;

    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] boxs = new boolean[9][9];

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.'){
                    int k = (int)(board[i][j] - '1');
                    int b = (i / 3 ) * 3 + j/3;
                    rows[i][k] = true;
                    cols[j][k] = true;
                    boxs[b][k] = true;
                }
            }
        }
    }

    public void backtrack(int cur, char[][] board, boolean[][] rows, boolean[][] cols, boolean[][] boxs){
        if (cur == 81){
            found = true;
            return;
        }

        int r = cur / 9;
        int c = cur % 9;
        int b = (r / 3) * 3 + c / 3;
        if(board[r][c] != '.'){
            backtrack(cur + 1, board, rows, cols, boxs);
            if(found) return;
        }else{
            for(char ch= '1'; ch <= '9'; ch++){
                int k = (int)(ch - '1');
                if(!rows[r][k] && !cols[c][k] && !boxs[b][k]){
                    board[r][c] = ch;
                    rows[r][k] = true;
                    cols[c][k] = true;
                    boxs[b][k] = true;
                    backtrack(cur + 1, board, rows, cols, boxs);
                    rows[r][k] = false;
                    cols[c][k] = false;
                    boxs[b][k] = false;
                    if(found) return;
                }
            }
            board[r][c] = '.';
        }
    }
}
