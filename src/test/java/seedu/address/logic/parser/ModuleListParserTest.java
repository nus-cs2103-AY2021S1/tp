package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.modulelistcommands.AddModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ClearModuleCommand;
import seedu.address.logic.commands.modulelistcommands.DeleteModuleCommand;
import seedu.address.logic.commands.modulelistcommands.ListModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.module.NameContainsKeywordsPredicate;
// import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.contact.ContactBuilder;
// import seedu.address.testutil.ModuleBuilder;


public class ModuleListParserTest {

    private final ModuleListParser parser = new ModuleListParser();

    @Test
    public void parseCommand_add() throws Exception {
        assertTrue(parser.parseCommand(
                AddModuleCommand.COMMAND_WORD + " " + PREFIX_NAME + "CS2100") instanceof AddModuleCommand);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearModuleCommand.COMMAND_WORD) instanceof ClearModuleCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(ClearModuleCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        Command command = parser.parseCommand(
                ModuleUtil.getDeleteCommand(INDEX_FIRST_CONTACT.getOneBased()));
        assertEquals(new DeleteModuleCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Contact person = new ContactBuilder().build();
        //EditModuleDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        //EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
        //        + INDEX_FIRST_PERSON.getOneBased() + " " + ModuleUtil.getEditPersonDescriptorDetails(descriptor));
        //assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindModuleCommand command = (FindModuleCommand) parser.parseCommand(
                FindModuleCommand.COMMAND_WORD + " " + keywords.stream()
                        .collect(Collectors.joining(" ")));
        assertEquals(new FindModuleCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListModuleCommand.COMMAND_WORD) instanceof ListModuleCommand);
        assertThrows(ParseException.class, () -> parser.parseCommand(ListModuleCommand.COMMAND_WORD + " 3"));
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

    @Test
    public void singleWordCommandTest_valid_success() throws ParseException {
        ModuleListParser moduleListParser = new ModuleListParser();
        Command command = moduleListParser.singleWordCommandsChecker(ExitCommand.COMMAND_WORD, "");
        Command expectedCommand = new ExitCommand();
        assertEquals(expectedCommand, command);
    }

    @Test
    public void singleWordCommandTest_invalid_failure() {
        ModuleListParser moduleListParser = new ModuleListParser();
        assertThrows(ParseException.class, () -> moduleListParser.singleWordCommandsChecker(ExitCommand.COMMAND_WORD,
                "123"));
    }
}
