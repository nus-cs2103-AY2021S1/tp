package seedu.expense.logic.parser;

import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.expense.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.AliasCommand;

public class AliasCommandParserTest {
    private AliasCommandParser parser = new AliasCommandParser();

    @Test
    public void parse_tooManyArguments_throwParseException() {
        String expectedMessage = String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "A B     C", expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AliasCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, "find", expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, "get", expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "chow", expectedMessage);
    }

    @Test
    public void parse_twoWords_success() {
        // parse-able does not guarantee executable
        assertParseSuccess(parser, "abc def", new AliasCommand("abc", "def"));
    }
}
