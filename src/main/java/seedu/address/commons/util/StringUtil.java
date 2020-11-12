package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code charChain}.
     *   Ignores case, a partial char match is required.
     *   <br>examples:<pre>
     *       containsCharIgnoreCase("ABc", "abc") == true
     *       containsCharIgnoreCase("def", "DEF") == true
     *       containsCharIgnoreCase("ABc", "AB") == true // a partial char match
     *       </pre>
     * @param sentence cannot be null
     * @param charChain cannot be null, cannot be empty, must be a single word
     */
    public static boolean containsCharIgnoreCase(String sentence, String charChain) {
        requireNonNull(sentence);
        requireNonNull(charChain);

        String preppedSentence = sentence.trim().toUpperCase();
        String preppedCharChain = charChain.trim().toUpperCase();
        checkArgument(!preppedCharChain.isEmpty(), "CharChain parameter cannot be empty");
        checkArgument(preppedCharChain.split("\\s+").length == 1, "CharChain parameter should be a "
                + "single word");

        return preppedSentence.contains(preppedCharChain);
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
