public class LongestCommonSubstring {
  public String lcSubstr(String word1, String word2){
    int[][] pos= new int[word1.length()+1][word2.length()+1];
    String res = "";
    int[] bound;
    for(int i=1;i<=word1.length();i++){
      for(int j=1;j<=word2.length();j++){
        if(word1.charAt(i-1) == word2.charAt(j-1)){
          pos[i][j] = pos[i-1][j-1] +1;
          if(pos[i][j]> res.length() ){
            res= word1.substring(i-1, i-1+ pos[i][j]);
          }
        }

      }
    }


    return res;
  }

  public int lcSubstring(String word1, String word2){
    return -1;
  }

}
