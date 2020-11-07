package seedu.address.logic.parser.exercise;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_NAME_DESC_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_TAG_DESC_ARMS;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_TAG_DESC_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_TAG_DESC_LEGS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXERCISE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXERCISE_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_ARMS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_CHEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exercise.ExerciseEditCommand;
import seedu.address.logic.commands.exercise.ExerciseEditCommand.EditExerciseDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.testutil.EditExerciseDescriptorBuilder;

public class ExerciseEditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseEditCommand.MESSAGE_USAGE);

    private ExerciseEditCommandParser parser = new ExerciseEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EXERCISE_NAME_BENCH, MESSAGE_INVALID_INDEX);

        // no field specified
        assertParseFailure(parser, "1", ExerciseEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EXERCISE_NAME_DESC_BENCH, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + EXERCISE_NAME_DESC_BENCH, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EXERCISE_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS_FORMAT); // invalid exercise
        assertParseFailure(parser, "1" + INVALID_EXERCISE_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Exercise} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + EXERCISE_TAG_DESC_LEGS + EXERCISE_TAG_DESC_CHEST + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EXERCISE_TAG_DESC_CHEST + TAG_EMPTY + EXERCISE_TAG_DESC_ARMS,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + EXERCISE_TAG_DESC_CHEST + EXERCISE_TAG_DESC_ARMS,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EXERCISE_NAME_DESC + INVALID_EXERCISE_TAG_DESC,
                Name.MESSAGE_CONSTRAINTS_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXERCISE;
        String userInput = targetIndex.getOneBased() + EXERCISE_TAG_DESC_ARMS
                + EXERCISE_NAME_DESC_BENCH + EXERCISE_TAG_DESC_CHEST;

        ExerciseEditCommand.EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withName(VALID_EXERCISE_NAME_BENCH)
                .withTags(VALID_EXERCISE_TAG_CHEST, VALID_EXERCISE_TAG_ARMS).build();
        ExerciseEditCommand expectedCommand = new ExerciseEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // exercise
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + EXERCISE_NAME_DESC_BENCH;
        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder()
                .withName(VALID_EXERCISE_NAME_BENCH).build();
        ExerciseEditCommand expectedCommand = new ExerciseEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + EXERCISE_TAG_DESC_CHEST;
        descriptor = new EditExerciseDescriptorBuilder().withTags(VALID_EXERCISE_TAG_CHEST).build();
        expectedCommand = new ExerciseEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditExerciseDescriptor descriptor = new EditExerciseDescriptorBuilder().withTags().build();
        ExerciseEditCommand expectedCommand = new ExerciseEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
