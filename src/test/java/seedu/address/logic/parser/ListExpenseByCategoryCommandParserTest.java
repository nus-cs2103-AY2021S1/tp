package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListExpenseByCategoryCommand;
import seedu.address.model.expense.CategoryContainsKeywordsPredicate;

public class ListExpenseByCategoryCommandParserTest {

    private ListExpenseByCategoryCommandParser parser = new ListExpenseByCategoryCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpenseByCategoryCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsListExpenseByCategoryCommand() {
        // no leading and trailing whitespaces
        ListExpenseByCategoryCommand expectedListExpenseByCategoryCommand =
                new ListExpenseByCategoryCommand(new CategoryContainsKeywordsPredicate(Arrays.asList("Food", "drink")));
        assertParseSuccess(parser, "Food drink", expectedListExpenseByCategoryCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Food \n \t drink  \t", expectedListExpenseByCategoryCommand);
    }

}
