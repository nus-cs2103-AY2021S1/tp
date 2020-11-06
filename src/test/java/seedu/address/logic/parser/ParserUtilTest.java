package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.util.Name;
import seedu.address.model.tag.Tag;

public class ParserUtilTest {
    private static final String INVALID_LESSON_NAME = "GER@1000";
    private static final String INVALID_EXERCISE_NAME = "Seated Leg Pr&ss";
    private static final String INVALID_TAG = "#priority";

    private static final String VALID_LESSON_NAME = "GER1000";
    private static final String VALID_EXERCISE_NAME = "Seated Leg Press";
    private static final String VALID_TAG_1 = "priority";
    private static final String VALID_TAG_2 = "lower";

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
}
