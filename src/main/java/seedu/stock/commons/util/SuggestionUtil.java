package seedu.stock.commons.util;

public class SuggestionUtil {

    /**
     * Computes the minimum of three integers.
     *
     * @param x The first integer.
     * @param y The second integer.
     * @param z The third integer.
     * @return The minimum of those three integers.
     */
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
        for (int i = 0; i <= str1Length; i++) {
            dp[i][0] = 2 * i;
        }
        for (int j = 0; j <= str2Length; j++) {
            dp[0][j] = 2 * j;
        }

        // fill up table
        // insertion cost: 2, remove cost: 2, replace cost: 3
        for (int i = 1; i <= str1Length; i++) {
            for (int j = 1; j <= str2Length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1] + 3, dp[i - 1][j] + 2, dp[i][j - 1] + 2);
                }
            }
        }

        int result = dp[str1Length][str2Length];
        assert result >= 0 : "Invalid edit distance";
        return result;
    }
}
