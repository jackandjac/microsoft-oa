package backtraccking;

import java.util.ArrayList;
import java.util.List;

public class SudokuGenerator {
    boolean found = false;
    public int[][] generateSudoku(){
        found = false;
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] box  = new boolean[9][9];
        int[][] matrix = new int[9][9];
        backtrack(0, cols, rows, box, matrix);
        return matrix;
    }
    public int[][] generateRandomSudoku(){
        found = false;
        boolean[][] rows = new boolean[9][9];
        boolean[][] cols = new boolean[9][9];
        boolean[][] box  = new boolean[9][9];
        int[][] matrix = new int[9][9];
        backtrackWithRandom(0, cols, rows, box, matrix);
        return matrix;
    }
    public void backtrack(int cur, boolean[][] cols, boolean[][] rows, boolean[][] box, int[][] matrix){
        if(cur == 81){
            found = true;
            return;
        }
        int r = cur / 9;
        int c = cur % 9;
        int boxi = (r/3)* 3 + c/3;

        for(int i = 1; i <=9 ; i++){
            matrix[r][c] = i;

            if(!cols[c][ i -1] && !rows[r][i - 1] && !box[boxi][i - 1]){
                cols[c][i-1] = true;
                rows[r][i-1] = true;
                box[boxi][i-1] = true;
                backtrack(cur + 1, cols, rows, box, matrix);
                cols[c][i-1] = false;
                rows[r][i-1] = false;
                box[boxi][i-1] = false;
                if(found) break;
            }
        }
    }

    public void backtrackWithRandom(int cur, boolean[][] cols, boolean[][] rows, boolean[][] box, int[][] matrix){
        if(cur == 81){
            found = true;
            return;
        }
        int r = cur / 9;
        int c = cur % 9;
        int boxi = (r/3)* 3 + c/3;
        List<Integer> candidates = this.randGenrator();
        for(int i = 0; i < candidates.size(); i++){
            matrix[r][c] = candidates.get(i);
            int idx = candidates.get(i);
            if(!cols[c][ idx -1] && !rows[r][idx - 1] && !box[boxi][idx - 1]){
                cols[c][idx-1] = true;
                rows[r][idx-1] = true;
                box[boxi][idx-1] = true;
                backtrack(cur + 1, cols, rows, box, matrix);
                cols[c][idx-1] = false;
                rows[r][idx-1] = false;
                box[boxi][idx-1] = false;
                if(found) break;
            }
        }
    }
    public List<Integer> randGenrator(){
        boolean[] nums = new boolean[9];
        List<Integer> list = new ArrayList<>();
        while(list.size() < 9){
            int idx = (int)(Math.random() * 9 );
            if(!nums[idx]){
                list.add(idx + 1);
            }
            nums[idx] = true;
        }
        return list;
    }

    public static void main(String args[]){
        SudokuGenerator generator = new SudokuGenerator();
        int[][] matrix = generator.generateSudoku();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(matrix[i][j]);
                if (j != 8) System.out.print(" ,");
            }
            System.out.println();
        }

        System.out.println("###########################");
        int[][] rmatrix = generator.generateRandomSudoku();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(rmatrix[i][j]);
                if (j != 8) System.out.print(" ,");
            }
            System.out.println();
        }
    }
}
