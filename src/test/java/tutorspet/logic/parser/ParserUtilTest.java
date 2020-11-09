package tutorspet.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutorspet.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static tutorspet.logic.parser.ParserUtil.parseDay;
import static tutorspet.logic.parser.ParserUtil.parseEmail;
import static tutorspet.logic.parser.ParserUtil.parseIndex;
import static tutorspet.logic.parser.ParserUtil.parseNumberOfOccurrences;
import static tutorspet.logic.parser.ParserUtil.parseParticipationScore;
import static tutorspet.logic.parser.ParserUtil.parseTag;
import static tutorspet.logic.parser.ParserUtil.parseTags;
import static tutorspet.logic.parser.ParserUtil.parseTime;
import static tutorspet.logic.parser.ParserUtil.parseVenue;
import static tutorspet.testutil.Assert.assertThrows;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import tutorspet.logic.parser.exceptions.ParseException;
import tutorspet.model.components.name.Name;
import tutorspet.model.components.tag.Tag;
import tutorspet.model.lesson.Day;
import tutorspet.model.lesson.NumberOfOccurrences;
import tutorspet.model.lesson.Venue;
import tutorspet.model.student.Email;
import tutorspet.model.student.Telegram;

public class ParserUtilTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TELEGRAM = "invalid+tElEg4m";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#Potential TA";
    private static final String INVALID_DAY = "ASDDAY";
    private static final String INVALID_TIME = "123412x";
    private static final String INVALID_VENUE = "&&&&&&";
    private static final String INVALID_NUMBER_OF_OCCURRENCES = "asd";
    private static final String OUT_OF_BOUNDS_NUMBER_OF_OCCURRENCES = "0";
    private static final String INVALID_PARTICIPATION_SCORE = "asd";
    private static final String OUT_OF_BOUNDS_PARTICIPATION_SCORE = "-1";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_TELEGRAM = "rachelW4lker";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_TAG_1 = "Weak";
    private static final String VALID_TAG_2 = "Average";
    private static final String VALID_DAY = "FRIDAY";
    private static final String VALID_TIME = "08:00";
    private static final String VALID_VENUE = "S17-0302";
    private static final String VALID_NUMBER_OF_OCCURRENCES = "13";
    private static final String VALID_PARTICIPATION_SCORE = "13";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_ITEM, parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_ITEM, parseIndex("  1  "));
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
    public void parseTelegram_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTelegram((String) null));
    }

    @Test
    public void parseTelegram_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTelegram(INVALID_TELEGRAM));
    }

    @Test
    public void parseTelegram_validValueWithoutWhitespace_returnsTelegram() throws Exception {
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(VALID_TELEGRAM));
    }

    @Test
    public void parseTelegram_validValueWithWhitespace_returnsTrimmedTelegram() throws Exception {
        String telegramWithWhitespace = WHITESPACE + VALID_TELEGRAM + WHITESPACE;
        Telegram expectedTelegram = new Telegram(VALID_TELEGRAM);
        assertEquals(expectedTelegram, ParserUtil.parseTelegram(telegramWithWhitespace));
    }

    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseEmail((String) null));
    }

    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseEmail(INVALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, parseEmail(VALID_EMAIL));
    }

    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, parseEmail(emailWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
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
    public void parseDay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseDay((String) null));
    }

    @Test
    public void parseDay_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseDay(INVALID_DAY));
    }

    @Test
    public void parseDay_validValueWithoutWhitespace_returnsDay() throws Exception {
        Day expectedDay = Day.FRIDAY;
        assertEquals(expectedDay, parseDay(VALID_DAY));
    }

    @Test
    public void parseDay_validValueWithWhitespace_returnsTrimmedDay() throws Exception {
        String dayWithWhitespace = WHITESPACE + VALID_DAY + WHITESPACE;
        Day expectedDay = Day.FRIDAY;
        assertEquals(expectedDay, parseDay(dayWithWhitespace));
    }

    @Test
    public void parseTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseTime((String) null));
    }

    @Test
    public void parseTime_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseTime(INVALID_TIME));
    }

    @Test
    public void parseTime_validValueWithoutWhitespace_returnsTime() throws Exception {
        LocalTime expectedTime = LocalTime.parse(VALID_TIME);
        assertEquals(expectedTime, parseTime(VALID_TIME));
    }

    @Test
    public void parseTime_validValueWithWhitespace_returnsTrimmedTime() throws Exception {
        String timeWithWhitespace = WHITESPACE + VALID_TIME + WHITESPACE;
        LocalTime expectedTime = LocalTime.parse(VALID_TIME);
        assertEquals(expectedTime, parseTime(timeWithWhitespace));
    }

    @Test
    public void parseVenue_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseVenue((String) null));
    }

    @Test
    public void parseVenue_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseVenue(INVALID_VENUE));
    }

    @Test
    public void parseVenue_validValueWithoutWhitespace_returnsVenue() throws Exception {
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, parseVenue(VALID_VENUE));
    }

    @Test
    public void parseVenue_validValueWithWhitespace_returnsTrimmedVenue() throws Exception {
        String venueWithWhitespace = WHITESPACE + VALID_VENUE + WHITESPACE;
        Venue expectedVenue = new Venue(VALID_VENUE);
        assertEquals(expectedVenue, ParserUtil.parseVenue(venueWithWhitespace));
    }

    @Test
    public void parseNumberOfOccurrences_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseNumberOfOccurrences((String) null));
    }

    @Test
    public void parseNumberOfOccurrences_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseNumberOfOccurrences(INVALID_NUMBER_OF_OCCURRENCES));
    }

    @Test
    public void parseNumberOfOccurrences_outOfBounds_throwsParseException() {
        assertThrows(ParseException.class, () -> parseNumberOfOccurrences(OUT_OF_BOUNDS_NUMBER_OF_OCCURRENCES));
    }

    @Test
    public void parseNumberOfOccurrences_validValueWithoutWhitespace_returnsNumberOfOccurrences() throws Exception {
        NumberOfOccurrences expectedNumberOfOccurrences =
                new NumberOfOccurrences(Integer.parseInt(VALID_NUMBER_OF_OCCURRENCES));
        assertEquals(expectedNumberOfOccurrences, parseNumberOfOccurrences(VALID_NUMBER_OF_OCCURRENCES));
    }

    @Test
    public void parseNumberOfOccurrences_validValueWithWhitespace_returnsTrimmedNumberOfOccurrences() throws Exception {
        String numberOfOccurrencesWithWhitespace = WHITESPACE + VALID_NUMBER_OF_OCCURRENCES + WHITESPACE;
        NumberOfOccurrences expectedNumberOfOccurrences =
                new NumberOfOccurrences(Integer.parseInt(VALID_NUMBER_OF_OCCURRENCES));
        assertEquals(expectedNumberOfOccurrences,
               parseNumberOfOccurrences(numberOfOccurrencesWithWhitespace));
    }

    @Test
    public void parseParticipationScore_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parseParticipationScore((String) null));
    }

    @Test
    public void parseParticipationScore_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> parseParticipationScore(INVALID_PARTICIPATION_SCORE));
    }

    @Test
    public void parseParticipationScore_outOfBounds_throwsParseException() {
        assertThrows(ParseException.class, () -> parseParticipationScore(OUT_OF_BOUNDS_PARTICIPATION_SCORE));
    }

    @Test
    public void parseParticipationScore_validValueWithoutWhitespace_returnsParticipationScore() throws Exception {
        int expectedParticipationScore = Integer.parseInt(VALID_PARTICIPATION_SCORE);
        assertEquals(expectedParticipationScore, parseParticipationScore(VALID_PARTICIPATION_SCORE));
    }

    @Test
    public void parseParticipationScore_validValueWithWhitespace_returnsTrimmedParticipationScore() throws Exception {
        String participationScoreWithWhitespace = WHITESPACE + VALID_PARTICIPATION_SCORE + WHITESPACE;
        int expectedParticipationScore = Integer.parseInt(VALID_PARTICIPATION_SCORE);
        assertEquals(expectedParticipationScore,
                parseParticipationScore(participationScoreWithWhitespace));
    }
}
