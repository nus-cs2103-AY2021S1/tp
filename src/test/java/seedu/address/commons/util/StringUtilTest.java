package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

public class StringUtilTest {

    //---------------- Tests for isNonZeroUnsignedInteger --------------------------------------

    @Test
    public void isNonZeroUnsignedInteger() {

        // EP: empty strings
        assertFalse(StringUtil.isNonZeroUnsignedInteger("")); // Boundary value
        assertFalse(StringUtil.isNonZeroUnsignedInteger("  "));

        // EP: not a number
        assertFalse(StringUtil.isNonZeroUnsignedInteger("a"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("aaa"));

        // EP: zero
        assertFalse(StringUtil.isNonZeroUnsignedInteger("0"));

        // EP: zero as prefix
        assertTrue(StringUtil.isNonZeroUnsignedInteger("01"));

        // EP: signed numbers
        assertFalse(StringUtil.isNonZeroUnsignedInteger("-1"));
        assertFalse(StringUtil.isNonZeroUnsignedInteger("+1"));

        // EP: numbers with white space
        assertFalse(StringUtil.isNonZeroUnsignedInteger(" 10 ")); // Leading/trailing spaces
        assertFalse(StringUtil.isNonZeroUnsignedInteger("1 0")); // Spaces in the middle

        // EP: number larger than Integer.MAX_VALUE
        assertFalse(StringUtil.isNonZeroUnsignedInteger(Long.toString(Integer.MAX_VALUE + 1)));

        // EP: valid numbers, should return true
        assertTrue(StringUtil.isNonZeroUnsignedInteger("1")); // Boundary value
        assertTrue(StringUtil.isNonZeroUnsignedInteger("10"));
    }


    //---------------- Tests for containsWordIgnoreCase --------------------------------------

    /*
     * Invalid equivalence partitions for word: null, empty, multiple words
     * Invalid equivalence partitions for sentence: null
     * The four test cases below test one invalid input at a time.
     */

    @Test
    public void containsWordIgnoreCase_nullWord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                StringUtil.matchesWordIgnoreCase("typical sentence", null, false));
    }

    @Test
    public void containsWordIgnoreCase_emptyWord_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, "Word parameter cannot be empty", ()
            -> StringUtil.matchesWordIgnoreCase("typical sentence", "  ", false));
    }

    @Test
    public void containsWordIgnoreCase_nullSentence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.matchesWordIgnoreCase(null, "abc", false));
    }

    /*
     * Valid equivalence partitions for word:
     *   - any word
     *   - word containing symbols/numbers
     *   - word with leading/trailing spaces
     *
     * Valid equivalence partitions for sentence:
     *   - empty string
     *   - one word
     *   - multiple words
     *   - sentence with extra spaces
     *
     * Possible scenarios returning true:
     *   - matches first word in sentence
     *   - last word in sentence
     *   - middle word in sentence
     *   - matches multiple words
     *
     * Possible scenarios returning false:
     *   - query word matches part of a sentence word
     *   - sentence word matches part of the query word
     *
     * The test method below tries to verify all above with a reasonably low number of test cases.
     */

    @Test
    public void containsWordIgnoreCase_validInputs_correctResult() {

        // Empty sentence
        assertFalse(StringUtil.matchesWordIgnoreCase("", "abc", true)); // Boundary case
        assertFalse(StringUtil.matchesWordIgnoreCase("", "abc", false)); // Boundary case
        assertFalse(StringUtil.matchesWordIgnoreCase("    ", "123", true));
        assertFalse(StringUtil.matchesWordIgnoreCase("    ", "123", false));

        // Matches a partial word
        assertTrue(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bb", true));
        assertFalse(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bb", false));

        // no. mismatched words exceed limit
        assertFalse(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bbbb", true));
        assertFalse(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bbbb", false));

        // no. mismatched words within limit
        assertTrue(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bbbbccc", true));
        assertFalse(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "bbbbccc", false));

        // Matches word in the sentence, different upper/lower case letters
        assertTrue(StringUtil.matchesWordIgnoreCase("aaa bBb ccc", "Bbb", false)); // First word (boundary case)
        assertTrue(StringUtil.matchesWordIgnoreCase("aaa bBb ccc@1", "CCc@1", false)); // Last word (boundary case)
        assertTrue(StringUtil.matchesWordIgnoreCase("  AAA   bBb   ccc  ", "aaa", false)); // Sentence has extra spaces
        assertTrue(StringUtil.matchesWordIgnoreCase("Aaa", "aaa", false)); // Only one word in sentence (boundary case)
        assertTrue(StringUtil.matchesWordIgnoreCase("aaa bbb ccc", "  ccc  ", false)); // Leading/trailing spaces

        // Matches multiple words in sentence
        assertTrue(StringUtil.matchesWordIgnoreCase("AAA bBb ccc  bbb", "bbB", false));

        // type and time must be a full match
        assertFalse(StringUtil.matchesWordIgnoreCase("01-01-2020 18:00", "01-02-2020", false));
        assertFalse(StringUtil.matchesWordIgnoreCase("todo", "todu", false));
    }

    //---------------- Tests for getDetails --------------------------------------

    /*
     * Equivalence Partitions: null, valid throwable object
     */

    @Test
    public void getDetails_exceptionGiven() {
        assertTrue(StringUtil.getDetails(new FileNotFoundException("file not found"))
            .contains("java.io.FileNotFoundException: file not found"));
    }

    @Test
    public void getDetails_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringUtil.getDetails(null));
    }

}
