package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_NON_EMPTY_ARGUMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_validArgs_returnsUnassignCommand() {

        assertParseSuccess(parser, "",
            new ListCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, "11111 ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "aaksbdkcbzdskjc ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "*!@# ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "\n ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "\t ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "m/cs2103 n/budi",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));
    }
}
