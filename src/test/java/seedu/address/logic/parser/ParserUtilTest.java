package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.body.Height;
import seedu.address.model.body.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

public class ParserUtilTest {
    private static final String INVALID_LESSON_NAME = "GER@1000";
    private static final String INVALID_EXERCISE_NAME = "Seated Leg Pr&ss";
    private static final String INVALID_DAY = "random";
    private static final String INVALID_DURATION_FORMAT = "1600 - 1800";
    private static final String INVALID_DURATION_ORDER = "1800-1600";
    private static final String INVALID_TAG = "#priority";
    private static final String INVALID_HEIGHT_FORMAT = "123.45.6";
    private static final String INVALID_HEIGHT_LIMIT_1 = "10";
    private static final String INVALID_HEIGHT_LIMIT_2 = "1000";
    private static final String INVALID_WEIGHT_FORMAT = "60.12.3";
    private static final String INVALID_WEIGHT_LIMIT_1 = "10";
    private static final String INVALID_WEIGHT_LIMIT_2 = "1000";

    private static final String VALID_LESSON_NAME = "GER1000";
    private static final String VALID_EXERCISE_NAME = "Seated Leg Press";
    private static final String VALID_DURATION = "1600-1800";
    private static final String VALID_DAY = "monday";
    private static final String VALID_TAG_1 = "priority";
    private static final String VALID_TAG_2 = "lower";
    private static final String VALID_HEIGHT = "170";
    private static final String VALID_WEIGHT = "70";

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
    public void parseLessonIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_LESSON, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_LESSON, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseExerciseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_EXERCISE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseLessonName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithoutWhitespace_returnsLessonName() throws Exception {
        Name expectedName = new Name(VALID_LESSON_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_LESSON_NAME));
    }

    @Test
    public void parseLessonName_validValueWithWhitespace_returnsTrimmedLessonName() throws Exception {
        String lessonNameWithWhitespace = WHITESPACE + VALID_LESSON_NAME + WHITESPACE;
        Name expectedLessonName = new Name(VALID_LESSON_NAME);
        assertEquals(expectedLessonName, ParserUtil.parseName(lessonNameWithWhitespace));
    }

    @Test
    public void parseExerciseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_EXERCISE_NAME));
    }

    @Test
    public void parseExerciseName_validValueWithoutWhitespace_returnsExerciseName() throws Exception {
        Name expectedName = new Name(VALID_EXERCISE_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_EXERCISE_NAME));
    }

    @Test
    public void parseExerciseName_validValueWithWhitespace_returnsTrimmedExerciseName() throws Exception {
        String exerciseNameWithWhitespace = WHITESPACE + VALID_EXERCISE_NAME + WHITESPACE;
        Name expectedExerciseName = new Name(VALID_EXERCISE_NAME);
        assertEquals(expectedExerciseName, ParserUtil.parseName(exerciseNameWithWhitespace));
    }

    @Test
    public void parseDay_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDay((String) null));
    }

    @Test
    public void parseDay_unknownDay_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDay(INVALID_DAY));
    }

    @Test
    public void parseDay_validDay_success() throws Exception {
        Day expectedDay = Day.MONDAY;
        assertEquals(expectedDay, ParserUtil.parseDay(VALID_DAY));
    }

    @Test
    public void parseDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDuration((String) null));
    }

    @Test
    public void parseDuration_invalidDurationFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_FORMAT));
    }

    @Test
    public void parseDuration_invalidDurationOrder_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDuration(INVALID_DURATION_ORDER));
    }

    @Test
    public void parseDuration_validDuration_success() throws Exception {
        Duration expectedDuration = new Duration(LocalTime.of(16, 0), LocalTime.of(18, 0));
        assertEquals(expectedDuration, ParserUtil.parseDuration(VALID_DURATION));
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
        Set<Tag> expectedTagSet = new HashSet<>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }

    @Test
    public void parseHeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseHeight(null));
    }

    @Test
    public void parseHeight_invalidHeightFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight(INVALID_HEIGHT_FORMAT));
    }

    @Test
    public void parseHeight_invalidHeightLowerLimit_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight(INVALID_HEIGHT_LIMIT_1));
    }

    @Test
    public void parseHeight_invalidHeightUpperLimit_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseHeight(INVALID_HEIGHT_LIMIT_2));
    }

    @Test
    public void parseHeight_validHeight_success() throws Exception {
        Height expectedHeight = new Height(170);
        assertEquals(expectedHeight, ParserUtil.parseHeight(VALID_HEIGHT));
    }

    @Test
    public void parseWeight_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseWeight(null));
    }

    @Test
    public void parseWeight_invalidWeightFormat_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT_FORMAT));
    }

    @Test
    public void parseWeight_invalidWeightLowerLimit_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT_LIMIT_1));
    }

    @Test
    public void parseWeight_invalidWeightUpperLimit_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseWeight(INVALID_WEIGHT_LIMIT_2));
    }

    @Test
    public void parseWeight_validWeight_success() throws Exception {
        Weight expectedWeight = new Weight(70);
        assertEquals(expectedWeight, ParserUtil.parseWeight(VALID_WEIGHT));
    }
}
