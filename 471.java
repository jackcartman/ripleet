/* DP, the formula is
dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j])
Note that you can find the string itself in each loop to be abbreviated.
Complexity: O(n^4) replaceAll requires O(n) */

public class Solution {
    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];
        for (int l = 0; l < s.length(); l++) {
            for (int i = 0; i < s.length() - l; i++) {
                int j = i + l;
                String substr = s.substring(i, j + 1);
                if (l < 4) dp[i][j] = substr;
                else {
                    dp[i][j] = substr;
                    for (int k = i; k < j; k++) {
                        if ((dp[i][k] + dp[k + 1][j]).length() < dp[i][j].length()) dp[i][j] = dp[i][k] + dp[k + 1][j];
                    }
                    for (int k = 0; k < l; k++) {
                        String repeatStr = substr.substring(0, k + 1);
                        if (repeatStr != null && substr.length() % repeatStr.length() == 0 
                            && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length() / repeatStr.length() + "[" + dp[i][i+k] + "]";
                            if(ss.length() < dp[i][j].length()) {
                            dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
}
