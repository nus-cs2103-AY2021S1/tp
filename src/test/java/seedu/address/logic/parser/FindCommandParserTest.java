package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate();
        predicate.setKeyword(PREFIX_TITLE, "Meet Alice");
        predicate.setKeyword(PREFIX_DESCRIPTION, "play");
        predicate.setKeyword(PREFIX_TYPE, "todo");
        predicate.setKeyword(PREFIX_DATE_TIME, "01-01-2020 23:59");
        predicate.setKeyword(PREFIX_STATUS, "incomplete");
        FindCommand expectedFindCommand = new FindCommand(predicate);
        assertParseSuccess(parser,
                " title:Meet Alice desc:play type:todo date:01-01-2020 23:59 status: incomplete",
                expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                " \n title:Meet Alice  \t desc:play \t\t\t type: todo \n date: \t 01-01-2020 23:59 "
                        + "\n status:incomplete",
                expectedFindCommand);
    }

}
