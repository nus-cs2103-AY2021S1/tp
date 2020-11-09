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
import seedu.address.logic.commands.RemoveCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the RemoveCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the RemoveCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class RemoveCommandParserTest {

    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "1 10", new RemoveCommand(Index.fromOneBased(1), 10));
        assertParseSuccess(parser, "2 3", new RemoveCommand(Index.fromOneBased(2), 3));
        assertParseSuccess(parser, "1 1", new RemoveCommand(Index.fromOneBased(1), 1));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        assertParseSuccess(parser, "2", new RemoveCommand(Index.fromOneBased(2), 1));
        assertParseSuccess(parser, "5", new RemoveCommand(Index.fromOneBased(5), 1));
    }

    @Test
    public void parse_invalidValues_failure() {
        // empty String
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_INSUFFICIENT_ARGUMENTS,
                        RemoveCommand.COMMAND_WORD, 1, RemoveCommand.MESSAGE_USAGE)));

        // More than 2 arguments
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS,
                        RemoveCommand.COMMAND_WORD, 2, RemoveCommand.MESSAGE_USAGE)));

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
