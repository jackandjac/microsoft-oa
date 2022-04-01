public class MaxChunckToMakeSort2 {

  public int maxChunckToSorted(int[] arr){
    int[] dp = new int[arr.length +1];
    dp[arr.length] = Integer.MAX_VALUE;
    for(int i= arr.length-1;i>=0;i--){
      dp[i] = Math.min(arr[i], dp[i+1]);
    }

    int res =0;
    int lmax = Integer.MIN_VALUE;
    for(int i=0;i<arr.length;i++ ){
      lmax = Math.max(lmax, arr[i]);
      if(lmax <= dp[i+1]) res++;
    }
    return res;
  }

}
