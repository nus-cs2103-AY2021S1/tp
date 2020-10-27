package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.FILE_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_FILE_NAME_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.NAME_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_FILE_NAME;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.PrintCommand;

public class PrintCommandParserTest {
    private PrintCommandParser parser = new PrintCommandParser();

    @Test
    public void parse_invalidArgument_failure() {
        String expectedInvalidArgumentMessage =
                String.format(PrintCommandParser.INVALID_PRINT_ARGUMENT, PrintCommand.MESSAGE_USAGE);

        // invalid arguments
        assertParseFailure(parser, INVALID_FILE_NAME_DESC, expectedInvalidArgumentMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintCommand.MESSAGE_USAGE);

        // invalid header field with no required field header
        assertParseFailure(parser, NAME_DESC_BANANA, expectedMessage);

        // missing required field header
        assertParseFailure(parser, "", expectedMessage);
    }


    @Test
    public void parse_multipleCompulsoryFields_failure() {
        String expectedDuplicateHeaderMessage =
                String.format(MESSAGE_DUPLICATE_HEADER_FIELD, PrintCommand.MESSAGE_USAGE);

        // duplicate header field /fn
        assertParseFailure(parser, FILE_NAME_DESC + FILE_NAME_DESC, expectedDuplicateHeaderMessage);
    }

    @Test
    public void parse_allFieldPresent_success() {

        // whitespace only preamble, field header present
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + FILE_NAME_DESC, new PrintCommand(VALID_FILE_NAME));
    }
}
