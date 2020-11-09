package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.address.testutil.TypicalJobs.getTypicalJobAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.information.Job;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewPersonCommand.
 */
public class ViewJobCommandTest {

    private Model model = new ModelManager(getTypicalPersonAddressBook(), getTypicalJobAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Job jobToDisplay = model.getFilteredJobList().get(INDEX_FIRST_JOB.getZeroBased());
        ViewJobCommand viewJobCommand = new ViewJobCommand(INDEX_FIRST_JOB);
        model.setDisplayedJob(jobToDisplay);
        String expectedMessage = String.format(ViewJobCommand.MESSAGE_DISPLAY_PERSON_SUCCESS, jobToDisplay);
        Model expectedModel = new ModelManager(model.getPersonAddressBook(), model.getJobAddressBook(),
                new UserPrefs());

        assertEquals(model.getDisplayedJob(), jobToDisplay);
        assertFalse(model.getDisplayedJob().equals(null));
        assertCommandSuccess(viewJobCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredJobList().size() + 1);
        ViewJobCommand viewJobCommand = new ViewJobCommand(outOfBoundIndex);

        assertCommandFailure(viewJobCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewJobCommand viewJobFirstCommand = new ViewJobCommand(INDEX_FIRST_JOB);
        ViewJobCommand viewJobSecondCommand = new ViewJobCommand(INDEX_SECOND_JOB);

        // same object -> returns true
        assertTrue(viewJobFirstCommand.equals(viewJobFirstCommand));

        // same values -> returns true
        ViewJobCommand viewJobFirstCommandCopy = new ViewJobCommand(INDEX_FIRST_PERSON);
        assertTrue(viewJobFirstCommand.equals(viewJobFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewJobFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewJobFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewJobFirstCommand.equals(viewJobSecondCommand));
    }
}
