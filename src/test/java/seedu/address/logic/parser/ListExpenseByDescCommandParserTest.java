package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListExpenseByDescCommand;
import seedu.address.model.expense.DescContainsKeywordsPredicate;

public class ListExpenseByDescCommandParserTest {

    private ListExpenseByDescCommandParser parser = new ListExpenseByDescCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpenseByDescCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListExpenseByDescCommand() {
        // no leading and trailing whitespaces
        ListExpenseByDescCommand expectedListExpenseByDescCommand =
                new ListExpenseByDescCommand(new DescContainsKeywordsPredicate(Arrays.asList("mcd", "kfc")));
        assertParseSuccess(parser, "mcd kfc", expectedListExpenseByDescCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n mcd \n \t kfc  \t", expectedListExpenseByDescCommand);
    }

}
