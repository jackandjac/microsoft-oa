import java.util.*;

public class SnakeTravel {
/*
Question 1
We have a two-dimensional board game involving snakes. The board has two types of squares on it: +'s represent impassable squares where snakes cannot go, and 0's represent squares through which snakes can move.

Here is an example board:

col-->        0  1  2  3  4  5  6  7  8
           +---------------------------
row      0 |  +  +  +  +  +  +  +  0  0
 |       1 |  +  +  0  0  0  0  0  +  +
 |       2 |  0  0  0  0  0  +  +  0  +
 v       3 |  +  +  0  +  +  +  +  0  0
         4 |  +  +  0  0  0  0  0  0  +
         5 |  +  +  0  +  +  0  +  0  +
Find the rows and columns in which the snake can move free freely.

Sample inputs:
board1 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '0', '0'},
{'0', '0', '+', '0', '0', '0', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[1], [3, 5]]

board2 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '+', '0'},
{'0', '0', '+', '0', '0', '0', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[], [4]]

board3 =
{{'+', '+', '+', '0', '+', '0', '0'},
{'0', '0', '0', '0', '0', '0', '0'},
{'0', '0', '+', '+', '0', '+', '0'},
{'0', '0', '0', '0', '+', '0', '0'},
{'+', '+', '+', '0', '0', '0', '+'}}
Expected output = [[1], []]

board4 = {{'+'}}
Expected output = [[], []]


Question 2
We have a two-dimensional board game involving snakes. The board has two types of squares on it: +'s represent impassable squares where snakes cannot go, and 0's represent squares through which snakes can move.

Snakes may move in any of four directions - up, down, left, or right - one square at a time, but they will never return to a square that they've already visited. If a snake enters the board on an edge square, we want to catch it at a different exit square on the board's edge.

The snake is familiar with the board and will take the route to the nearest reachable exit, in terms of the number of squares it has to move through to get there. Note that there may not be a reachable exit.

Here is an example board:

col-->        0  1  2  3  4  5  6  7  8
           +---------------------------
row      0 |  +  +  +  +  +  +  +  0  0
 |       1 |  +  +  0  0  0  0  0  +  +
 |       2 |  0  0  0  0  0  +  +  0  +
 v       3 |  +  +  0  +  +  +  +  0  0
         4 |  +  +  0  0  0  0  0  0  +
         5 |  +  +  0  +  +  0  +  0  +
Write a function that takes a rectangular board with only +'s and O's, along with a starting point on the edge of the board, and returns the coordinates of the nearest exit to which it can travel. If there is a tie, return any of the nearest exits.

Sample inputs:
board1 = [['+', '+', '+', '+', '+', '+', '+', '0', '0'],
['+', '+', '0', '0', '0', '0', '0', '+', '+'],
['0', '0', '0', '0', '0', '+', '+', '0', '+'],
['+', '+', '0', '+', '+', '+', '+', '0', '0'],
['+', '+', '0', '0', '0', '0', '0', '0', '+'],
['+', '+', '0', '+', '+', '0', '+', '0', '+']]
start1_1 = (2, 0) # Expected output = (5, 2)
start1_2 = (0, 7) # Expected output = (0, 8)
start1_3 = (5, 2) # Expected output = (2, 0) or (5, 5)
start1_4 = (5, 5) # Expected output = (5, 7)

board2 = [['+', '+', '+', '+', '+', '+', '+'],
['0', '0', '0', '0', '+', '0', '+'],
['+', '0', '+', '0', '+', '0', '0'],
['+', '0', '0', '0', '+', '+', '+'],
['+', '+', '+', '+', '+', '+', '+']]
start2_1 = (1, 0) # Expected output = null (or a special value representing no possible exit)
start2_2 = (2, 6) # Expected output = null

board3 = [['+', '0', '+', '0', '+',],
['0', '0', '+', '0', '0',],
['+', '0', '+', '0', '+',],
['0', '0', '+', '0', '0',],
['+', '0', '+', '0', '+']]
start3_1 = (0, 1) # Expected output = (1, 0)
start3_2 = (4, 1) # Expected output = (3, 0)
start3_3 = (0, 3) # Expected output = (1, 4)
start3_4 = (4, 3) # Expected output = (3, 4)

board4 = [['+', '0', '+', '0', '+',],
['0', '0', '0', '0', '0',],
['+', '+', '+', '+', '+',],
['0', '0', '0', '0', '0',],
['+', '0', '+', '0', '+']]
start4_1 = (1, 0) # Expected output = (0, 1)
start4_2 = (1, 4) # Expected output = (0, 3)
start4_3 = (3, 0) # Expected output = (4, 1)
start4_4 = (3, 4) # Expected output = (4, 3)

board5 = [['+', '0', '0', '0', '+',],
['+', '0', '+', '0', '+',],
['+', '0', '0', '0', '+',],
['+', '0', '+', '0', '+']]
start5_1 = (0, 1) # Expected output = (0, 2)
start5_2 = (3, 1) # Expected output = (0, 1)

All test cases:
findExit(board1, start1_1) => (5, 2)
findExit(board1, start1_2) => (0, 8)
findExit(board1, start1_3) => (2, 0) or (5, 5)
findExit(board1, start1_4) => (5, 7)
findExit(board2, start2_1) => null (or a special value representing no possible exit)
findExit(board2, start2_2) => null
findExit(board3, start3_1) => (1, 0)
findExit(board3, start3_2) => (3, 0)
findExit(board3, start3_3) => (1, 4)
findExit(board3, start3_4) => (3, 4)
findExit(board4, start4_1) => (0, 1)
findExit(board4, start4_2) => (0, 3)
findExit(board4, start4_3) => (4, 1)
findExit(board4, start4_4) => (4, 3)
findExit(board5, start5_1) => (0, 2)
findExit(board5, start5_2) => (0, 1)

Complexity Analysis:

r: number of rows in the board
c: number of columns in the board
*/

    public List<List<Integer>> snakeTravel(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n];
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        for(int i = 0; i < m; i++){
            int count = 0;
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '+'){
                    dp[j]++;
                    count++;
                }
                if(i == m -1 && dp[j] == 0){
                    cols.add(j);
                }
            }
            if(count == 0) rows.add(i);
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(rows);
        result.add(cols);
        return result;
    }

    public int[] snakTravelII(char[][] board, int[] entry){
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1},{0, -1}};
        int m = board.length;
        int n = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        queue.offer(entry);

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];
                visited[r][c] = true;
                if((r == 0 || c == 0 || r == m - 1 || c == n -1) && !(r == entry[0] && c == entry[1])){
                    return cur;
                }
                for(int j = 0; j < dirs.length; j++){
                    int row = r + dirs[j][0];
                    int col = c + dirs[j][1];
                    if(row >=0 && col >=0 && row < m && col < n && !visited[row][col] && board[row][col] == '0' ){
                        queue.offer(new int[]{row, col});
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }
    public static void main(String args[]){
        char[][] board1 =
                {{'+', '+', '+', '0', '+', '0', '0'},
                        {'0', '0', '0', '0', '0', '0', '0'},
                        {'0', '0', '+', '0', '0', '0', '0'},
                        {'0', '0', '0', '0', '+', '0', '0'},
                        {'+', '+', '+', '0', '0', '0', '+'}};
        char[][] board2 = {{'+', '+', '+', '0', '+', '0', '0'},
                {'0', '0', '0', '0', '0', '+', '0'},
                {'0', '0', '+', '0', '0', '0', '0'},
                {'0', '0', '0', '0', '+', '0', '0'},
                {'+', '+', '+', '0', '0', '0', '+'}};


        char[][] board3 =
                {{'+', '+', '+', '0', '+', '0', '0'},
                        {'0', '0', '0', '0', '0', '0', '0'},
                        {'0', '0', '+', '+', '0', '+', '0'},
                        {'0', '0', '0', '0', '+', '0', '0'},
                        {'+', '+', '+', '0', '0', '0', '+'}};
        SnakeTravel snake = new SnakeTravel();
        List<List<Integer>> result = snake.snakeTravel(board1);
        result = snake.snakeTravel(board2);
        result = snake.snakeTravel(board3);
        System.out.println("end");
        snake.test2();

    }

    public void test2() {
        char[][] board1 = {{'+', '+', '+', '+', '+', '+', '+', '0', '0'},
                {'+', '+', '0', '0', '0', '0', '0', '+', '+'},
                {'0', '0', '0', '0', '0', '+', '+', '0', '+'},
                {'+', '+', '0', '+', '+', '+', '+', '0', '0'},
                {'+', '+', '0', '0', '0', '0', '0', '0', '+'},
                {'+', '+', '0', '+', '+', '0', '+', '0', '+'}};
        int[] start1_1 = {2, 0}; // # Expected output = {5, 2}
        int[] res = this.snakTravelII(board1, start1_1);
        System.out.println("Expected output {" + res[0] + " , "+ res[1] +"}");
        int[] start1_2 = {0, 7}; // Expected output = {0, 8}
        res = this.snakTravelII(board1, start1_2);
        System.out.println("Expected output {" + res[0] + " , "+ res[1] +"}");
        int[] start1_3 = {5, 2}; // Expected output = {2, 0} or {5, 5}
        res = this.snakTravelII(board1, start1_3);
        System.out.println("Expected output {" + res[0] + " , "+ res[1] +"}");
        int[] start1_4 = {5, 5}; // Expected output = {5, 7}
        res = this.snakTravelII(board1, start1_4);
        System.out.println("Expected output {" + res[0] + " , "+ res[1] +"}");


        char[][] board2 = {{'+', '+', '+', '+', '+', '+', '+'},
                {'0', '0', '0', '0', '+', '0', '+'},
                {'+', '0', '+', '0', '+', '0', '0'},
                {'+', '0', '0', '0', '+', '+', '+'},
                {'+', '+', '+', '+', '+', '+', '+'}};
        int[] start2_1 = {1, 0};  // ; // Expected output = null {or a special value representing no possible exit}
        int[] start2_2 = {2, 6};  // ; // Expected output = null

        char[][] board3 = {{'+', '0', '+', '0', '+',},
                {'0', '0', '+', '0', '0',},
                {'+', '0', '+', '0', '+',},
                {'0', '0', '+', '0', '0',},
                {'+', '0', '+', '0', '+'}};
        int[] start3_1 = {0, 1};  //# Expected output = {1, 0}
        int[] start3_2 = {4, 1};  //# Expected output = {3, 0}
        int[] start3_3 = {0, 3};  // # Expected output = {1, 4}
        int[] start3_4 = {4, 3};  //# Expected output = {3, 4}

        char[][] board4 = {{'+', '0', '+', '0', '+',},
                {'0', '0', '0', '0', '0',},
                {'+', '+', '+', '+', '+',},
                {'0', '0', '0', '0', '0',},
                {'+', '0', '+', '0', '+'}};
        int[] start4_1 = {1, 0};  //# Expected output = {0, 1}
        int[] start4_2 = {1, 4};  // # Expected output = {0, 3}
        int[] start4_3 = {3, 0};  // # Expected output = {4, 1}
        int[] start4_4 = {3, 4};  // # Expected output = {4, 3}

        char[][] board5 = {{'+', '0', '0', '0', '+',},
                {'+', '0', '+', '0', '+',},
                {'+', '0', '0', '0', '+',},
                {'+', '0', '+', '0', '+'}};
        int[] start5_1 = {0, 1};  // # Expected output = {0, 2}
        int[] start5_2 = {3, 1};  //# Expected output = {0, 1}
    }

    public static boolean found = false;
    public static int[][] locateWord(char[][] board, String word){
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] result = new int[word.length()][2];
        Map<Character, List<int[]>> idxMap = new HashMap<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                idxMap.computeIfAbsent(board[i][j], k-> new ArrayList<>()).add(new int[]{i,j});
            }
        }
        for(int[] loc: idxMap.get(word.charAt(0))){
            int row = loc[0];
            int col = loc[1];
            backtrack(row, col, 0, board, result, word, visited);
            if(found) break;
        }
        return result;
    }

    public static void backtrack(int row, int col, int idx, char[][] board, int[][] result, String word, boolean[][] visited){
        if(idx == word.length() - 1 && board[row][col] == word.charAt(idx)){
            result[idx]= new int[]{row, col};
            found = true;
            return;
        }
        int[][]  dirs = new int[][]{{1,0},{0,1}};
        if(word.charAt(idx) == board[row][col]){
            char cur = board[row][col];
            result[idx] = new int[]{row, col};
            visited[row][col] = true;
            for(int i = 0; i < dirs.length; i++){
                int r = row + dirs[i][0];
                int c = col + dirs[i][1];
                if(r >= 0 && c >=0 && r < board.length && c < board[0].length && !visited[r][c]){
                    backtrack(r,c, idx +1, board, result, word, visited);
                }
            }
            visited[row][col] = false;

        }

    }
}
