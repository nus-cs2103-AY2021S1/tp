package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.category.Category.MESSAGE_CONSTRAINTS;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.GetTotalCommand;

class GetTotalCommandParserTest {

    private final GetTotalCommandParser parser = new GetTotalCommandParser();
    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");

    @Test
    public void parse_validExpenses_returnsGetTotalCommandSuccess() {
        assertParseSuccess(parser, CATEGORY_DESC_EXPENSE, new GetTotalCommand(expense));
    }

    @Test
    public void parse_validRevenues_returnsGetTotalCommandSuccess() {
        assertParseSuccess(parser, " c/revenue", new GetTotalCommand(revenue));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "total", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            GetTotalCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCategory_throwsParseException() {
        assertParseFailure(parser, INVALID_CATEGORY_DESC, MESSAGE_CONSTRAINTS);
    }
}
