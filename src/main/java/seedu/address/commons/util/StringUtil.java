package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, support fuzzy match
     *   <br>examples:<pre>
     *       matchesWordIgnoreCase("ABc def", "abc", true) == true // a full match
     *       matchesWordIgnoreCase("ABc def", "abb DEF") == true // a partial match
     *       matchesWordIgnoreCase("ABc def", "ABadncbas") == false //not a match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     * @param isFuzzy true if the matching is fuzzy, false if it must be a full match
     */
    public static boolean matchesWordIgnoreCase(String sentence, String word, boolean isFuzzy) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return isFuzzy
                ? isFuzzyMatched(sentence.toLowerCase(), preppedWord.toLowerCase())
                : Arrays.stream(wordsInPreppedSentence).anyMatch(preppedWord::equalsIgnoreCase);
    }

    private static boolean isFuzzyMatched(String sentence, String keyword) {
        int lKeyword = keyword.length();
        int lTask = sentence.length();

        // allowed a fifth of the characters in the keyword to be mismatched
        final int fuzzyLimit = lKeyword / 5;

        // if keyword is longer or is empty, no match can be found
        if (lKeyword > lTask || lKeyword == 0) {
            return false;
        }

        for (int i = 0; i <= lTask - lKeyword; ++i) {
            boolean matchFound = true;
            int fuzzyCount = 0;
            for (int j = 0; j < lKeyword; ++j) {
                boolean isCharMatched = sentence.charAt(i + j) == keyword.charAt(j);
                if (!isCharMatched && fuzzyCount < fuzzyLimit) {
                    fuzzyCount++;
                    continue;
                }

                if (!isCharMatched) {
                    matchFound = false;
                    break;
                }
            }
            if (matchFound) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
