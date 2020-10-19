package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExercise.PUSH_UP;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.person.Name;
import seedu.address.testutil.ExerciseBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exercise expectedExercise = new ExerciseBuilder(PUSH_UP).build();
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // normal input
        assertParseSuccess(parser, " n/Push Up d/Test 1 at/09-10-2020 c/12345",
                new AddCommand(expectedExercise));

        // multiple names - last name accepted
        assertParseSuccess(parser, " n/Push n/Push Up d/Test 1 at/09-10-2020 c/12345",
                new AddCommand(expectedExercise));

        //multiple descriptions - last description accepted
        assertParseSuccess(parser, " n/Push Up d/Test d/Test 1 at/09-10-2020 c/12345",
                new AddCommand(expectedExercise));

        //multiple date
        assertParseSuccess(parser, " n/Push Up d/Test 1 at/10-10-2020 at/09-10-2020 c/12345",
                new AddCommand(expectedExercise));

        //multiple calories
        assertParseSuccess(parser, " n/Push Up d/Test 1 at/09-10-2020 c/1234 c/12345",
                new AddCommand(expectedExercise));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        //missing name
        assertParseFailure(parser, " d/Test 1 at/09-10-2020 c/12345",
                expectedMessage);

        //missing description
        assertParseFailure(parser, " n/Push Up at/09-10-2020 c/12345",
                expectedMessage);

        //missing date
        assertParseFailure(parser, " n/Push Up d/Test 1 c/12345",
                expectedMessage);

        //missing calories
        assertParseFailure(parser, " n/Run d/Test 1 at/10-10-2020",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid name
        assertParseFailure(parser, " n/  d/Test 1 at/10-10-2020 c/12345",
                Name.MESSAGE_CONSTRAINTS);

        //invalid description
        assertParseFailure(parser, " n/Run d/ at/10-10-2020 c/12345",
                Description.MESSAGE_CONSTRAINTS);

        //invalid date
        assertParseFailure(parser, " n/Run d/Test 1 at/2020-10-10 c/12345",
                Date.MESSAGE_CONSTRAINTS);

        //invalid calories
        assertParseFailure(parser, " n/Run d/Test 1 at/10-10-2020 c/abc",
                Calories.MESSAGE_CONSTRAINTS);
    }

}
