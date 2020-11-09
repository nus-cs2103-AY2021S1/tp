package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.CALORIES_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CALORIES_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MUSCLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLE_DESC_ARM;
import static seedu.address.logic.commands.CommandTestUtil.MUSCLE_DESC_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_GYM;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HOUSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_ARM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PUSH_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_SIT_UP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HOUSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MUSCLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXERCISE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.exercise.Name;
import seedu.address.testutil.EditExerciseDescriptorBuilder;

public class EditCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;
    private static final String MUSCLE_EMPTY = " " + PREFIX_MUSCLE;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_NAME_PUSH_UP, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_CALORIES_DESC, Calories.MESSAGE_CONSTRAINTS); // invalid calories
        assertParseFailure(parser, "1" + INVALID_MUSCLE_DESC, MuscleTag.MESSAGE_CONSTRAINTS); // invalid muscle tags
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, ExerciseTag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid name followed by valid description
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + DESCRIPTION_DESC_PUSH_UP, Name.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name
        assertParseFailure(parser, "1" + NAME_DESC_PUSH_UP + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Exercise} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_GYM + TAG_DESC_HOUSE + TAG_EMPTY, ExerciseTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_GYM + TAG_EMPTY + TAG_DESC_HOUSE, ExerciseTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_GYM + TAG_DESC_HOUSE, ExerciseTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + MUSCLE_DESC_CHEST + MUSCLE_DESC_ARM + MUSCLE_EMPTY, MuscleTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + MUSCLE_DESC_CHEST + MUSCLE_EMPTY + MUSCLE_DESC_ARM, MuscleTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser,
                "1" + MUSCLE_EMPTY + MUSCLE_DESC_CHEST + MUSCLE_DESC_ARM, MuscleTag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DESCRIPTION_DESC
                        + VALID_DATE_PUSH_UP + VALID_CALORIES_PUSH_UP,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        try {
            Index targetIndex = INDEX_SECOND_EXERCISE;
            String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + TAG_DESC_GYM
                    + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + MUSCLE_DESC_ARM + TAG_DESC_HOUSE;

            UpdateCommand.EditExerciseDescriptor descriptor =
                    new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP)
                            .withDescription(VALID_DESCRIPTION_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                            .withCalories(VALID_CALORIES_PUSH_UP).withMuscleTags(VALID_MUSCLE_CHEST, VALID_MUSCLE_ARM)
                            .withTags(VALID_TAG_GYM, VALID_TAG_HOUSE).build();

            UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand);
        } catch (ParseException e) {
            throw new AssertionError("Execution of test 'parse_allFieldsSpecified_success' should not fail.", e);
        }
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP)
                        .withDescription(VALID_DESCRIPTION_PUSH_UP).build();

        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP;
        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withDescription(VALID_DESCRIPTION_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_PUSH_UP;
        descriptor = new EditExerciseDescriptorBuilder().withDate(VALID_DATE_PUSH_UP).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // calories
        try {
            userInput = targetIndex.getOneBased() + CALORIES_DESC_PUSH_UP;
            descriptor = new EditExerciseDescriptorBuilder().withCalories(VALID_CALORIES_PUSH_UP).build();
            expectedCommand = new UpdateCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand);
        } catch (ParseException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }

        // muscles worked
        userInput = targetIndex.getOneBased() + MUSCLE_DESC_CHEST;
        descriptor = new EditExerciseDescriptorBuilder().withMuscleTags(VALID_MUSCLE_CHEST).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_HOUSE;
        descriptor = new EditExerciseDescriptorBuilder().withTags(VALID_TAG_HOUSE).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_HOUSE
                + NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_HOUSE
                + NAME_DESC_SIT_UP + DESCRIPTION_DESC_SIT_UP + DATE_DESC_SIT_UP
                + CALORIES_DESC_SIT_UP + MUSCLE_DESC_ARM + TAG_DESC_GYM;
        try {
            UpdateCommand.EditExerciseDescriptor descriptor =
                    new EditExerciseDescriptorBuilder().withName(VALID_NAME_SIT_UP)
                            .withDescription(VALID_DESCRIPTION_SIT_UP)
                            .withDate(VALID_DATE_SIT_UP)
                            .withCalories(VALID_CALORIES_SIT_UP)
                            .withMuscleTags(VALID_MUSCLE_CHEST, VALID_MUSCLE_ARM)
                            .withTags(VALID_TAG_HOUSE, VALID_TAG_GYM).build();

            UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

            assertParseSuccess(parser, userInput, expectedCommand);
        } catch (ParseException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXERCISE;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_PUSH_UP;
        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withName(VALID_NAME_PUSH_UP).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        try {
            // other valid values specified
            userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_PUSH_UP + INVALID_NAME_DESC + DATE_DESC_PUSH_UP
                    + CALORIES_DESC_PUSH_UP + NAME_DESC_PUSH_UP
                    + MUSCLE_DESC_CHEST + TAG_DESC_HOUSE;
            descriptor =
                    new EditExerciseDescriptorBuilder()
                            .withName(VALID_NAME_PUSH_UP)
                            .withDescription(VALID_DESCRIPTION_PUSH_UP)
                            .withCalories(VALID_CALORIES_PUSH_UP).withDate(VALID_DATE_PUSH_UP)
                            .withMuscleTags(VALID_MUSCLE_CHEST).withTags(VALID_TAG_HOUSE)
                            .build();
            expectedCommand = new UpdateCommand(targetIndex, descriptor);
            assertParseSuccess(parser, userInput, expectedCommand);
        } catch (ParseException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    @Test
    public void parse_resetMuscleTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + MUSCLE_EMPTY;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withMuscleTags().build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EXERCISE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        UpdateCommand.EditExerciseDescriptor descriptor =
                new EditExerciseDescriptorBuilder().withTags().build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
