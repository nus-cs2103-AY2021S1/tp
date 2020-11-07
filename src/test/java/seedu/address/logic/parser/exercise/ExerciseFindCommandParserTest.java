package seedu.address.logic.parser.exercise;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exercise.ExerciseFindCommand;
import seedu.address.model.exercise.ExerciseNameContainsKeywordsPredicate;

public class ExerciseFindCommandParserTest {

    private ExerciseFindCommandParser parser = new ExerciseFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ExerciseFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsExerciseFindCommand() {
        // no leading and trailing whitespaces
        ExerciseFindCommand expectedExerciseFindCommand =
                new ExerciseFindCommand(new ExerciseNameContainsKeywordsPredicate(Arrays.asList("Bench")));
        assertParseSuccess(parser, "Bench", expectedExerciseFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Bench", expectedExerciseFindCommand);
    }

}
