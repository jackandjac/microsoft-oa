import java.util.HashMap;
import java.util.Map;

public class SolPlusOptimize {
    /**
//    The following code runs fine for smaller inputs but not for the larger inputs. Optimize the code without changing the functionality
//    public int sol(int[] A){
//        int N = A.length(), result = 0;
//        for(int i=0;i<N;i++){
//            for(int j=0;j<N;j++){
//                if(A[i] == A[j]) result = Math.max(result,Math.abs(i-j));
//            }
//        }
//        return result;
//    }
    */
      public int sol(int[] array){
          int result =0;
          if(null == array || array.length ==0 ) return 0;
          Map<Integer, int[]> numap = new HashMap<>();
          for(int i=0;i<array.length;i++){
              numap.putIfAbsent(array[i], new int[]{-1,-1});
              int[] numpos = numap.get(array[i]);
              if(numpos[0] == -1){
                  numpos[0] = i;
              }else{
                  numpos[1] = i;
                  result = Math.max(result, numpos[1]  - numpos[0]);
              }

          }

          return result;
      }
}
