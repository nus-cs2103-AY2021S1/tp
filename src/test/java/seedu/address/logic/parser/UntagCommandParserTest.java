package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UntagCommand;

public class UntagCommandParserTest {
    private UntagCommandParser parser = new UntagCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "1", new UntagCommand(Index.fromOneBased(1)));
        assertParseSuccess(parser, "2", new UntagCommand(Index.fromOneBased(2)));
    }

    @Test
    public void parse_invalidValues_failure() {
        // empty String
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_INSUFFICIENT_ARGUMENTS, UntagCommand.COMMAND_WORD, 1,
                        UntagCommand.MESSAGE_USAGE)));

        // Index passed is not a non-zero integer
        assertParseFailure(parser, "a", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));
        assertParseFailure(parser, "-1", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));

        // 0 is a boundary value
        assertParseFailure(parser, "0", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));
        assertParseFailure(parser, "1.4", String.format(Messages.MESSAGE_INVALID_INDEX,
                "Order Item Index"));

        // More than 1 argument
        assertParseFailure(parser, "1 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS,
                        UntagCommand.COMMAND_WORD, 1, UntagCommand.MESSAGE_USAGE)));
    }
}
