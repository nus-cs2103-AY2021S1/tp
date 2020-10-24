package seedu.fma.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.logic.commands.AddCommand;
import seedu.fma.logic.commands.AddExCommand;
import seedu.fma.logic.commands.ClearCommand;
import seedu.fma.logic.commands.DeleteCommand;
import seedu.fma.logic.commands.DeleteExCommand;
import seedu.fma.logic.commands.EditCommand;
import seedu.fma.logic.commands.EditExCommand;
import seedu.fma.logic.commands.ExitCommand;
import seedu.fma.logic.commands.FindCommand;
import seedu.fma.logic.commands.HelpCommand;
import seedu.fma.logic.commands.ListCommand;
import seedu.fma.logic.parser.exceptions.ParseException;
import seedu.fma.model.LogBook;

class FixMyAbsParserTest {
    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    void parseCommand_invalidCommand_throwsParseException() {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertThrows(ParseException.class, () -> parser.parseCommand("funny", logBook));
        assertThrows(ParseException.class, () -> parser.parseCommand("ewfwe", logBook));
        assertThrows(ParseException.class, () -> parser.parseCommand("", logBook));
    }

    @Test
    void parseCommand_containsClearCommand_returnsClearCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(", logBook) instanceof ClearCommand);
    }

    @Test
    void parseCommand_containsFindCommand_returnsFindCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(FindCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(", logBook) instanceof FindCommand);
    }

    @Test
    void parseCommand_containsListCommand_returnsListCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(", logBook) instanceof ListCommand);
    }

    @Test
    void parseCommand_containsExitCommand_returnsExitCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(", logBook) instanceof ExitCommand);
    }

    @Test
    void parseCommand_containsHelpCommand_returnsHelpCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD
                + " e/situp r/50 c/my abs hurt:(", logBook) instanceof HelpCommand);
    }

    @Test
    void parseCommand_containsAddCommand_returnsAddCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(AddCommand.COMMAND_WORD
                + " e/Sit ups r/50 c/my abs hurt:(", logBook) instanceof AddCommand);
    }

    @Test
    void parseCommand_containsEditCommand_returnsEditCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(EditCommand.COMMAND_WORD
                + " 1 r/50 c/my abs hurt:(", logBook) instanceof EditCommand);
    }

    @Test
    void parseCommand_containsDeleteCommand_returnsDeleteCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(DeleteCommand.COMMAND_WORD
                + " 1", logBook) instanceof DeleteCommand);
    }

    @Test
    void parseCommand_containsAddExCommand_returnsAddExCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(AddExCommand.COMMAND_WORD
                + " e/love c/100000", logBook) instanceof AddExCommand);
    }

    @Test
    void parseCommand_containsEditExCommand_returnsEditExCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(EditExCommand.COMMAND_WORD
                + " 1 c/100", logBook) instanceof EditExCommand);
    }

    @Test
    void parseCommand_containsDeleteExCommand_returnsDeleteExCommand() throws ParseException {
        FixMyAbsParser parser = new FixMyAbsParser();
        assertTrue(parser.parseCommand(DeleteExCommand.COMMAND_WORD
                + " 1", logBook) instanceof DeleteExCommand);
    }
}
