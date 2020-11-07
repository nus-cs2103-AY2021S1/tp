package seedu.address.logic.parser.exercise;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_NAME_DESC_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_NAME_DESC_SQUATS;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_TAG_DESC_ARMS;
import static seedu.address.logic.commands.CommandTestUtil.EXERCISE_TAG_DESC_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXERCISE_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXERCISE_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_NAME_BENCH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_ARMS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXERCISE_TAG_CHEST;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exercise.ExerciseAddCommand;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;
import seedu.address.testutil.ExerciseBuilder;

public class ExerciseAddCommandParserTest {
    private ExerciseAddCommandParser parser = new ExerciseAddCommandParser();

    @Test
    public void parse_allExerciseFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(BENCH_PRESS).withTags(VALID_EXERCISE_TAG_CHEST).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EXERCISE_NAME_DESC_BENCH
                + EXERCISE_TAG_DESC_CHEST, new ExerciseAddCommand(expectedExercise));

        // multiple tags - all accepted
        Exercise expectedExerciseMultipleTags = new ExerciseBuilder(BENCH_PRESS)
                .withTags(VALID_EXERCISE_TAG_CHEST, VALID_EXERCISE_TAG_ARMS).build();
        assertParseSuccess(parser, EXERCISE_NAME_DESC_BENCH + EXERCISE_TAG_DESC_CHEST
                + EXERCISE_TAG_DESC_ARMS, new ExerciseAddCommand(expectedExerciseMultipleTags));
    }

    @Test
    public void parse_optionalExerciseFieldsMissing_success() {
        // zero tags
        Exercise expectedExercise = new ExerciseBuilder(SQUATS).withTags().build();
        assertParseSuccess(parser, EXERCISE_NAME_DESC_SQUATS, new ExerciseAddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryExerciseFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseAddCommand.MESSAGE_USAGE);

        // missing exercise prefix
        assertParseFailure(parser, VALID_EXERCISE_NAME_BENCH, expectedMessage);
    }

    @Test
    public void parse_invalidExerciseValue_failure() {
        // invalid exercise
        assertParseFailure(parser, INVALID_EXERCISE_NAME_DESC
                + EXERCISE_TAG_DESC_CHEST + EXERCISE_TAG_DESC_ARMS, Name.MESSAGE_CONSTRAINTS_FORMAT);

        // invalid tag
        assertParseFailure(parser, EXERCISE_NAME_DESC_BENCH
                + INVALID_EXERCISE_TAG_DESC + VALID_EXERCISE_TAG_ARMS, Tag.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + EXERCISE_NAME_DESC_BENCH
                        + EXERCISE_TAG_DESC_ARMS + EXERCISE_TAG_DESC_CHEST,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExerciseAddCommand.MESSAGE_USAGE));
    }
}
