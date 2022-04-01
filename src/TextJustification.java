import java.util.ArrayList;
import java.util.List;

public class TextJustification {

  public List<String> fullyJustify(String[] words, int maxLength){
    List<String> results = new ArrayList<>();
    int idx =0;

    while(idx < words.length){
      int curlength = words[idx].length();
      int nxtIdx = idx +1;

      while(nxtIdx < words.length){
        if(words[nxtIdx].length() + 1 + curlength> maxLength ) break;
        curlength +=words[nxtIdx].length() +1;
        nxtIdx++;
      }
      StringBuilder sb = new StringBuilder();
      int diff = nxtIdx - idx -1;
      if(nxtIdx == words.length || diff == 0){
        for(int i=idx; i< nxtIdx;i++){
          sb.append(words[i]+ " ");
        }
        sb.deleteCharAt(sb.length()-1);
        for(int i=sb.length();i<maxLength;i++){
          sb.append(" ");
        }
      }else{
        int spaces = (maxLength - curlength)/diff;
        int slots = (maxLength - curlength) % diff;

        for(int i=idx;i<nxtIdx;i++){
          sb.append(words[i]);
          if(i<nxtIdx -1){
            for(int j=0;j<=(spaces+ ((i- idx)< slots ? 1:0));j++ ){
              sb.append(" ");
            }
          }
        }
      }
      results.add(sb.toString());
      idx = nxtIdx;
    }
    return results;
  }

}
