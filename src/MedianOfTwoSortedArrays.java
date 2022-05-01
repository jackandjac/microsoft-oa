public class MedianOfTwoSortedArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2){
      int[] smarray, bigarray;
      if(nums1.length > nums2.length){
        bigarray = nums1;
        smarray  = nums2;
      }else{
        bigarray = nums2;
        smarray  = nums1;
      }
      int len = bigarray.length + smarray.length;
      int sl = 0;
      int sr = smarray.length -1;

      while(true){
        int smid = Math.floorDiv(sl+ sr, 2);
        int bmid = len/2 - smid -2;

        int sleft = (smid < 0) ? Integer.MIN_VALUE: smarray[smid];
        int sright = (smid +1 > smarray.length ) ? Integer.MAX_VALUE: smarray[smid+1];

        int bleft = (bmid< 0 ) ? Integer.MIN_VALUE : bigarray[bmid];
        int bright = (bmid +1 > bigarray.length ) ? Integer.MAX_VALUE : bigarray[bmid +1];

        if(sleft <= bright && bleft <= sright){
          if(len %2 ==1){
            return Math.min(sright,bright);
          }else{
            return (Math.max(sleft ,bleft) + Math.min(sright,bright))/2.0;
          }
        }else{
          if(bleft > sright){
            sl = smid +1;
          } else if(sleft> bright ){
            sr = smid -1;
          }
        }

      }


  }

}
