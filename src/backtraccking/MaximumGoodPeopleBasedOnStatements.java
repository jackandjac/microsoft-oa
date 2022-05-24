package backtraccking;

import java.util.Arrays;

/**
 *  2151. Maximum Good People Based on Statements
 * */
public class MaximumGoodPeopleBasedOnStatements {
    private int max = 0;
    public int maximumGood(int[][] statements) {
        int n = statements.length;
        int[] indicators = new int[n];
        Arrays.fill(indicators, 2);
        dfs(0, indicators, statements);
        return max;
    }

    private void dfs(int idx, int[] indicators, int[][] statements){
        // to do Ending conditions
        int n = statements.length;
        if(idx == n){
            int acc = 0;
            for(int i = 0; i < n; i++){
                if(indicators[i] == 1) acc++;
            }
            max = Math.max(acc, max);
        }

        int[] temp = indicators.clone();
        if (temp[idx] == 0){
            dfs(idx + 1, temp, statements);
            return;
        } else if (temp[idx] == 1){
            int[] stmt = statements[idx];
            for (int i=0; i<n; i++){
                if( (stmt[i] == 1 && temp[i] == 0) || (stmt[i] == 0 && temp[i] == 1)){
                    return;
                }
                if(stmt[i] != 2) temp[i] = stmt[i];
            }
            dfs(idx + 1, temp, statements);
        }else{
            temp[idx] = 0;
            dfs(idx + 1, temp, statements);
            temp[idx] = 1;
            int[] stmt = statements[idx];
            for(int i=0; i<n; i++){
                if((stmt[i] == 1 && temp[i] == 0) || (stmt[i] == 0 && temp[i] == 1)){
                    return;
                }
                if(stmt[i] != 2) temp[i] = stmt[i];
            }
            dfs(idx + 1, temp, statements);
        }
    }
}
