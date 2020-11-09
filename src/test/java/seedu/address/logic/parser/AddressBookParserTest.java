package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ContactTaskTagCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.add.AddContactCommand;
import seedu.address.logic.commands.add.AddEventCommand;
import seedu.address.logic.commands.add.AddTodoCommand;
import seedu.address.logic.commands.delete.DeleteContactCommand;
import seedu.address.logic.commands.due.DueAtCommand;
import seedu.address.logic.commands.due.DueBeforeCommand;
import seedu.address.logic.commands.edit.EditContactCommand;
import seedu.address.logic.commands.edit.EditContactCommand.EditPersonDescriptor;
import seedu.address.logic.commands.find.FindContactCommand;
import seedu.address.logic.commands.link.LinkCollaborativeCommand;
import seedu.address.logic.commands.list.ListContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactMatchesFindKeywordPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Event;
import seedu.address.model.task.Todo;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TodoBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_addContact() throws Exception {
        Person person = new PersonBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddContactCommand(person), command);
    }

    @Test
    public void parseCommand_addTodo() throws Exception {
        Todo todo = new TodoBuilder().build();
        AddTodoCommand command = (AddTodoCommand) parser.parseCommand(AddTodoCommand.COMMAND_WORD
                + " todo desc/homework date/14-12-2020 time/2359");
        assertEquals(new AddTodoCommand(todo), command);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        Event event = new EventBuilder().build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(AddEventCommand.COMMAND_WORD
                + " event desc/meeting startdate/12-12-2020 starttime/1000 enddate/12-12-2020 endtime/1130");
        assertEquals(new AddEventCommand(event), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " contact ") instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " contact 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " contact " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD
                + " contact i/" + INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywordsName = Arrays.asList("foo", "bar", "baz");
        String keywordTag = "tag";
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " contact n/"
                        + keywordsName.stream().collect(Collectors.joining(" ")) + " t/" + keywordTag);
        assertEquals(new FindContactCommand(new ContactMatchesFindKeywordPredicate(keywordsName, keywordTag)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " contact ") instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_list_throwsParseException() throws Exception {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_link() throws Exception {
        LinkCollaborativeCommand command = (LinkCollaborativeCommand) parser.parseCommand(
            LinkCommand.COMMAND_WORD + " doc desc/proposal url/https://www.google.com i/1");
        assertEquals(new LinkCollaborativeCommand(ParserUtil.parseIndex("1"),
            new CollaborativeLink("proposal", "https://www.google.com")), command);
    }

    @Test
    public void parseCommand_dueBefore() throws Exception {
        assertTrue(parser.parseCommand(DueBeforeCommand.COMMAND_WORD + " date/12-10-2020 time/2359")
            instanceof DueBeforeCommand);
    }

    @Test
    public void parseCommand_dueAt() throws Exception {
        assertTrue(parser.parseCommand(DueAtCommand.COMMAND_WORD + " date/12-10-2020 time/2359")
            instanceof DueAtCommand);
    }

    @Test
    public void parseCommand_done() throws Exception {
        assertTrue(parser.parseCommand(DoneCommand.COMMAND_WORD + " 1") instanceof DoneCommand);
    }

    @Test
    public void parseCommand_sort() throws Exception {
        assertTrue(parser.parseCommand(SortCommand.COMMAND_WORD + " contact") instanceof SortCommand);
    }

    @Test
    public void parseCommand_contactTaskTag() throws Exception {
        assertTrue(parser.parseCommand(ContactTaskTagCommand.COMMAND_WORD + " t/CS2100 contactIndex/1 taskIndex/2")
            instanceof ContactTaskTagCommand);
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
