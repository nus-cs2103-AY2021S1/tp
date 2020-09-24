package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RemindCommand}.
 */
public class RemindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person assignmentToRemind = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentToRemind);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemindCommand remindCommand = new RemindCommand(outOfBoundIndex);

        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person assignmentToRemind = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(RemindCommand.MESSAGE_REMIND_ASSIGNMENT_SUCCESS, assignmentToRemind);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), assignmentToRemind);

        assertCommandSuccess(remindCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RemindCommand remindCommand = new RemindCommand(outOfBoundIndex);

        assertCommandFailure(remindCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyRemindedAssignmentUnfilteredList_failure() {
        // Set reminders for assignment in filtered list in address book
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person firstPersonReminded = new PersonBuilder(firstPerson).withRemindersSet().build();
        model.setPerson(firstPerson, firstPersonReminded);

        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void execute_alreadyRemindedAssignmentFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // Set reminders for assignment in filtered list in address book
        Person personInList = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personInListReminded = new PersonBuilder(personInList).withRemindersSet().build();
        model.setPerson(personInList, personInListReminded);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        RemindCommand remindCommand = new RemindCommand(INDEX_FIRST_PERSON);

        assertCommandFailure(remindCommand, model, RemindCommand.MESSAGE_REMINDED_ASSIGNMENT);
    }

    @Test
    public void equals() {
        RemindCommand remindFirstCommand = new RemindCommand(INDEX_FIRST_PERSON);
        RemindCommand remindSecondCommand = new RemindCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(remindFirstCommand.equals(remindFirstCommand));

        // same values -> returns true
        RemindCommand remindFirstCommandCopy = new RemindCommand(INDEX_FIRST_PERSON);
        assertTrue(remindFirstCommand.equals(remindFirstCommandCopy));

        // different types -> returns false
        assertFalse(remindFirstCommand.equals(1));

        // null -> returns false
        assertFalse(remindFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(remindFirstCommand.equals(remindSecondCommand));
    }
}
