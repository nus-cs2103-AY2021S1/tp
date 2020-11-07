package seedu.simplykitchen.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import seedu.simplykitchen.logic.commands.ChangeQuantityCommand;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} contains the {@code word}.
     *   Ignores case, but a full word match is required.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "AB") == false //not a full word match
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
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
     * Returns true if {@code s} represents a non-zero unsigned positive integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedPositiveInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a non-zero signed double with a maximum of 2 decimal places
     * and within the range of (-100,000.00, 0) and (0, +100,000.00)
     * e.g. -99999.99, ..., -2.99, -1, +1, +2.99, ..., +99.999.99 <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "0", " +1.5 " (untrimmed), "+ 1.5" (contains whitespace), "+1a" (contains letters),
     *      "-1.123" (more than 2 decimal places)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroSignedDoubleWithinRange(String s) {
        requireNonNull(s);

        try {
            double value = Double.parseDouble(s);
            String valueValidationRegex = "[+-][0-9]*([.][0-9][0-9]?)?";
            return value != 0.00 && s.matches(valueValidationRegex)
                    && value > -ChangeQuantityCommand.MAX_AMOUNT && value < ChangeQuantityCommand.MAX_AMOUNT;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a non-positive unsigned integer
     * e.g. 0, -1, -2, ..., {@code Integer.MIN_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonPositiveUnsignedDouble(String s) {
        requireNonNull(s);

        try {
            double value = Double.parseDouble(s);
            return value <= 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Returns true if {@code s} represents a integer more than {@code Integer.MAX_VALUE} <br>
     * or less than {@code Integer.MIN_VALUE}
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isIntegerOverflow(String s) {
        requireNonNull(s);

        try {
            double value = Double.parseDouble(s);
            return value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
