package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class McGymmyParserCommandsTest {
    private final McGymmyParser parser = new McGymmyParser();

    @Test
    public void defaultCommands_added() {
        // if this breaks, you need to add the command in McGymmyParser.addDefaultCommands
        String[] commands = {"add", "edit", "delete", "clear", "find", "delete", "help", "list"};
        Set<String> registeredCommands = parser.getRegisteredCommands();
        for (String command : commands) {
            assertTrue(registeredCommands.contains(command));
        }
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parse(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parse(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parse(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        String searchString = "haiufas iuaohbfiasduo";
        Command command = parser.parse(FindCommand.COMMAND_WORD + " " + searchString);
        assertTrue(command instanceof FindCommand);
        assertEquals(CommandParserTestUtil.commandParameterValue(command, ""), Optional.of(searchString));
    }

    @Test
    public void parseCommand_findEmptyParameter_failure() {
        assertThrows(ParseException.class, () -> parser.parse(FindCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        String indexString = "68";
        Command command = parser.parse(DeleteCommand.COMMAND_WORD + " " + indexString);
        assertTrue(command instanceof DeleteCommand);
        assertEquals(CommandParserTestUtil.commandParameterValue(command, ""), Optional.of(indexString));
    }

    @Test
    public void parseCommand_deleteEmptyParameter_failure() {
        assertThrows(ParseException.class, () -> parser.parse(DeleteCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_add() throws Exception {
        String nameString = "michale json";
        String phoneString = "9912629";
        String emailString = "test@example.com";
        String commandString = String.format("%s -n %s -p %s -e %s",
            AddCommand.COMMAND_WORD,
            nameString,
            phoneString,
            emailString);
        Command command = parser.parse(commandString);
        assertTrue(command instanceof AddCommand);
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "n"), Optional.of(nameString));
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "p"), Optional.of(phoneString));
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "e"), Optional.of(emailString));
    }

    @Test
    public void parseCommand_addMissingParameters_failure() {
        String commandString = String.format("%s -n poop", AddCommand.COMMAND_WORD);
        assertThrows(ParseException.class, () -> parser.parse(commandString));
    }

    @Test
    public void parseCommand_edit() throws Exception {
        String indexString = "68";
        String phoneString = "88888888";
        String emailString = "test@google.cn";
        String commandString = String.format("%s %s -p %s -e %s",
            EditCommand.COMMAND_WORD,
            indexString,
            phoneString,
            emailString);
        Command command = parser.parse(commandString);
        assertTrue(command instanceof EditCommand);
        assertEquals(CommandParserTestUtil.commandParameterValue(command, ""), Optional.of(indexString));
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "n"), Optional.empty());
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "p"), Optional.of(phoneString));
        assertEquals(CommandParserTestUtil.commandParameterValue(command, "e"), Optional.of(emailString));
    }

    @Test
    public void parseCommand_editMissingIndex_failure() {
        String commandString = String.format("%s -n poop", EditCommand.COMMAND_WORD);
        assertThrows(ParseException.class, () -> parser.parse(commandString));
    }
}
