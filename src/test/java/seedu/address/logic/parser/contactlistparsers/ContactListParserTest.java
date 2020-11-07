package seedu.address.logic.parser.contactlistparsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.contactlistcommands.AddContactCommand;
import seedu.address.logic.commands.contactlistcommands.ClearContactCommand;
import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactCommand;
import seedu.address.logic.commands.contactlistcommands.EditContactDescriptor;
import seedu.address.logic.commands.contactlistcommands.FindContactCommand;
import seedu.address.logic.commands.contactlistcommands.ImportantContactCommand;
import seedu.address.logic.commands.contactlistcommands.ListContactCommand;
import seedu.address.logic.commands.contactlistcommands.ResetContactCommand;
import seedu.address.logic.commands.contactlistcommands.SortContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.address.model.contact.FindContactCriteria;
import seedu.address.testutil.contact.ContactBuilder;
import seedu.address.testutil.contact.ContactUtil;
import seedu.address.testutil.contact.EditContactDescriptorBuilder;

public class ContactListParserTest {

    private final ContactListParser parser = new ContactListParser();

    @Test
    public void parseCommand_addContactInput_commandCreated() throws ParseException {
        Contact contact = new ContactBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(ContactUtil.getAddCommand(contact));
        assertEquals(new AddContactCommand(contact), command);
    }

    @Test
    public void parseCommand_editContactInput_commandCreated() throws ParseException {
        Contact contact = new ContactBuilder().build();
        EditContactDescriptor descriptor = new EditContactDescriptorBuilder(contact).build();
        EditContactCommand command = (EditContactCommand) parser
                .parseCommand(EditContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased()
                + " " + ContactUtil.getEditContactDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST_CONTACT, descriptor), command);
    }

    @Test
    public void parseCommand_deleteContactInput_commandCreated() throws ParseException {
        DeleteContactCommand command = (DeleteContactCommand) parser
                .parseCommand(DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_clearContactInput_commandCreated() throws ParseException {
        assertTrue(parser.parseCommand(ClearContactCommand.COMMAND_WORD) instanceof ClearContactCommand);
    }

    @Test
    public void parseCommand_findContactInput_commandCreated() throws ParseException {
        List<String> keywords = Arrays.asList("one", "two", "three");
        FindContactCommand command = (FindContactCommand) parser.parseCommand(FindContactCommand.COMMAND_WORD
                + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        FindContactCriteria findContactCriteria = new FindContactCriteria();
        findContactCriteria.addPredicate(new ContactNameContainsKeywordsPredicate(keywords));
        assertEquals(new FindContactCommand(findContactCriteria), command);
    }

    @Test
    public void parseCommand_listContactInput_commandCreated() throws ParseException {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_markContactImportantInput_commandCreated() throws ParseException {
        ImportantContactCommand command = (ImportantContactCommand) parser.parseCommand(
                ImportantContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new ImportantContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_resetContactInput_commandCreated() throws ParseException {
        ResetContactCommand command = (ResetContactCommand) parser.parseCommand(
                ResetContactCommand.COMMAND_WORD + " " + INDEX_FIRST_CONTACT.getOneBased());
        assertEquals(new ResetContactCommand(INDEX_FIRST_CONTACT), command);
    }

    @Test
    public void parseCommand_sortContactInput_commandCreated() throws ParseException {
        assertTrue(parser.parseCommand(SortContactCommand.COMMAND_WORD) instanceof SortContactCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknown Command"));
    }

}
