package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PERSON_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonWithId;
import static seedu.address.testutil.TypicalBookings.getTypicalBookingBook;
import static seedu.address.testutil.TypicalPersons.VALID_PERSON_ID_ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;
import static seedu.address.testutil.TypicalRoomService.getTypicalRoomServiceBook;
import static seedu.address.testutil.TypicalRooms.getTypicalRoomBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalPersonBook(), new UserPrefs(), getTypicalRoomBook(),
            getTypicalBookingBook(), getTypicalRoomServiceBook());

    @Test
    public void execute_validPersonIdUnfilteredList_success() {
        Person personToDelete = model.getPersonWithId(VALID_PERSON_ID_ALICE);
        DeleteCommand deleteCommand = new DeleteCommand(VALID_PERSON_ID_ALICE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(model.getPersonBook(), new UserPrefs(), model.getRoomBook(),
                model.getBookingBook(), model.getRoomServiceBook());
        expectedModel.deletePerson(personToDelete);
        expectedModel.deleteBookingByPersonId(VALID_PERSON_ID_ALICE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistPersonIdUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(INVALID_PERSON_ID);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_ID_MISSING);
    }

    @Test
    public void execute_nonExistPersonIdFilteredList_throwsCommandException() {
        showPersonWithId(model, VALID_PERSON_ID_ALICE);
        DeleteCommand deleteCommand = new DeleteCommand(INVALID_PERSON_ID);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_PERSON_ID_MISSING);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(VALID_PERSON_ID_AMY);
        DeleteCommand deleteSecondCommand = new DeleteCommand(VALID_PERSON_ID_BOB);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(VALID_PERSON_ID_AMY);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
