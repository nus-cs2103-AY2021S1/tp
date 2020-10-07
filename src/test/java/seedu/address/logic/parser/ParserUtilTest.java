package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FileAddress;
import seedu.address.model.person.TagName;


public class ParserUtilTest {
    private static final String INVALID_FILE_NAME = "R@chel";
    private static final String INVALID_FILE_ADDRESS = " ";


    private static final String VALID_TAG_NAME = "Rachel Walker";
    private static final String VALID_FILE_ADDRESS = "c:\\a\\b\\rachel.txt";

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
        assertEquals(INDEX_FIRST_TAG, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TAG, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTagName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTagName(INVALID_FILE_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        TagName expectedName = new TagName(VALID_TAG_NAME);
        assertEquals(expectedName, ParserUtil.parseTagName(VALID_TAG_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TAG_NAME + WHITESPACE;
        TagName expectedName = new TagName(VALID_TAG_NAME);
        assertEquals(expectedName, ParserUtil.parseTagName(nameWithWhitespace));
    }


    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFileAddress((String) null));
    }

    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFileAddress(INVALID_FILE_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        FileAddress expectedFileAddress = new FileAddress(VALID_FILE_ADDRESS);
        assertEquals(expectedFileAddress, ParserUtil.parseFileAddress(VALID_FILE_ADDRESS));
    }

    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_FILE_ADDRESS + WHITESPACE;
        FileAddress expectedFileAddress = new FileAddress(VALID_FILE_ADDRESS);
        assertEquals(expectedFileAddress, ParserUtil.parseFileAddress(addressWithWhitespace));
    }

}
