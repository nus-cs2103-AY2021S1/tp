package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.DeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();
    private final Category expense = new Category("expense");
    private final Category revenue = new Category("revenue");

    @Test
    public void parse_validExpense_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 c/expense", new DeleteCommand(INDEX_FIRST_ENTRY, expense));
    }
    @Test
    public void parse_validRevenue_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 c/revenue", new DeleteCommand(INDEX_FIRST_ENTRY, revenue));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraPrefixes() {
        String expectedMessage = String.format(MESSAGE_MULTIPLE_PREFIXES, DeleteCommand.PREFIXES);

        // Extra prefixes
        assertParseFailure(parser, "1 c/revenue c/revenue", expectedMessage);
    }

    @Test
    public void parse_noPrefixPresent() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE);
        // No prefix present
        assertParseFailure(parser, "c/revenue", expectedMessage);
    }

}
