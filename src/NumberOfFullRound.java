public class NumberOfFullRound {

  public int numberOfRounds(String loginTime, String logoutTime){
    String[] st = loginTime.split(":");
    String[] et = loginTime.split(":");

    int stmin = Integer.parseInt(st[0])*60 + Integer.parseInt(st[1]);
    int etmin = Integer.parseInt(et[0])*60 + Integer.parseInt(et[1]);
    if(etmin > stmin && etmin -stmin <15) return 0;

    stmin = stmin + (stmin % 15 == 0 ? 0: 15-(stmin%15) );
    etmin = etmin + (stmin> etmin ? 24*60:0 );

    int res = (etmin - stmin) /15 ;

    return res;

  }

}
