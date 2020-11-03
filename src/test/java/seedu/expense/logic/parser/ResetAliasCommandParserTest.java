package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.ResetAliasCommand;

public class ResetAliasCommandParserTest {
    private ResetAliasCommandParser parser = new ResetAliasCommandParser();

    @Test
    public void parse_nonEmptyArguments_throwParseException() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, ResetAliasCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "AC", expectedMessage);
    }

    @Test
    public void parse_emptyArguments_success() {
        assertParseSuccess(parser, "", new ResetAliasCommand());
    }
}
