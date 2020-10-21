package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnassignallCommand;

public class UnassignallCommandParserTest {

    private UnassignallCommandParser parser = new UnassignallCommandParser();

    @Test
    public void parse_validArgs_returnsUnassignallCommand() {

        assertParseSuccess(parser, "",
            new UnassignallCommand());
    }

    @Test
    public void parse_invalidArgs_returnsUnassignallCommand() {

        assertParseFailure(parser, "adjsaja",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignallCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignallCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "*",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignallCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignallCommand.MESSAGE_USAGE));
    }

}
