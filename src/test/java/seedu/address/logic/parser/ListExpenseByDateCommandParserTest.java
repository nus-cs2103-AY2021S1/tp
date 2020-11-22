package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListExpenseByDateCommand;
import seedu.address.model.expense.DateContainsKeywordsPredicate;

public class ListExpenseByDateCommandParserTest {

    private ListExpenseByDateCommandParser parser = new ListExpenseByDateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpenseByDateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListExpenseByDateCommand() {
        // no leading and trailing whitespaces
        ListExpenseByDateCommand expectedListExpenseByDateCommand =
                new ListExpenseByDateCommand(
                        new DateContainsKeywordsPredicate(Arrays.asList("2011-11-11", "2022-12-22")));
        assertParseSuccess(parser, "2011-11-11 2022-12-22", expectedListExpenseByDateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 2011-11-11 \n \t 2022-12-22  \t", expectedListExpenseByDateCommand);
    }

}
