package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.assignment.*;

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
        assertParseFailure(parser, " d/2345 hello", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.INVALID_DATE_OR_TIME_MESSAGE));
        assertParseFailure(parser, " mod/CS386", ModuleCode.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " priority/NO", Priority.MESSAGE_CONSTRAINTS);
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
        assertParseSuccess(parser, " priority/HIGH LOW", expectedFindCommandByPriority);
    }

}
