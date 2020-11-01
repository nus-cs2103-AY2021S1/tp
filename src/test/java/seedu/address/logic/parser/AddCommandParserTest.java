package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_QUANTITY;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;

public class AddCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "1 10", new AddCommand(Index.fromOneBased(1), 10));
        assertParseSuccess(parser, "2 3", new AddCommand(Index.fromOneBased(2), 3));
        assertParseSuccess(parser, "1 1", new AddCommand(Index.fromOneBased(1), 1));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        assertParseSuccess(parser, "2", new AddCommand(Index.fromOneBased(2), 1));
    }

    @Test
    public void parse_invalidValues_failure() {
        // empty String
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_INSUFFICIENT_ARGUMENTS, AddCommand.COMMAND_WORD, 1, AddCommand.MESSAGE_USAGE)));

        // More than 2 arguments
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS,
                AddCommand.COMMAND_WORD, 2, AddCommand.MESSAGE_USAGE)));

        // Index passed is not a non-zero integer
        assertParseFailure(parser, "1.4", String.format(MESSAGE_INVALID_INDEX, "Menu Index"));
        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_INDEX, "Menu Index"));
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_INDEX, "Menu Index"));

        // Quantity passed is not a non-zero integer
        assertParseFailure(parser, "1 1.2", MESSAGE_INVALID_QUANTITY);
        assertParseFailure(parser, "1 0", MESSAGE_INVALID_QUANTITY);
        assertParseFailure(parser, "1 -1", MESSAGE_INVALID_QUANTITY);
    }
}
