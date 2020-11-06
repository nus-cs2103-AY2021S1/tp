package seedu.address.logic.parser.routines;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.routines.RoutineFindCommand;
import seedu.address.model.routine.RoutineNameContainsKeywordsPredicate;

public class RoutineFindCommandParserTest {

    private RoutineFindCommandParser parser = new RoutineFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, RoutineFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLessonFindCommand() {

        RoutineNameContainsKeywordsPredicate predicate = new RoutineNameContainsKeywordsPredicate(
                Arrays.asList("Leg", "Day"));
        // no leading and trailing whitespaces
        RoutineFindCommand expectedRoutineFindCommand =
                new RoutineFindCommand(predicate);
        assertParseSuccess(parser, "Leg Day", expectedRoutineFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Leg\n \t Day  \t", expectedRoutineFindCommand);
    }

}
