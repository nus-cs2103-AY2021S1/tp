package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.DateTime;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;

public class ParserUtilTest {
    private static final String INVALID_TITLE = "R@te movie";
    private static final String INVALID_DATE_TIME = "1-1-2020 12:00";
    private static final String INVALID_DATE = "1-1-2020";
    private static final String INVALID_TIME = "12:0";
    private static final String INVALID_DESCRIPTION = "@example";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TITLE = "Rate movie";
    private static final String VALID_DATE_TIME = "01-01-2020 12:00";
    private static final String VALID_TIME = "12:00";
    private static final String VALID_DESCRIPTION = "rachel,example.com";
    private static final String VALID_TAG_1 = "friend";

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
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_TASK, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle((String) null));
    }

    @Test
    public void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithoutWhitespace_returnsTitle() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    public void parseTitle_validValueWithWhitespace_returnsTrimmedTitle() throws Exception {
        String titleWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(titleWithWhitespace));
    }

    @Test
    public void parseDateTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDateTime((String) null));
    }

    @Test
    public void parseDateTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDateTime(INVALID_DATE_TIME));
    }

    @Test
    public void parseDateTime_validValueWithoutWhitespace_returnsDateTime() throws Exception {
        DateTime expectedDateTime = new DateTime(VALID_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(VALID_DATE_TIME));
    }

    @Test
    public void parseDateTime_validValueWithWhitespace_returnsTrimmedDateTime() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE_TIME + WHITESPACE;
        DateTime expectedDateTime = new DateTime(VALID_DATE_TIME);
        assertEquals(expectedDateTime, ParserUtil.parseDateTime(dateWithWhitespace));
    }
    @Test
    public void parseDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_DATE));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTime((String) null));
    }

    @Test
    public void parseTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTime(INVALID_TIME));
    }

    @Test
    public void parseTime_validValueWithoutWhitespace_returnsTime() throws Exception {
        LocalTime expectedTime = new Time(VALID_TIME).value;
        assertEquals(expectedTime, ParserUtil.parseTime(VALID_TIME));
    }

    @Test
    public void parseTime_validValueWithWhitespace_returnsTrimmedTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_TIME + WHITESPACE;
        LocalTime expectedTime = new Time(VALID_TIME).value;
        assertEquals(expectedTime, ParserUtil.parseTime(timeWithWhitespace));
    }

    @Test
    public void parseDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDescription((String) null));
    }

    @Test
    public void parseDescription_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithoutWhitespace_returnsDescription() throws Exception {
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescription() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        Description expectedDescription = new Description(VALID_DESCRIPTION);
        assertEquals(expectedDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
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
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }
}
