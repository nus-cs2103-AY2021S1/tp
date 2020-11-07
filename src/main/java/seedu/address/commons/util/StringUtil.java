package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     * Ignores case, but a full word match is required.
     * <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     *
     * @param sentence cannot be null
     * @param word     cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        return Arrays.stream(wordsInPreppedSentence)
                .anyMatch(preppedWord::equalsIgnoreCase);
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
     *
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

    /**
     * Returns true if String is an integer.
     */
    public static boolean isInteger(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if String is an integer and is between 1 and 2,147,483,647 inclusive.
     */
    public static boolean isValidIndexRange(String s) {
        if (!isInteger(s)) {
            return false;
        }
        BigInteger index = new BigInteger(s);
        return index.compareTo(BigInteger.valueOf(1)) >= 0
                && index.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0;
    }

    /**
     * Breaks a string using newline. stringBreaker will try
     * to break strings into words so that sentences keeps its meaning.
     *
     * @param s               String to format.
     * @param charBuffer      Buffer for the number of characters in a line.
     * @param charPerLine     Approximate chars per line.
     * @param upperBufferOnly True if line must be minimum the charPerLine specified.
     * @return Formatted String.
     */
    public static String stringBreaker(String s, int charBuffer, int charPerLine, boolean upperBufferOnly) {
        StringBuilder str = new StringBuilder(s);
        int currPos = 0;
        int currLineLen = 0;
        while (currPos < str.length()) {
            if (currLineLen >= charPerLine + charBuffer) {
                str.insert(currPos, '\n');
                currLineLen = 0;
            } else if (!upperBufferOnly && currLineLen >= charPerLine - charBuffer) {
                if (str.charAt(currPos) == ' ') {
                    while (str.charAt(currPos) == ' ') {
                        str.deleteCharAt(currPos);
                    }
                    str.insert(currPos, '\n');
                    currLineLen = 0;
                }
            } else if (upperBufferOnly && currLineLen >= charPerLine) {
                if (str.charAt(currPos) == ' ') {
                    while (str.charAt(currPos) == ' ') {
                        str.deleteCharAt(currPos);
                    }
                    str.insert(currPos, '\n');
                    currLineLen = 0;
                }
            }
            currPos++;
            currLineLen++;
        }
        return str.toString();
    }

    /**
     * Breaks a string using newline. stringBreaker will try
     * to break strings into words so that sentences keeps its meaning.
     * If string exceeds maxLines, append ellipsis while keeping length
     * within buffer.
     *
     * @param s               String to format.
     * @param charBuffer      Buffer for the number of characters in a line.
     * @param charPerLine     Approximate chars per line.
     * @param upperBufferOnly True if line must be minimum the charPerLine specified.
     * @param maxLines        Max Lines of the formatted string.
     * @param ellipsis        ellipsis for overflow text.
     * @return Formatted String.
     */
    public static String stringBreaker(String s, int charBuffer, int charPerLine,
                                       boolean upperBufferOnly, int maxLines, String ellipsis) {
        if (charBuffer < 0 || charPerLine < 0 || maxLines < 0 || ellipsis.length() > charPerLine + charBuffer) {
            return "";
        }
        StringBuilder str = new StringBuilder(s);

        int currPos = 0;
        int currLineLen = 0;
        int lineNum = 0;
        while (currPos < str.length() && lineNum < maxLines) {
            if (currLineLen >= charPerLine + charBuffer) {
                str.insert(currPos, '\n');
                currLineLen = -1;
                lineNum++;
            } else if (!upperBufferOnly && currLineLen >= charPerLine - charBuffer) {
                if (str.charAt(currPos) == ' ') {
                    while (str.charAt(currPos) == ' ') {
                        str.deleteCharAt(currPos);
                    }
                    str.insert(currPos, '\n');
                    currLineLen = -1;
                    lineNum++;
                }
            } else if (upperBufferOnly && currLineLen >= charPerLine) {
                if (str.charAt(currPos) == ' ') {
                    while (str.charAt(currPos) == ' ') {
                        str.deleteCharAt(currPos);
                    }
                    str.insert(currPos, '\n');
                    currLineLen = -1;
                    lineNum++;
                }
            }
            currPos++;
            currLineLen++;
        }
        // if there are still text left, append ellipsis
        if (currPos < str.length()) {
            return str.substring(0, currPos - ellipsis.length()).concat(ellipsis);
        }
        return str.toString();
    }

    /**
     * Break the string at keywords founds with newline.
     * @param s String to break.
     * @param strict Break only if all the keywords are found.
     * @param keywords Keywords to break.
     * @return Formatted string.
     */
    public static String stringBreakerByKeywords(String s, boolean strict, String... keywords) {
        StringBuilder result = new StringBuilder(s);

        for (String keyword : keywords) {
            int pos = result.lastIndexOf(keyword);
            if (pos < 0 && strict) {
                return s;
            } else if (pos > 0) {
                result.insert(pos, '\n');
            }
        }
        return result.toString();
    }
}
