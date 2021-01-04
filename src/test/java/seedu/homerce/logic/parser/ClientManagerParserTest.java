package seedu.homerce.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.homerce.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.ExitCommand;
import seedu.homerce.logic.commands.HelpCommand;
import seedu.homerce.logic.commands.client.AddClientCommand;
import seedu.homerce.logic.commands.client.ClearClientCommand;
import seedu.homerce.logic.commands.client.DeleteClientCommand;
import seedu.homerce.logic.commands.client.EditClientCommand;
import seedu.homerce.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.homerce.logic.commands.client.ListClientCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;
import seedu.homerce.model.client.Client;
import seedu.homerce.testutil.client.ClientBuilder;
import seedu.homerce.testutil.client.ClientUtil;
import seedu.homerce.testutil.client.EditClientDescriptorBuilder;

public class ClientManagerParserTest {

    private final HomerceParser parser = new HomerceParser();

    @Test
    public void parseCommand_add() throws Exception {
        Client client = new ClientBuilder().build();
        AddClientCommand command = (AddClientCommand) parser.parseCommand(ClientUtil.getAddCommand(client));
        assertEquals(new AddClientCommand(client), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearClientCommand.COMMAND_WORD) instanceof ClearClientCommand);
        assertTrue(parser.parseCommand(ClearClientCommand.COMMAND_WORD + " 3") instanceof ClearClientCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteClientCommand command = (DeleteClientCommand) parser.parseCommand(
                DeleteClientCommand.COMMAND_WORD + " " + INDEX_FIRST_CLIENT.getOneBased());
        assertEquals(new DeleteClientCommand(INDEX_FIRST_CLIENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Client client = new ClientBuilder().build();
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(client).build();
        EditClientCommand command = (EditClientCommand) parser.parseCommand(EditClientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_CLIENT.getOneBased() + " " + ClientUtil.getEditClientDescriptorDetails(descriptor));
        assertEquals(new EditClientCommand(INDEX_FIRST_CLIENT, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    // Update find test to meet new implementation
    //    @Test
    //    public void parseCommand_find() throws Exception {
    //        List<String> keywords = Arrays.asList("foo", "bar", "baz");
    //        FindClientCommand command = (FindClientCommand) parser.parseCommand(
    //                FindClientCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
    //        assertEquals(new FindClientCommand(new NameContainsKeywordsPredicate(keywords)), command);
    //    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD) instanceof ListClientCommand);
        assertTrue(parser.parseCommand(ListClientCommand.COMMAND_WORD + " 3") instanceof ListClientCommand);
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
