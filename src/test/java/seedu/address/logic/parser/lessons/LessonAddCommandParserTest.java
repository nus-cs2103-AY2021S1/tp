package seedu.address.logic.parser.lessons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_NAME_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_NAME_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TAG_DESC_EASY;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_LECTURE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalLessons.CS2030;
import static seedu.address.testutil.TypicalLessons.CS2106;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lessons.LessonAddCommand;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.testutil.LessonBuilder;

public class LessonAddCommandParserTest {
    private LessonAddCommandParser parser = new LessonAddCommandParser();

    @Test
    public void parse_allLessonFieldsPresent_success() {
        Lesson expectedLesson = new LessonBuilder(CS2106).withTags(VALID_LESSON_TAG_LECTURE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LESSON_NAME_DESC_CS2106
                + LESSON_TAG_DESC_LECTURE, new LessonAddCommand(expectedLesson));

        // multiple lessons - last lesson accepted
        assertParseSuccess(parser, LESSON_NAME_DESC_CS2030 + LESSON_NAME_DESC_CS2106
                + LESSON_TAG_DESC_LECTURE, new LessonAddCommand(expectedLesson));

        // multiple tags - all accepted
        Lesson expectedLessonMultipleTags = new LessonBuilder(CS2106)
                .withTags(VALID_LESSON_TAG_LECTURE, VALID_LESSON_TAG_EASY).build();
        assertParseSuccess(parser, LESSON_NAME_DESC_CS2106
                + LESSON_TAG_DESC_EASY + LESSON_TAG_DESC_LECTURE, new LessonAddCommand(expectedLessonMultipleTags));
    }

    @Test
    public void parse_optionalLessonFieldsMissing_success() {
        // zero tags
        Lesson expectedLesson = new LessonBuilder(CS2030).withTags().build();
        assertParseSuccess(parser, LESSON_NAME_DESC_CS2030, new LessonAddCommand(expectedLesson));
    }

    @Test
    public void parse_compulsoryLessonFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE);

        // missing lesson prefix
        assertParseFailure(parser, VALID_LESSON_NAME_CS2106, expectedMessage);
    }

    @Test
    public void parse_invalidLessonValue_failure() {
        // invalid lesson
        assertParseFailure(parser, INVALID_LESSON_NAME_DESC
                + LESSON_TAG_DESC_EASY + LESSON_TAG_DESC_LECTURE, Name.MESSAGE_CONSTRAINTS_FORMAT);

        // invalid tag
        assertParseFailure(parser, LESSON_NAME_DESC_CS2106
                + INVALID_LESSON_TAG_DESC + VALID_LESSON_TAG_LECTURE, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + LESSON_NAME_DESC_CS2106
                        + LESSON_TAG_DESC_EASY + LESSON_TAG_DESC_LECTURE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonAddCommand.MESSAGE_USAGE));
    }
}
