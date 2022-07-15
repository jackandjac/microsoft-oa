import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MiniMahjong {

    public boolean findCompleteHand(String tile){
        Map<Integer, Integer> counter = new HashMap<>();
        for(char c:tile.toCharArray()){
            int key = c - '0';
            counter.merge(key, 1, Integer::sum);
        }

        boolean hasPair = false;
        for(int key: counter.keySet()){
            int size = counter.get(key);
            if(size % 3 == 0){
                continue;
            }else{
                if(size % 3 == 2 && !hasPair){
                    hasPair = true;
                    continue;
                }else{
                    return false;
                }
            }
        }
        return hasPair;

    }

    public boolean findCompleteHandI(String tile){
        int[] nums = new int[10];
        for(char c: tile.toCharArray()){
            nums[c - '0']++;
        }
        boolean hasPair = false;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 3 == 0){
                continue;
            }else{
                if(nums[i] % 3 == 2 && !hasPair){
                    hasPair = true;
                }else{
                    return false;
                }
            }
        }
        return hasPair;
    }

    public boolean findCompleteHandII(String tile){

        int[] nums = new int[10];
        for(char c : tile.toCharArray()){
            nums[c - '0']++;
        }
        for(int i = 0; i < 10; i++){
            nums[i] -= 2;
            int[] temp = Arrays.copyOf(nums,10);
            boolean res = backtrack(temp);
            if(res) return true;
            nums[i] +=2;
        }
        return false;
    }

    public boolean findHandII(String tile){
        int[] nums = new int[10];
        for(char c: tile.toCharArray()){
            nums[c - '0']++;
        }
        for(int i = 0; i < 10; i++){
            if(nums[i] == 0 ) continue;
            nums[i] -= 2;
            int[] temp = Arrays.copyOf(nums,10);
            boolean  res = dfs(temp);
            if(res) return res;
            nums[i] += 2;
        }
        return false;
    }

    private boolean dfs(int[] nums){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[i] %= 3;
            }
        }

        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) continue;
            if(nums[i] < 0 ) return false;
            if(i > 7 ) continue;;

            int idx1 = i + 1;
            int idx2 = i + 2;
            nums[idx1] -= nums[i];
            nums[idx2] -= nums[i];
            nums[i] = 0;
        }
        return nums[8] == 0 && nums[9] == 0;
    }

    public boolean backtrack(int[] nums){
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[i] %= 3;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0) continue;
            if(nums[i] < 0) return false;

            if(i > 7) continue;
            int idx1 = i + 1;
            int idx2 = i + 2;
            nums[idx1] -= nums[i];
            nums[idx2] -= nums[i];
            nums[i] = 0;
        }
        return nums[8] == 0 && nums[9] == 0;
    }



    public static void main(String[] args) {
        MiniMahjong majong = new MiniMahjong();

        majong.test1();
        majong.test2();

    }
    public void test2(){
        String[] samples = new String[]{
                "11123", "12131", "11123455", "11122334",
                "11234", "123456", "11133355577", "11223344556677",
                "12233444556677", "11234567899", "00123457", "0012345",
                "11890", "99", "111223344"

        };
        boolean[] results = new boolean[]{
                true,true,true,true,
                true,false,true,true,
                true,false,false,false,
                false,true,false
        };
        System.out.println("#############Test 2 Start ###################");
        for(int i = 0; i < samples.length; i++){
            if(this.findHandII(samples[i]) != results[i]){
                System.out.println("result ["+ i + "] is incorrect");
            } else{
                System.out.println("result[" + i + "] is " + results[i] + ", and this is expected result.");
            }
        }
        System.out.println("#############Test 2 Ends ###################");
    }

    public void test1(){

        String[] sample = new String[]{
                "11133555", "111333555", "00000111", "13233121", "11223344555", "99999999", "999999999", "9", "99", "000022", "888889", "889", "88888844", "77777777777777", "1111111", "1111122222"
        };
        boolean[] results = new boolean[]{true, false, true, true, false, true, false, false, true, false, false, false, true, true, false, false};
        System.out.println("#############Test 1 Start ###################");
        for(int i = 0; i < sample.length; i++){
            if(this.findCompleteHandI(sample[i]) != results[i]){
                System.out.println("result ["+ i + "] is incorrect");
            } else{
                System.out.println("result[" + i + "] is " + results[i] + ", and this is expected result.");
            }
        }
        System.out.println("#############Test 1 Ends ###################");
    }
}
