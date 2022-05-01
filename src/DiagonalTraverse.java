import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res= new int[m*n];
        List<Integer> list = new ArrayList<>();
        boolean reverse = false;
        int idx = 0;
        for(int i=0;i<m+n -1;i++){
              int r, c;
              if(i<m){
                  r = i;
                  c = 0;
              }else{
                  r = m-1;
                  c = i - m +1;
              }
              int k = c;
              for(int j=r;j>=0 && k<n;j-- ){
                  list.add(mat[j][k++]);
              }
              if(reverse) Collections.reverse(list);
              for(int l=0;l<list.size();l++){
                  res[idx++] = list.get(l);
              }
              list.clear();
              reverse = !reverse;
        }

        return res;
    }
}
