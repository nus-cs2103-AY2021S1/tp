package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.assignment.DeadlineContainsKeywordsPredicate;
import seedu.address.model.assignment.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.assignment.NameContainsKeywordsPredicate;
import seedu.address.model.assignment.Priority;
import seedu.address.model.assignment.PriorityContainsKeywordsPredicate;
import seedu.address.model.task.ModuleCode;



public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOnePrefix_throwsParseException() {
        assertParseFailure(parser, " n/CS1231S Homework mod/CS2100", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MORE_THAN_ONE_PREFIX_MESSAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        //invalid prefix
        assertParseFailure(parser, " boo/CS2100", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        //invalid date and time
        assertParseFailure(parser, " d/2345 hello", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        //invalid date and time
        assertParseFailure(parser, " d/99-99-1010 2500", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        //invalid date
        assertParseFailure(parser, " d/99-10-2020", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        //invalid time
        assertParseFailure(parser, " d/9800", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        //invalid module code
        assertParseFailure(parser, " mod/CS386", ModuleCode.MESSAGE_CONSTRAINTS);
        //invalid priority
        assertParseFailure(parser, " p/NO", Priority.MESSAGE_CONSTRAINTS);
        //preamble present
        assertParseFailure(parser, " preamble mod/CS2100", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " preamble n/assignment 2", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " preamble d/1200", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " preamble priority/MEDIUM", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommandByName =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("CS1231S", "Homework")));

        FindCommand expectedFindCommandByModuleCode =
                new FindCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList("CS1231S", "CS2100")));

        FindCommand expectedFindCommandByDeadline =
                new FindCommand(new DeadlineContainsKeywordsPredicate(Arrays.asList("1200", "24-10-2020")));

        FindCommand expectedFindCommandByPriority =
                new FindCommand(new PriorityContainsKeywordsPredicate(Arrays.asList("HIGH", "LOW")));

        assertParseSuccess(parser, " n/CS1231S Homework", expectedFindCommandByName);

        assertParseSuccess(parser, " mod/CS1231S CS2100", expectedFindCommandByModuleCode);

        assertParseSuccess(parser, " d/1200 24-10-2020", expectedFindCommandByDeadline);

        assertParseSuccess(parser, " p/HIGH LOW", expectedFindCommandByPriority);
    }
}
