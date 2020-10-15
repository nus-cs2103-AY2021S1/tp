package seedu.fma.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.AddCommand;
import seedu.fma.logic.commands.ClearCommand;
import seedu.fma.logic.commands.DeleteCommand;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.ExitCommand;
import seedu.fma.logic.commands.FindCommand;
import seedu.fma.logic.commands.HelpCommand;
import seedu.fma.logic.commands.ListCommand;
import seedu.fma.logic.parser.exceptions.ParseException;

class FixMyAbsParserTest {

    @Test
    void parseCommand_invalidCommand_throwsParseException() {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertThrows(ParseException.class, () -> parser.parseCommand("funny"));
        assertThrows(ParseException.class, () -> parser.parseCommand("ewfwe"));
        assertThrows(ParseException.class, () -> parser.parseCommand(""));
    }

    @Test
    void parseCommand_containsClearCommand_returnsClearCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(") instanceof ClearCommand);
    }

    @Test
    void parseCommand_containsFindCommand_returnsFindCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(") instanceof FindCommand);
    }

    @Test
    void parseCommand_containsListCommand_returnsListCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(") instanceof ListCommand);
    }

    @Test
    void parseCommand_containsExitCommand_returnsExitCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(") instanceof ExitCommand);
    }

    @Test
    void parseCommand_containsHelpCommand_returnsHelpCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(") instanceof HelpCommand);
    }

    @Test
    void parseCommand_containsAddCommand_returnsAddCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD
                + " e/Sit ups r/50 c/my abs hurt:(") instanceof AddCommand);
    }

    @Test
    void parseCommand_containsEditCommand_returnsEditCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD
                + " e/Sit ups r/50 c/my abs hurt:(") instanceof EditCommand);
    }

    @Test
    void parseCommand_containsDeleteCommand_returnsDeleteCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                + " e/Sit ups r/50 c/my abs hurt:(") instanceof DeleteCommand);
    }
}
