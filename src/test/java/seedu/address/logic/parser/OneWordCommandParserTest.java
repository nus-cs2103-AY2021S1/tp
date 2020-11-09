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
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.UnassignallCommand;

public class OneWordCommandParserTest {

    private OneWordCommandParser parser = new OneWordCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {

        assertParseSuccess(parser, "list",
            new ListCommand());
    }

    @Test
    public void parse_validArgs_returnsUnassignAllCommand() {

        assertParseSuccess(parser, "unassignall",
            new UnassignallCommand());
    }

    @Test
    public void parse_validArgs_returnsMlistCommand() {

        assertParseSuccess(parser, "mlist",
                new MlistCommand());
    }

    @Test
    public void parse_validArgs_returnsClistCommand() {

        assertParseSuccess(parser, "clist",
                new ClistCommand());
    }

    @Test
    public void parse_validArgs_returnsCclearCommand() {

        assertParseSuccess(parser, "cclear",
                new CclearCommand());
    }

    @Test
    public void parse_validArgs_returnsMclearCommand() {

        assertParseSuccess(parser, "mclear",
                new MclearCommand());
    }

    @Test
    public void parse_validArgs_returnsResetCommand() {

        assertParseSuccess(parser, "reset",
                new ResetCommand());
    }

    @Test
    public void parse_validArgs_returnsSwitchCommand() {

        assertParseSuccess(parser, "switch",
                new SwitchCommand());
    }

    @Test
    public void parse_validArgs_returnsHelpCommand() {

        assertParseSuccess(parser, "help",
                new HelpCommand());
    }

    @Test
    public void parse_validArgs_returnsExitCommand() {

        assertParseSuccess(parser, "exit",
                new ExitCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        assertParseFailure(parser, "list 11111 ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.COMMAND_WORD)
                + ListCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "cclear aaksbdkcbzdskjc ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, CclearCommand.COMMAND_WORD)
                + CclearCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "mclear dkadkl* ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, MclearCommand.COMMAND_WORD)
                + MclearCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "mlist *!@# ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, MlistCommand.COMMAND_WORD)
                + MlistCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "clist hahaa\n ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ClistCommand.COMMAND_WORD)
                + ClistCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "list ***\t ",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ListCommand.COMMAND_WORD)
                + ListCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "reset m/cs2103 n/budi",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ResetCommand.COMMAND_WORD)
                + ResetCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "unassignall 1",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, UnassignallCommand.COMMAND_WORD)
                + UnassignallCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "switch !",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, SwitchCommand.COMMAND_WORD)
                + SwitchCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "help 112",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, HelpCommand.COMMAND_WORD)
                + HelpCommand.MESSAGE_USAGE);

        assertParseFailure(parser, "exit 1 1",
            String.format(MESSAGE_NON_EMPTY_ARGUMENT, ExitCommand.COMMAND_WORD)
                + ExitCommand.MESSAGE_USAGE);
    }
}
