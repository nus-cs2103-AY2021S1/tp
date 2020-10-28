package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_SEARCH_PHRASE;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.Description;
import seedu.address.model.task.TaskContainsKeywordsPredicate;
import seedu.address.model.task.Title;
import seedu.address.model.task.deadline.Status;

public class FindTaskCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "Meet Alice");
        predicate.setKeyword(PREFIX_DESCRIPTION, "play");
        predicate.setKeyword(PREFIX_DATE, "01-01-2020");
        predicate.setKeyword(PREFIX_STATUS, "incomplete");
        FindTaskCommand expectedFindTaskCommand = new FindTaskCommand(predicate);
        assertParseSuccess(parser,
                " title:Meet Alice desc:play date:01-01-2020 status: incomplete",
                expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n title:Meet Alice  \t desc:play \t\t\t date: \t 01-01-2020 "
                        + "\n status:incomplete",
                expectedFindTaskCommand);
    }

    @Test
    public void parse_emptyArgs_returnsFalse() {
        // throw error if argument is empty
        assertParseFailure(parser, " title:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " desc:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " status:", MESSAGE_EMPTY_SEARCH_PHRASE);
        assertParseFailure(parser, " date:", MESSAGE_EMPTY_SEARCH_PHRASE);

        // one of the attribute is empty
        assertParseFailure(parser, " title:abc date: desc:edf", MESSAGE_EMPTY_SEARCH_PHRASE);

        // space is trimmed. empty spaces also considered empty
        assertParseFailure(parser, " desc:abc date:  ", MESSAGE_EMPTY_SEARCH_PHRASE);
    }

    @Test
    public void parse_argsWrongFormat_returnsFalse() {
        // throw error if argument is invalid
        assertParseFailure(parser, " title:@@", Title.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " desc:@@", Description.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " status:comple", Status.SEARCH_CONSTRAINTS);
        assertParseFailure(parser, " date:01-01-202", DateUtil.SEARCH_DATE_CONSTRAINTS);
        assertParseFailure(parser, " date:13", DateUtil.SEARCH_DATE_CONSTRAINTS);

        // one of the attribute is invalid
        assertParseFailure(parser, " title:abc# date: desc:edf", Title.SEARCH_CONSTRAINTS);
    }
}
