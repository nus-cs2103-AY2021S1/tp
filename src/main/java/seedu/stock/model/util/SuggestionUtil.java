package seedu.stock.model.util;

public class SuggestionUtil {
    public static int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }

    /**
     * Returns the minimum edit needed to change {@code str1} to {@code str2}.
     * Edit consists of remove, replace, or insert.
     *
     * @param str1 The string to be edited.
     * @param str2 The target string to be achieved.
     * @return The minimum edit needed to change {@code str1} to {@code str2}.
     */
    public static int minimumEditDistance(String str1, String str2) {
        int str1Length = str1.length();
        int str2Length = str2.length();
        int[][] dp = new int[str1Length + 1][str2Length + 1];

        // fill up base case
        for (int i = 0; i < str1Length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < str2Length; j++) {
            dp[0][j] = j;
        }

        // fill up table
        for (int i = 1; i <= str1Length; i++) {
            for (int j = 1; j <= str2Length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[str1Length][str2Length];
    }
}
