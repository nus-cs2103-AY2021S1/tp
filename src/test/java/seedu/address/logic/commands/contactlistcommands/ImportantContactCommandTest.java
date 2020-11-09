package seedu.address.logic.commands.contactlistcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CONTACT;
import static seedu.address.testutil.contact.TypicalContacts.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.EventList;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;

public class ImportantContactCommandTest {

    // first index of todo list is not completed, second index is completed
    private ModelManager model = new ModelManager(
        new ModuleList(),
        new ModuleList(),
        getTypicalContactList(),
        new TodoList(),
        new EventList(),
        new UserPrefs());

    @Test
    public void execute_validIndexNotImportantToImportantUnfilteredList_resetSuccessful() {
        Contact contactToMark = model.getFilteredContactList().get(INDEX_SECOND_CONTACT.getZeroBased());
        contactToMark = contactToMark.markAsNotImportant();
        ImportantContactCommand importantContactCommand = new ImportantContactCommand(INDEX_SECOND_CONTACT);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            getTypicalContactList(),
            new TodoList(),
            new EventList(),
            new UserPrefs());
        Contact markedContact = model.getFilteredContactList().get(INDEX_SECOND_CONTACT.getZeroBased());
        markedContact = markedContact.markAsImportant();
        expectedModel.setContact(contactToMark, markedContact);

        String expectedMessage = String.format(ImportantContactCommand.MESSAGE_MARK_CONTACT_SUCCESS, markedContact);

        assertCommandSuccess(importantContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexImportantToImportantUnfilteredList_resetSuccessful() {
        Contact contactToMark = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        contactToMark = contactToMark.markAsImportant();
        ImportantContactCommand importantContactCommand = new ImportantContactCommand(INDEX_FIRST_CONTACT);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            getTypicalContactList(),
            new TodoList(),
            new EventList(),
            new UserPrefs());
        Contact markedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        markedContact = markedContact.markAsImportant();
        expectedModel.setContact(contactToMark, markedContact);

        String expectedMessage = String.format(ImportantContactCommand.MESSAGE_MARK_CONTACT_SUCCESS, markedContact);

        assertCommandSuccess(importantContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        ImportantContactCommand importantContactCommand = new ImportantContactCommand(outOfBoundIndex);

        assertCommandFailure(importantContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexNotImportantToImportantFilteredList_success() {
        showContactAtIndex(model, INDEX_SECOND_CONTACT);

        Contact contactToMark = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        contactToMark = contactToMark.markAsNotImportant();
        ImportantContactCommand importantContactCommand = new ImportantContactCommand(INDEX_FIRST_CONTACT);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            getTypicalContactList(),
            new TodoList(),
            new EventList(),
            new UserPrefs());
        Contact markedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        markedContact = markedContact.markAsImportant();
        expectedModel.setContact(contactToMark, markedContact);

        showContactAtIndex(expectedModel, INDEX_SECOND_CONTACT);

        String expectedMessage = String.format(ImportantContactCommand.MESSAGE_MARK_CONTACT_SUCCESS, markedContact);

        assertCommandSuccess(importantContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexImportantToImportantFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToMark = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        contactToMark = contactToMark.markAsImportant();
        ImportantContactCommand importantContactCommand = new ImportantContactCommand(INDEX_FIRST_CONTACT);

        ModelManager expectedModel = new ModelManager(
            new ModuleList(),
            new ModuleList(),
            getTypicalContactList(),
            new TodoList(),
            new EventList(),
            new UserPrefs());
        Contact markedContact = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        markedContact = markedContact.markAsImportant();
        expectedModel.setContact(contactToMark, markedContact);

        showContactAtIndex(expectedModel, INDEX_SECOND_CONTACT);

        String expectedMessage = String.format(ImportantContactCommand.MESSAGE_MARK_CONTACT_SUCCESS, markedContact);

        assertCommandSuccess(importantContactCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getContactList().size());

        ImportantContactCommand importantContactCommand = new ImportantContactCommand(outOfBoundIndex);

        assertCommandFailure(importantContactCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ImportantContactCommand resetSecondCommand = new ImportantContactCommand(INDEX_SECOND_CONTACT);
        ImportantContactCommand resetThirdCommand = new ImportantContactCommand(INDEX_THIRD_CONTACT);

        // same object -> returns true
        assertTrue(resetSecondCommand.equals(resetSecondCommand));

        // same values -> returns true
        ImportantContactCommand resetSecondCommandCopy = new ImportantContactCommand(INDEX_SECOND_CONTACT);
        assertTrue(resetSecondCommand.equals(resetSecondCommandCopy));

        // different types -> returns false
        assertFalse(resetSecondCommand.equals(1));

        // null -> returns false
        assertFalse(resetSecondCommand.equals(null));

        // different Contact -> returns false
        assertFalse(resetSecondCommand.equals(resetThirdCommand));
    }
}
