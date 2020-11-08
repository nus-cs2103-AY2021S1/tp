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
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_ARM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MUSCLE_CHEST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_GYM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HOUSE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExercise.TYPICAL_EXERCISE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.exercise.Name;
import seedu.address.testutil.ExerciseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(TYPICAL_EXERCISE).build();

        // normal input
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                NAME_DESC_SIT_UP + NAME_DESC_PUSH_UP
                        + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                new AddCommand(expectedExercise));

        //multiple descriptions - last description accepted
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_SIT_UP
                        + DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                new AddCommand(expectedExercise));

        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_SIT_UP + DATE_DESC_PUSH_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                new AddCommand(expectedExercise));

        //multiple calories
        assertParseSuccess(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_SIT_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                new AddCommand(expectedExercise));

        // multiple muscle tags - all accepted
        Exercise expectedExerciseMultipleMuscleTags = new ExerciseBuilder(TYPICAL_EXERCISE)
                .withMuscleTags(VALID_MUSCLE_CHEST, VALID_MUSCLE_ARM)
                .build();
        assertParseSuccess(parser, NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                + DATE_DESC_PUSH_UP + CALORIES_DESC_SIT_UP
                + MUSCLE_DESC_CHEST + MUSCLE_DESC_ARM
                + TAG_DESC_GYM, new AddCommand(expectedExerciseMultipleMuscleTags));

        // multiple tags - all accepted
        Exercise expectedExerciseMultipleTags = new ExerciseBuilder(TYPICAL_EXERCISE)
                .withTags(VALID_TAG_GYM, VALID_TAG_HOUSE)
                .build();
        assertParseSuccess(parser, NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                + DATE_DESC_PUSH_UP + CALORIES_DESC_SIT_UP
                + MUSCLE_DESC_CHEST + TAG_DESC_GYM + TAG_DESC_HOUSE, new AddCommand(expectedExerciseMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing name
        assertParseFailure(parser, DESCRIPTION_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_ARM + TAG_DESC_HOUSE,
                expectedMessage);

        //missing description
        assertParseFailure(parser, NAME_DESC_PUSH_UP + DATE_DESC_PUSH_UP
                        + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_ARM + TAG_DESC_HOUSE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid name
        assertParseFailure(parser,
                INVALID_NAME_DESC + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                Name.MESSAGE_CONSTRAINTS);

        //invalid description
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + INVALID_DESCRIPTION_DESC
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                Description.MESSAGE_CONSTRAINTS);

        //invalid date
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + INVALID_DATE_DESC + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                Date.MESSAGE_CONSTRAINTS);

        //invalid calories
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + INVALID_CALORIES_DESC + MUSCLE_DESC_CHEST + TAG_DESC_GYM,
                Calories.MESSAGE_CONSTRAINTS);

        //invalid muscle tags
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + INVALID_MUSCLE_DESC + TAG_DESC_GYM,
                MuscleTag.MESSAGE_CONSTRAINTS);

        //invalid tags
        assertParseFailure(parser,
                NAME_DESC_PUSH_UP + DESCRIPTION_DESC_PUSH_UP
                        + DATE_DESC_PUSH_UP + CALORIES_DESC_PUSH_UP + MUSCLE_DESC_CHEST + INVALID_TAG_DESC,
                ExerciseTag.MESSAGE_CONSTRAINTS);
    }

}
