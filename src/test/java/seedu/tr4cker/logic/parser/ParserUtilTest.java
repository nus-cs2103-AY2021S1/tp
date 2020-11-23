package seedu.tr4cker.logic.parser;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.temporal.TemporalAdjusters.next;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.tr4cker.testutil.Assert.assertThrows;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.countdown.EventDate;
import seedu.tr4cker.model.countdown.EventName;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.TaskDescription;
import seedu.tr4cker.model.util.NaturalDateUtil;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DEADLINE = "90-90-2021 9999";
    private static final String INVALID_NATURAL_DEADLINE_1 = "Tuesdday";
    private static final String INVALID_NATURAL_DEADLINE_2 = "Tuesday 2500";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String EXPIRED_DEADLINE = "01-01-2020 2359";
    private static final String INVALID_EVENT_NAME = "Exciting!!! Stuff";
    private static final String INVALID_DATE = "55-66-7777";
    private static final String INVALID_DATE_2 = "31-Nov-2021";
    private static final String EXPIRED_DATE = "01-01-2020";
    private static final String INVALID_QUERY_DAYS = "-10";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_DEADLINE_MM = "25-12-2021 2359";
    private static final String VALID_DEADLINE_MMM = "25-Dec-2021 2359";
    private static final String VALID_DEADLINE_NO_TIME_MM = "25-12-2021";
    private static final String VALID_DEADLINE_NO_TIME_MMM = "25-Dec-2021";
    private static final String VALID_DESCRIPTION = "description";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";
    private static final String VALID_EVENT_NAME = "Exciting Stuff";
    private static final String VALID_DATE_MM = "09-08-2025";
    private static final String VALID_DATE_MMM = "09-Aug-2025";
    private static final String VALID_QUERY_DAYS = "10";

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
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));

    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseCompletionStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseCompletionStatus((String) null));
    }

    @Test
    public void parseCompletionStatus_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil
                .parseCompletionStatus(DoneCommandParserTest.INVALID_COMPLETION_STATUS_1));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseCompletionStatus(DoneCommandParserTest.INVALID_COMPLETION_STATUS_2));
        assertThrows(ParseException.class, () -> ParserUtil
                .parseCompletionStatus(DoneCommandParserTest.NOT_A_COMPLETION_STATUS));
    }

    @Test
    public void parseCompletionStatus_validValueWithoutWhitespace_returnsCompletionStatus() throws Exception {
        assertEquals(DoneCommandParserTest.COMPLETION_STATUS_2,
                ParserUtil.parseCompletionStatus(DoneCommandParserTest.VALID_COMPLETION_STATUS_HALF));
    }

    @Test
    public void parseCompletionStatus_validValueWithWhitespace_returnsTrimmedCompletionStatus() throws Exception {
        String completionStatusWithWhitespace = WHITESPACE
                + DoneCommandParserTest.VALID_COMPLETION_STATUS_HALF + WHITESPACE;

        assertEquals(DoneCommandParserTest.COMPLETION_STATUS_2,
                ParserUtil.parseCompletionStatus(completionStatusWithWhitespace));
    }

    @Test
    public void parseDeadline_expiredDeadline_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Deadline(EXPIRED_DEADLINE, true));
    }

    @Test
    public void parseDeadline_expiredDeadline_returnsDeadline() {
        boolean isCreated = true;
        try {
            Deadline expectedDeadline = new Deadline(EXPIRED_DEADLINE, false);
        } catch (IllegalArgumentException e) {
            isCreated = false;
        }
        assertTrue(isCreated);
    }

    @Test
    public void parseDeadline_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDeadline((String) null));
    }

    @Test
    public void parseDeadline_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_DEADLINE));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(EXPIRED_DEADLINE));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_NATURAL_DEADLINE_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseDeadline(INVALID_NATURAL_DEADLINE_2));
    }

    @Test
    public void parseDeadline_validValueWithoutWhitespace_returnsDeadline() throws Exception {
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE_MM, false);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_MM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_MMM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_NO_TIME_MM));
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(VALID_DEADLINE_NO_TIME_MMM));

        String expectedDate = LocalDate.now().with(next(FRIDAY))
                .format(NaturalDateUtil.DATE_TIME_FORMATTER);
        expectedDeadline = new Deadline(expectedDate + " 2200", false);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("friday 2200"));
        expectedDeadline = new Deadline(expectedDate + " 2359", false);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("friday"));

        expectedDeadline = new Deadline(LocalDate.now().format(NaturalDateUtil.DATE_TIME_FORMATTER)
                + " 2359", false);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline("today"));
    }

    @Test
    public void parseDeadline_validValueWithWhitespace_returnsTrimmedDeadline() throws Exception {
        String deadlineWithWhitespace = WHITESPACE + VALID_DEADLINE_MM + WHITESPACE;
        Deadline expectedDeadline = new Deadline(VALID_DEADLINE_MM, false);
        assertEquals(expectedDeadline, ParserUtil.parseDeadline(deadlineWithWhitespace));
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
    public void parseDescription_validValueWithoutWhitespace_returnsDescriptions() throws Exception {
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(VALID_DESCRIPTION));
    }

    @Test
    public void parseDescription_validValueWithWhitespace_returnsTrimmedDescriptions() throws Exception {
        String descriptionWithWhitespace = WHITESPACE + VALID_DESCRIPTION + WHITESPACE;
        TaskDescription expectedTaskDescription = new TaskDescription(VALID_DESCRIPTION);
        assertEquals(expectedTaskDescription, ParserUtil.parseDescription(descriptionWithWhitespace));
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

    @Test
    public void parseEventName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventName((String) null));
    }

    @Test
    public void parseEventName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_EVENT_NAME));
    }

    @Test
    public void parseEventName_validValueWithoutWhitespace_returnsName() throws Exception {
        EventName expectedName = new EventName(VALID_EVENT_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(VALID_EVENT_NAME));
    }

    @Test
    public void parseEventName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_EVENT_NAME + WHITESPACE;
        EventName expectedName = new EventName(VALID_EVENT_NAME);
        assertEquals(expectedName, ParserUtil.parseEventName(nameWithWhitespace));
    }

    @Test
    public void parseEventDate_expiredEventDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new EventDate(EXPIRED_DATE, true));
    }

    @Test
    public void parseEventDate_expiredEventDate_returnsEventDate() {
        boolean isCreated = true;
        try {
            EventDate expectedDate = new EventDate(EXPIRED_DATE, false);
        } catch (IllegalArgumentException e) {
            isCreated = false;
        }
        assertTrue(isCreated);
    }

    @Test
    public void parseEventDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEventDate((String) null));
    }

    @Test
    public void parseEventDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(INVALID_DATE));
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(EXPIRED_DATE));
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(INVALID_DATE_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseEventDate(VALID_DEADLINE_MM)); // cannot have time
    }

    @Test
    public void parseEventDate_validValueWithoutWhitespace_returnsEventDate() throws Exception {
        EventDate expectedDate = new EventDate(VALID_DATE_MM, false);
        assertEquals(expectedDate, ParserUtil.parseEventDate(VALID_DATE_MM));
        assertEquals(expectedDate, ParserUtil.parseEventDate(VALID_DATE_MMM));
    }

    @Test
    public void parseEventDate_validValueWithWhitespace_returnsTrimmedEventDate() throws Exception {
        String dateWithWhitespace = WHITESPACE + VALID_DATE_MM + WHITESPACE;
        EventDate expectedDate = new EventDate(VALID_DATE_MM, false);
        assertEquals(expectedDate, ParserUtil.parseEventDate(dateWithWhitespace));
    }

    @Test
    public void parseQueryDays_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseQueryDays((String) null));
    }

    @Test
    public void parseQueryDays_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQueryDays(INVALID_QUERY_DAYS));
        assertThrows(ParseException.class, () -> ParserUtil.parseQueryDays(INVALID_NAME));
    }

    @Test
    public void parseQueryDays_validValueWithoutWhitespace_returnsQueryDays() throws Exception {
        int expectedQueryDays = 10;
        assertEquals(expectedQueryDays, ParserUtil.parseQueryDays(VALID_QUERY_DAYS));
    }

    @Test
    public void parseQueryDays_validValueWithWhitespace_returnsTrimmedQueryDays() throws Exception {
        String queryDaysWithWhitespace = WHITESPACE + VALID_QUERY_DAYS + WHITESPACE;
        int expectedQueryDays = 10;
        assertEquals(expectedQueryDays, ParserUtil.parseQueryDays(queryDaysWithWhitespace));
    }
}
