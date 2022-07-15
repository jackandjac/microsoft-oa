package dp;

import java.util.HashSet;
import java.util.Set;

public class ValidateNonogram {
    public boolean validateIt(char[][] matrix, int[][] rowCommands, int[][] colCommands) {
        int m = matrix.length;
        int n = matrix[0].length;
        String[][] rdp = new String[m][n];
        String[][] cdp = new String[m][n];
        Set<String> rset = new HashSet<>();
        Set<String> cset = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cur = matrix[i][j];
                if (i == 0) {
                    if (cur == 'w') {
                        cdp[i][j] = "0";
                    } else {
                        cdp[i][j] = "1";
                    }
                }

                if (j == 0) {
                    if (cur == 'w') {
                        rdp[i][j] = "0";
                    } else {
                        rdp[i][j] = "1";
                    }
                }

                if (i > 0 && j > 0) {
                    // rdp case
                    if (cur == 'w') {
                        if (rdp[i][j - 1].equals("0") || rdp[i][j - 1].endsWith(",")) {
                            rdp[i][j] = rdp[i][j - 1];
                        } else {
                            rdp[i][j] = rdp[i][j - 1] + ",";
                        }

                        //cdp
                        if (cdp[i - 1][j].equals("0") || cdp[i - 1][j].endsWith(",")) {
                            cdp[i][j] = cdp[i - 1][j];
                        } else {
                            cdp[i][j] = cdp[i - 1][j] + ",";
                        }

                    } else {
                        if (rdp[i][j - 1].equals("0")) {
                            rdp[i][j] = "1";
                        } else if (rdp[i][j - 1].endsWith(",")) {
                            rdp[i][j] = rdp[i][j - 1] + "1";
                        } else {
                            if (rdp[i][j - 1].contains(",")) {
                                int comma = rdp[i][j - 1].indexOf(",");
                                String head = rdp[i][j - 1].substring(0, comma);
                                String tail = rdp[i][j - 1].substring(comma + 1);
                                rdp[i][j] = head + "," + (Integer.parseInt(tail) + 1);
                            } else {
                                rdp[i][j] = "" + (Integer.parseInt(rdp[i][j - 1]) + 1);
                            }
                        }

                        //now let's process cdp
                        if(cdp[i-1][j].equals("0")){
                            cdp[i][j] = "1";
                        }else if(cdp[i-1][j].endsWith(",")){
                            cdp[i][j] = cdp[i-1][j] + "1";
                        }else{
                            if (cdp[i-1][j].contains(",")) {
                                int comma = cdp[i-1][j].indexOf(",");
                                String head = cdp[i-1][j].substring(0, comma);
                                String tail = cdp[i-1][j].substring(comma + 1);
                                cdp[i][j] = head + "," + (Integer.parseInt(tail) + 1);
                            } else {
                                cdp[i][j] = "" + (Integer.parseInt(cdp[i-1][j]) + 1);
                            }
                        }
                    }

                }
                cset.add(cdp[i][j]);
                rset.add(rdp[i][j]);
            }
        }

        for(int[] rc: rowCommands){
            String cmd = "";
            if(rc.length == 1){
                cmd += rc[0];
            }else{
                cmd = rc[0]+ "," + rc[1];
            }
            if(!rset.contains(cmd)) return false;
        }
        for(int[] cc: colCommands){
            String cmd = "";
            if(cc.length == 1){
                cmd += cc[0];
            }else{
                cmd = cc[0]+ "," + cc[1];
            }
            if(!cset.contains(cmd)) return false;
        }

        return true;
    }
}
