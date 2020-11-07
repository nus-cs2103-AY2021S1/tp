package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.AddFinanceCommand;
import nustorage.logic.commands.ClearCommand;
import nustorage.logic.commands.CreateInventoryRecordCommand;
import nustorage.logic.commands.DeleteFinanceCommand;
import nustorage.logic.commands.DeleteInventoryRecordCommand;
import nustorage.logic.commands.EditFinanceCommand;
import nustorage.logic.commands.EditInventoryCommand;
import nustorage.logic.commands.ExitCommand;
import nustorage.logic.commands.FindFinanceCommand;
import nustorage.logic.commands.FindInventoryRecordCommand;
import nustorage.logic.commands.HelpCommand;
import nustorage.logic.commands.ListFinanceRecordsCommand;
import nustorage.logic.commands.ListInventoryCommand;
import nustorage.logic.parser.exceptions.ParseException;

public class NuStorageParserTest {

    private final NuStorageParser parser = new NuStorageParser();

    @Test
    public void parseCommand_createInventory() throws Exception {
        assertTrue(parser.parseCommand(CreateInventoryRecordCommand.COMMAND_WORD
                        + " i/iPhone q/30") instanceof CreateInventoryRecordCommand);

        assertTrue(parser.parseCommand(CreateInventoryRecordCommand.COMMAND_WORD
                + " i/iPad q/1 c/12") instanceof CreateInventoryRecordCommand);
    }


    @Test
    public void parseCommand_deleteInventory() throws Exception {
        assertTrue(parser.parseCommand(DeleteInventoryRecordCommand.COMMAND_WORD
                + " 1") instanceof DeleteInventoryRecordCommand);
    }

    @Test
    public void parseCommand_editInventory() throws Exception {
        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 1 q/300") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 2 i/changeItemName") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 3 c/30") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 4 i/changeItemName q/300") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 5 i/changeItemName c/30") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 5 q/300 c/30") instanceof EditInventoryCommand);

        assertTrue(parser.parseCommand(EditInventoryCommand.COMMAND_WORD
                + " 5 i/changeItemName q/300 c/30") instanceof EditInventoryCommand);
    }

    @Test
    public void parseCommand_findInventory() throws Exception {
        assertTrue(parser.parseCommand(FindInventoryRecordCommand.COMMAND_WORD
                + " keyword") instanceof FindInventoryRecordCommand);
    }

    @Test
    public void parseCommand_listInventory() throws Exception {
        assertTrue(parser.parseCommand(ListInventoryCommand.COMMAND_WORD) instanceof ListInventoryCommand);
        assertTrue(parser.parseCommand(ListInventoryCommand.COMMAND_WORD + " 3") instanceof ListInventoryCommand);
    }

    @Test
    public void parseCommand_addFinance() throws Exception {
        assertTrue(parser.parseCommand(AddFinanceCommand.COMMAND_WORD
                + " amt/3000") instanceof AddFinanceCommand);

        assertTrue(parser.parseCommand(AddFinanceCommand.COMMAND_WORD
                + " amt/3000 at/2020-09-09 00:00") instanceof AddFinanceCommand);
    }

    @Test
    public void parseCommand_deleteFinance() throws Exception {
        assertTrue(parser.parseCommand(DeleteFinanceCommand.COMMAND_WORD
                + " 1") instanceof DeleteFinanceCommand);
    }

    @Test
    public void parseCommand_editFinance() throws Exception {
        assertTrue(parser.parseCommand(EditFinanceCommand.COMMAND_WORD
                + " 1 amt/0") instanceof EditFinanceCommand);

        assertTrue(parser.parseCommand(EditFinanceCommand.COMMAND_WORD
                + " 2 at/2099-01-02 12:34") instanceof EditFinanceCommand);

        assertTrue(parser.parseCommand(EditFinanceCommand.COMMAND_WORD
                + " 3 amt/0 at/2099-01-02 12:34") instanceof EditFinanceCommand);
    }

    @Test
    public void parseCommand_findFinance() throws Exception {
        assertTrue(parser.parseCommand(FindFinanceCommand.COMMAND_WORD
                + " keyword") instanceof FindFinanceCommand);
    }

    @Test
    public void parseCommand_listFinance() throws Exception {
        assertTrue(parser.parseCommand(ListFinanceRecordsCommand.COMMAND_WORD) instanceof ListFinanceRecordsCommand);
        assertTrue(parser.parseCommand(ListFinanceRecordsCommand.COMMAND_WORD
                + " 3") instanceof ListFinanceRecordsCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }


    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
