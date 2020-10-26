package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.SetBinExpiryCommand;

public class SetBinExpiryCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBinExpiryCommand.HELP.getFullMessage());

    private SetBinExpiryCommandParser parser = new SetBinExpiryCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // no index specified
        assertParseFailure(parser, PREAMBLE_WHITESPACE, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative days
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // floating point days
        assertParseFailure(parser, "-5.04", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 j/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validFieldsSpecified_success() {
        assertParseSuccess(parser, "30", new SetBinExpiryCommand(30));
        assertParseSuccess(parser, "131", new SetBinExpiryCommand(131));
    }
}
