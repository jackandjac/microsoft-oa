import java.util.*;
/*
Suppose we have an unsorted log file of accesses to web resources. Each log entry consists of an access time, the ID of the user making the access, and the resource ID.

The access time is represented as seconds since 00:00:00, and all times are assumed to be in the same day.

Example:
logs1 = [
    ["58523", "user_1", "resource_1"],
    ["62314", "user_2", "resource_2"],
    ["54001", "user_1", "resource_3"],
    ["200", "user_6", "resource_5"],
    ["215", "user_6", "resource_4"],
    ["54060", "user_2", "resource_3"],
    ["53760", "user_3", "resource_3"],
    ["58522", "user_22", "resource_1"],
    ["53651", "user_5", "resource_3"],
    ["2", "user_6", "resource_1"],
    ["100", "user_6", "resource_6"],
    ["400", "user_7", "resource_2"],
    ["100", "user_8", "resource_6"],
    ["54359", "user_1", "resource_3"],
]

Example 2:
logs2 = [
    ["300", "user_1", "resource_3"],
    ["599", "user_1", "resource_3"],
    ["900", "user_1", "resource_3"],
    ["1199", "user_1", "resource_3"],
    ["1200", "user_1", "resource_3"],
    ["1201", "user_1", "resource_3"],
    ["1202", "user_1", "resource_3"]
]

Example 3:
logs3 = [
    ["300", "user_10", "resource_5"]
]

Write a function that takes the logs and returns the resource with the highest number of accesses in any 5 minute window, together with how many accesses it saw.

Expected Output:
most_requested_resource(logs1) # => ('resource_3', 3) [resource_3 is accessed at 53760, 54001, and 54060]
most_requested_resource(logs2) # => ('resource_3', 4) [resource_3 is accessed at 1199, 1200, 1201, and 1202]
most_requested_resource(logs3) # => ('resource_5', 1) [resource_5 is accessed at 300]

Complexity analysis variables:

n: number of logs in the input
*/

public class LogAnalysis {

    public String[] getMostWanted(String[][] logs){
        Map<String, PriorityQueue<Integer>> logMap = new HashMap<>();
        if(logs.length == 1){
            return new String[]{logs[2][0], "" + 1 };
        }
        for(String[] log: logs){
            logMap.computeIfAbsent(log[2], k-> new PriorityQueue<>()).add(Integer.parseInt(log[0]));
        }
        int max = 0;
        String maxId = null;
        for(String key : logMap.keySet()){
            PriorityQueue<Integer> pq = logMap.get(key);

            List<Integer> list = new ArrayList<>();
            while(!pq.isEmpty()){
                list.add(pq.poll());
            }
            int[] dp = new int[list.size()];
            dp[0] = 0;
            int count = 0;
            for(int i = 1; i < list.size(); i++){
                int interval = list.get(i) - list.get(dp[i-1]);
                if( interval < 60* 5 ){
                    dp[i] = dp[i - 1];
                }else{
                    if(i - dp[i-1] == 1){
                        dp[i] = i;
                    }else{
                        int s = dp[i-1];
                        while(s < i && interval> 60 * 5 ){
                            s++;
                            interval = list.get(i) - list.get(s);
                        }
                        if(interval > 60*5){
                            s = i;
                        }
                        dp[i] = s;
                    }
                }
            count = Math.max(count, i - dp[i] + 1);
            }
            if(count > max){
                max = count;
                maxId = key;
            }
        }
        return new String[]{maxId, "" + max};
    }

    public void test(){
        String[][] logs1 = new String[][] {
                { "58523", "user_1", "resource_1" },
                { "62314", "user_2", "resource_2" },
                { "54001", "user_1", "resource_3" },//
                { "200", "user_6", "resource_5" },
                { "215", "user_6", "resource_4" },
                { "54060", "user_2", "resource_3" },//
                { "53760", "user_3", "resource_3" },//
                { "58522", "user_22", "resource_1" },
                { "53651", "user_5", "resource_3" },//
                { "2", "user_6", "resource_1" },
                { "100", "user_6", "resource_6" },
                { "400", "user_7", "resource_2" },
                { "100", "user_8", "resource_6" },
                { "54359", "user_1", "resource_3"},
        };

        String[][] logs2 = new String[][] {
                {"300", "user_1", "resource_3"},
                {"599", "user_1", "resource_3"},
                {"900", "user_1", "resource_3"},
                {"1199", "user_1", "resource_3"},
                {"1200", "user_1", "resource_3"},
                {"1201", "user_1", "resource_3"},
                {"1202", "user_1", "resource_3"}
        };

        String[][] logs3 = new String[][] {
                {"300", "user_10", "resource_5"}
        };
        String[] res = this.getMostWanted(logs1);
        System.out.println(res[0] + " , "+ res[1]);
        res = this.getMostWanted(logs2);
        System.out.println(res[0] + " , "+ res[1]);
        res = this.getMostWanted(logs3);
        System.out.println(res[0] + " , "+ res[1]);
    }

    public static void main(String args[]){
        LogAnalysis la = new LogAnalysis();
        la.test();
    }

}
