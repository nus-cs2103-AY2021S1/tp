package seedu.address.logic.parser.lessons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LESSON_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_NAME_DESC_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_NAME_DESC_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TAG_DESC_EASY;
import static seedu.address.logic.commands.CommandTestUtil.LESSON_TAG_DESC_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_NAME_CS2106;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LESSON_TAG_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_LESSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_LESSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.lessons.LessonEditCommand;
import seedu.address.logic.commands.lessons.LessonEditCommand.EditLessonDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.testutil.EditLessonDescriptorBuilder;

public class LessonEditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, LessonEditCommand.MESSAGE_USAGE);

    private LessonEditCommandParser parser = new LessonEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_LESSON_NAME_CS2030, MESSAGE_INVALID_INDEX);

        // no field specified
        assertParseFailure(parser, "1", LessonEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + LESSON_NAME_DESC_CS2030, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + LESSON_NAME_DESC_CS2030, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_LESSON_NAME_DESC, Name.MESSAGE_CONSTRAINTS_FORMAT); // invalid lesson
        assertParseFailure(parser, "1" + INVALID_LESSON_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Lesson} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + LESSON_TAG_DESC_LECTURE + LESSON_TAG_DESC_EASY + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + LESSON_TAG_DESC_LECTURE + TAG_EMPTY + LESSON_TAG_DESC_EASY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + LESSON_TAG_DESC_LECTURE + LESSON_TAG_DESC_EASY,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_LESSON_NAME_DESC + INVALID_LESSON_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_LESSON;
        String userInput = targetIndex.getOneBased() + LESSON_TAG_DESC_EASY
                + LESSON_NAME_DESC_CS2030 + LESSON_TAG_DESC_LECTURE;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2030)
                .withTags(VALID_LESSON_TAG_EASY, VALID_LESSON_TAG_LECTURE).build();
        LessonEditCommand expectedCommand = new LessonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // lesson
        Index targetIndex = INDEX_THIRD_LESSON;
        String userInput = targetIndex.getOneBased() + LESSON_NAME_DESC_CS2030;
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2030).build();
        LessonEditCommand expectedCommand = new LessonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + LESSON_TAG_DESC_LECTURE;
        descriptor = new EditLessonDescriptorBuilder().withTags(VALID_LESSON_TAG_LECTURE).build();
        expectedCommand = new LessonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_LESSON;
        String userInput = targetIndex.getOneBased() + LESSON_NAME_DESC_CS2030
                + LESSON_NAME_DESC_CS2030 + LESSON_TAG_DESC_LECTURE + LESSON_NAME_DESC_CS2106 + LESSON_TAG_DESC_EASY;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106)
                .withTags(VALID_LESSON_TAG_LECTURE, VALID_LESSON_TAG_EASY).build();
        LessonEditCommand expectedCommand = new LessonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_LESSON;
        String userInput = targetIndex.getOneBased() + INVALID_LESSON_NAME_DESC + LESSON_NAME_DESC_CS2106;
        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106).build();
        LessonEditCommand expectedCommand = new LessonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + LESSON_TAG_DESC_EASY + INVALID_LESSON_NAME_DESC
                + LESSON_TAG_DESC_LECTURE + LESSON_NAME_DESC_CS2106;
        descriptor = new EditLessonDescriptorBuilder().withName(VALID_LESSON_NAME_CS2106)
                .withTags(VALID_LESSON_TAG_LECTURE, VALID_LESSON_TAG_EASY).build();
        expectedCommand = new LessonEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_LESSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditLessonDescriptor descriptor = new EditLessonDescriptorBuilder().withTags().build();
        LessonEditCommand expectedCommand = new LessonEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
