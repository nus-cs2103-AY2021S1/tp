package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INSUFFICIENT_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "n a", new SortCommand("n", "a"));
        assertParseSuccess(parser, "n d", new SortCommand("n", "d"));
        assertParseSuccess(parser, "p a", new SortCommand("p", "a"));
        assertParseSuccess(parser, "p d", new SortCommand("p", "d"));
    }

    @Test
    public void parse_optionalFieldMissing_success() {
        assertParseSuccess(parser, "n", new SortCommand("n", "t"));
        assertParseSuccess(parser, "p", new SortCommand("p", "t"));
    }


    @Test
    public void parse_invalidValues_failure() {
        // empty String
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_INSUFFICIENT_ARGUMENTS, SortCommand.COMMAND_WORD, 1, SortCommand.MESSAGE_USAGE)));

        // More than 2 arguments
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS,
                SortCommand.COMMAND_WORD, 2, SortCommand.MESSAGE_USAGE)));

        // Invalid String
        assertParseFailure(parser, "a", SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "j", SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "p 1", SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "p n", SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "n i", SortCommand.MESSAGE_USAGE);
    }
}
