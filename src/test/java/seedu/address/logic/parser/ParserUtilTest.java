package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TAG;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.label.Label;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.TagName;


public class ParserUtilTest {
    private static final String INVALID_FILE_NAME = "R@chel";
    private static final String INVALID_FILE_ADDRESS = "dsf/asdf??";
    private static final String INVALID_LABEL_1 = "L@bel";
    private static final String INVALID_LABEL_2 = "L8bel!";


    private static final String VALID_TAG_NAME = "Rachel Walker";
    private static final String VALID_FILE_ADDRESS = "c:\\a\\b\\rachel.txt";
    private static final String VALID_LABEL_1 = "label";
    private static final String VALID_LABEL_2 = "label";

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

    @Test
    public void parseLabel_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLabel(null));
    }

    @Test
    public void parseLabel_invalidValue_throwParserException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLabel(INVALID_LABEL_1));
    }

    @Test
    public void parseLabel_validValueWithoutWhitespace_returnLabel() throws ParseException {
        Label expectedLabel = new Label(VALID_LABEL_1);
        assertEquals(expectedLabel, ParserUtil.parseLabel(VALID_LABEL_1));
    }

    @Test
    public void parseLabel_validValueWithWhitespace_returnTrimmedLabel() throws ParseException {
        String labelWithWhitespace = WHITESPACE + VALID_LABEL_1 + WHITESPACE;
        Label expectedLabel = new Label(VALID_LABEL_1);
        assertEquals(expectedLabel, ParserUtil.parseLabel(labelWithWhitespace));
    }

    @Test
    public void parseLabels_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseLabels(null));
    }

    @Test
    public void parseLabels_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseLabels(Collections.singletonList(INVALID_LABEL_1)));
        assertThrows(ParseException.class, () ->
                ParserUtil.parseLabels(Arrays.asList(INVALID_LABEL_1, INVALID_LABEL_2)));
        assertThrows(ParseException.class, () -> ParserUtil.parseLabels(Arrays.asList(VALID_LABEL_1, INVALID_LABEL_1)));
    }

    @Test
    public void parseLabels_validValue_returnSetLabels() throws ParseException {
        Label expectedLabel1 = new Label(VALID_LABEL_1);
        Label expectedLabel2 = new Label(VALID_LABEL_2);
        assertEquals(new HashSet<Label>(Arrays.asList(expectedLabel1, expectedLabel2)),
                ParserUtil.parseLabels(Arrays.asList(VALID_LABEL_1, VALID_LABEL_2)));
    }
}
