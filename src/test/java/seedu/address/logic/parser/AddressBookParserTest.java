package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddJobCommand;
import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearJobCommand;
import seedu.address.logic.commands.ClearPersonCommand;
import seedu.address.logic.commands.DeleteJobCommand;
import seedu.address.logic.commands.DeletePersonCommand;
import seedu.address.logic.commands.EditJobCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindJobCommand;
import seedu.address.logic.commands.FindPersonCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListJobCommand;
import seedu.address.logic.commands.ListPersonCommand;
import seedu.address.logic.commands.SortJobCommand;
import seedu.address.logic.commands.SortPersonCommand;
import seedu.address.logic.commands.ViewJobCommand;
import seedu.address.logic.commands.ViewPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.information.Job;
import seedu.address.model.information.Person;
import seedu.address.testutil.EditJobDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.JobBuilder;
import seedu.address.testutil.JobUtil;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addPerson() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddPersonCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_addJob() throws Exception {
        Job job = new JobBuilder().build();
        AddJobCommand command = (AddJobCommand) parser.parseCommand(JobUtil.getAddJobCommand(job));
        assertEquals(new AddJobCommand(job), command);
    }

    @Test
    public void parseCommand_clearPerson() throws Exception {
        assertTrue(parser.parseCommand(ClearPersonCommand.COMMAND_WORD) instanceof ClearPersonCommand);
        assertTrue(parser.parseCommand(ClearPersonCommand.COMMAND_WORD + "     ") instanceof ClearPersonCommand);
    }

    @Test
    public void parseCommand_clearJob() throws Exception {
        assertTrue(parser.parseCommand(ClearJobCommand.COMMAND_WORD) instanceof ClearJobCommand);
        assertTrue(parser.parseCommand(ClearJobCommand.COMMAND_WORD + "     ") instanceof ClearJobCommand);
    }


    @Test
    public void parseCommand_sortPerson() throws Exception {
        assertTrue(parser.parseCommand(SortPersonCommand.COMMAND_WORD + " type/n order/asc")
                instanceof SortPersonCommand);
    }

    @Test
    public void parseCommand_sortJob() throws Exception {
        assertTrue(parser.parseCommand(SortJobCommand.COMMAND_WORD + " type/n order/asc")
                instanceof SortJobCommand);
    }

    @Test
    public void parseCommand_findPerson() throws Exception {
        assertTrue(parser.parseCommand(FindPersonCommand.COMMAND_WORD + " n/name")
                instanceof FindPersonCommand);
    }

    @Test
    public void parseCommand_findJob() throws Exception {
        assertTrue(parser.parseCommand(FindJobCommand.COMMAND_WORD + " n/name")
                instanceof FindJobCommand);
    }

    @Test
    public void parseCommand_listPerson() throws Exception {
        assertTrue(parser.parseCommand(ListPersonCommand.COMMAND_WORD)
                instanceof ListPersonCommand);
    }

    @Test
    public void parseCommand_listJob() throws Exception {
        assertTrue(parser.parseCommand(ListJobCommand.COMMAND_WORD)
                instanceof ListJobCommand);
    }

    @Test
    public void parseCommand_viewPerson() throws Exception {
        assertTrue(parser.parseCommand(ViewPersonCommand.COMMAND_WORD + " 1")
                instanceof ViewPersonCommand);
    }

    @Test
    public void parseCommand_viewJob() throws Exception {
        assertTrue(parser.parseCommand(ViewJobCommand.COMMAND_WORD + " 1")
                instanceof ViewJobCommand);
    }

    @Test
    public void parseCommand_deletePerson() throws Exception {
        DeletePersonCommand command = (DeletePersonCommand) parser.parseCommand(
                DeletePersonCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePersonCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_deleteJob() throws Exception {
        DeleteJobCommand command = (DeleteJobCommand) parser.parseCommand(
                DeleteJobCommand.COMMAND_WORD + " " + INDEX_FIRST_JOB.getOneBased());
        assertEquals(new DeleteJobCommand(INDEX_FIRST_JOB), command);
    }

    @Test
    public void parseCommand_editPerson() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_editJob() throws Exception {
        Job job = new JobBuilder().build();
        EditJobCommand.EditJobDescriptor descriptor = new EditJobDescriptorBuilder(job).build();
        EditJobCommand command = (EditJobCommand) parser.parseCommand(EditJobCommand.COMMAND_WORD + " "
                + INDEX_FIRST_JOB.getOneBased() + " " + JobUtil.getEditJobDescriptorDetails(descriptor));
        assertEquals(new EditJobCommand(INDEX_FIRST_JOB, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + "    ") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + "    ") instanceof HelpCommand);
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
    public void parseCommand_unknownComplexCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknown command"));
    }
}
