package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_NON_EMPTY_ARGUMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CclearCommand;
import seedu.address.logic.commands.ClistCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MclearCommand;
import seedu.address.logic.commands.MlistCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.UnassignallCommand;

public class OneWordCommandParserTest {

    private OneWordCommandParser parser = new OneWordCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {

        assertParseSuccess(parser, "list",
            new ListCommand());
    }

    @Test
    public void parse_validArgs_returnsUnassignCommand() {

        assertParseSuccess(parser, "unassignall",
            new UnassignallCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, "list 11111 ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "cclear aaksbdkcbzdskjc ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, CclearCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "mclear dkadkl* ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, MclearCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "mlist *!@# ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, MlistCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "clist hahaa\n ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ClistCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "list ***\t ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "reset m/cs2103 n/budi",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ResetCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "unassignall 1",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, UnassignallCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "help 112",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, HelpCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "exit 1 1",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ExitCommand.MESSAGE_USAGE));
    }
}
