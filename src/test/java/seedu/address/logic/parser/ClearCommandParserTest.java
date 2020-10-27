package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.ClearCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the ClearCommand code. The path variation for those two cases occur inside the ParserUtil,
 * and therefore should be covered by the ParserUtilTest.
 */
public class ClearCommandParserTest {

    private final ClearCommandParser parser = new ClearCommandParser();
    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");

    @Test
    public void parse_validExpense_returnsClearCommand() {
        assertParseSuccess(parser, "clear c/expense", new ClearCommand(expense));
    }
    @Test
    public void parse_validRevenue_returnsClearCommand() {
        assertParseSuccess(parser, "clear c/revenue", new ClearCommand(revenue));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ClearCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleSamePrefix_throwsParseException() {
        assertParseFailure(parser, "clear c/revenue c/r", String.format(MESSAGE_MULTIPLE_PREFIXES,
            ClearCommand.PREFIXES));
    }

    @Test
    public void parse_nonEmptyPreample_throwsParseException() {
        assertParseFailure(parser, "clearc/revenue", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            ClearCommand.MESSAGE_USAGE));
    }
}
