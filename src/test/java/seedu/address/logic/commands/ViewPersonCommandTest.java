package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewPersonCommand.
 */
public class ViewPersonCommandTest {

    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDisplay = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewPersonCommand viewPersonCommand = new ViewPersonCommand(INDEX_FIRST_PERSON);
        model.setDisplayedPerson(personToDisplay);
        String expectedMessage = String.format(ViewPersonCommand.MESSAGE_DISPLAY_PERSON_SUCCESS, personToDisplay);
        Model expectedModel = new ModelManager(model.getPersonAddressBook(), model.getJobAddressBook(),
                new UserPrefs());

        assertEquals(model.getDisplayedPerson(), personToDisplay);
        assertFalse(model.getDisplayedPerson().equals(null));
        assertCommandSuccess(viewPersonCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewPersonCommand viewPersonCommand = new ViewPersonCommand(outOfBoundIndex);

        assertCommandFailure(viewPersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewPersonCommand viewPersonFirstCommand = new ViewPersonCommand(INDEX_FIRST_PERSON);
        ViewPersonCommand viewPersonSecondCommand = new ViewPersonCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewPersonFirstCommand.equals(viewPersonFirstCommand));

        // same values -> returns true
        ViewPersonCommand viewPersonFirstCommandCopy = new ViewPersonCommand(INDEX_FIRST_PERSON);
        assertTrue(viewPersonFirstCommand.equals(viewPersonFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewPersonFirstCommand.equals(viewPersonSecondCommand));
    }
}
