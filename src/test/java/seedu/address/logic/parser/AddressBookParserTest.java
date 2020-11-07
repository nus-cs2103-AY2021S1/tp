package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddModCommand;
import seedu.address.logic.commands.CclearCommand;
import seedu.address.logic.commands.ClistCommand;
import seedu.address.logic.commands.DelModCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindModCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.MclearCommand;
import seedu.address.logic.commands.MlistCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.commands.UnassignallCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.predicates.ModuleCodeContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleInstructorsContainsKeywordsPredicate;
import seedu.address.model.module.predicates.ModuleNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.ModuleUtil;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.builders.EditPersonDescriptorBuilder;
import seedu.address.testutil.builders.ModuleBuilder;
import seedu.address.testutil.builders.PersonBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_addModule() throws Exception {
        Module module = new ModuleBuilder().build();
        AddModCommand command = (AddModCommand) parser.parseCommand(ModuleUtil.getAddModCommand(module));
        assertEquals(new AddModCommand(module), command);
    }

    @Test
    public void parseCommand_reset() throws Exception {
        assertTrue(parser.parseCommand(ResetCommand.COMMAND_WORD) instanceof ResetCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteModule() throws Exception {
        Module module = new ModuleBuilder().withCode("CS2103").build();
        DelModCommand command = (DelModCommand) parser.parseCommand(ModuleUtil.getDelModCommand(module));
        assertEquals(new DelModCommand(module.getModuleCode()), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_NAME + String.join(" ", keywords));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findModule() throws Exception {
        Person damith = new PersonBuilder().withName("Damith").build();
        Module module = new ModuleBuilder().withCode("CS2103")
                .withName("Software Engineering").withInstructors(damith).build();
        FindModCommand command = (FindModCommand) parser.parseCommand(ModuleUtil.getFindModCommand(module));

        ModuleCodeContainsKeywordsPredicate codePredicate = new ModuleCodeContainsKeywordsPredicate("CS2103");
        ModuleNameContainsKeywordsPredicate namePredicate =
                new ModuleNameContainsKeywordsPredicate(Arrays.asList("Software", "Engineering"));
        ModuleInstructorsContainsKeywordsPredicate instructorPredicate =
                new ModuleInstructorsContainsKeywordsPredicate(Collections.singletonList("Damith"));
        assertEquals(new FindModCommand(Arrays.asList(codePredicate, namePredicate, instructorPredicate)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listContacts() throws Exception {
        assertTrue(parser.parseCommand(ClistCommand.COMMAND_WORD) instanceof ClistCommand);
    }

    @Test
    public void parseCommand_clearContacts() throws Exception {
        assertTrue(parser.parseCommand(CclearCommand.COMMAND_WORD) instanceof CclearCommand);
    }

    @Test
    public void parseCommand_listModules() throws Exception {
        assertTrue(parser.parseCommand(MlistCommand.COMMAND_WORD) instanceof MlistCommand);
    }

    @Test
    public void parseCommand_clearModules() throws Exception {
        assertTrue(parser.parseCommand(MclearCommand.COMMAND_WORD) instanceof MclearCommand);
    }

    @Test
    public void parseCommand_switch() throws Exception {
        assertTrue(parser.parseCommand(SwitchCommand.COMMAND_WORD) instanceof SwitchCommand);
    }

    @Test
    public void parseCommand_unassignall() throws Exception {
        assertTrue(parser.parseCommand(UnassignallCommand.COMMAND_WORD) instanceof UnassignallCommand);
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
