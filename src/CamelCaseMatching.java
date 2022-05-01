import java.util.ArrayList;
import java.util.List;

public class CamelCaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pat){
        List<Boolean> result = new ArrayList<>();
        for(String query:queries){
            int idx =0, i;
            for(i=0;i<query.length();i++){
                char ch = query.charAt(i);
                if(idx >= pat.length() && ch >='A' && ch <= 'Z' )
                    break;
                if(idx <pat.length() && ch == pat.charAt(idx)){
                    idx++;
                }else if(ch >='A' && ch <='Z' && ch != pat.charAt(idx)){
                    break;
                }
            }
                if(i == query.length() && idx == pat.length()){
                    result.add(true);
                }else{
                    result.add(false);
                }

        }
        return result;
    }
}
