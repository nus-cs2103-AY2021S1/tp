package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PERSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseExpenditureIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpenditureIndex("10 a"));
    }

    @Test
    public void parseExpenditureIndex_overflowInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseExpenditureIndex("999999999999"));
    }

    @Test
    public void parseExpenditureName_tooLargeInput_throwsParseException() {
        String largerThanThreshold = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertThrows(ParseException.class, () -> ParserUtil.parseExpenditureName(largerThanThreshold));
    }

    @Test
    public void parsePrice_invalidInput_throwsParseException() {
        String input = "h";
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(input));
    }

    @Test
    public void parsePrice_lessThanZeroInput_throwsParseException() {
        String input = "-1";
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(input));
    }

    @Test
    public void parsePrice_exceededValueInput_throwsParseException() {
        String input = "999999999999";
        assertThrows(ParseException.class, () -> ParserUtil.parsePrice(input));
    }

    @Test
    public void parseBudgetName_tooLargeInput_throwsParseException() {
        String largerThanThreshold = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertThrows(ParseException.class, () -> ParserUtil.parseBudgetName(largerThanThreshold));
    }


    @Test
    public void parseBudgetThreshold_noInput_throwsAssertionError() {
        String input = " ";
        assertThrows(AssertionError.class, () -> ParserUtil.parseBudgetThreshold(input));
    }

    @Test
    public void parseBudgetThreshold_lessThanZeroInput_throwsParseException() {
        String input = "-1";
        assertThrows(ParseException.class, () -> ParserUtil.parseBudgetThreshold(input));
    }

    @Test
    public void parseBudgetThreshold_tooLargeInput_throwsParseException() {
        String largerThanThreshold = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        assertThrows(ParseException.class, () -> ParserUtil.parseBudgetThreshold(largerThanThreshold));
    }

    @Test
    public void isValidDouble_validDouble_success() {
        String input = "99999";
        assertTrue(ParserUtil.isValidDouble(input));
    }

    @Test
    public void isValidDouble_invalidDouble_success() {
        String input = "9a";
        assertFalse(ParserUtil.isValidDouble(input));
    }

    @Test
    public void isDouble_validDouble_success() {
        String input = "99999";
        assertTrue(ParserUtil.isDouble(input));
    }

    @Test
    public void isDouble_invalidDouble_success() {
        String input = "9a";
        assertFalse(ParserUtil.isDouble(input));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
