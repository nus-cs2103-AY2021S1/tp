package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBookWithMember;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.FullNameMatchesKeywordPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model, Meetings, UndoCommand, RedoCommand,) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
        new UserPrefs());

    private Model modelWithMembersInMeetings = new ModelManager(getTypicalAddressBook(),
            getTypicalMeetingBookWithMember(), getTypicalModuleBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ArrayList<String> nameOne = new ArrayList<>();
        nameOne.add(personToDelete.getName().fullName);
        DeleteCommand deleteCommand = new DeleteCommand(
                new FullNameMatchesKeywordPredicate(nameOne), new ArrayList<>()
        );

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName().toString(), false, false, true, false);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getMeetingBook(),
            model.getModuleBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validNameAndNameInOneMeeting_success() {
        Person personToDelete = ALICE;
        ArrayList<String> nameOne = new ArrayList<>();
        nameOne.add(personToDelete.getName().fullName);
        DeleteCommand deleteCommand = new DeleteCommand(
                new FullNameMatchesKeywordPredicate(nameOne), new ArrayList<>()
        );
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
                personToDelete.getName().toString());

        Model expectedModel = new ModelManager(getTypicalAddressBook(),
                getTypicalMeetingBookWithMember(), getTypicalModuleBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.updatePersonInModuleBook(personToDelete);
        expectedModel.updatePersonInMeetingBook(personToDelete);

        assertCommandSuccess(deleteCommand, modelWithMembersInMeetings, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteContactInSelectedMeeting_updatesSelectedMeeting() throws CommandException {
        Meeting selectedMeeting = model.getSelectedMeeting();

        ArrayList<String> name = new ArrayList<>();
        name.add(selectedMeeting.getParticipants().stream().collect(Collectors.toList()).get(0).getName().toString());
        new DeleteCommand(new FullNameMatchesKeywordPredicate(name), new ArrayList<>()
        ).execute(model);

        assertNull(model.getSelectedMeeting());
    }

    @Test
    public void equals() {
        ArrayList<String> nameOne = new ArrayList<>();
        nameOne.add(ALICE.getName().fullName);
        ArrayList<String> nameTwo = new ArrayList<>();
        nameTwo.add(BOB.getName().fullName);
        DeleteCommand deleteFirstCommand = new DeleteCommand(
                new FullNameMatchesKeywordPredicate(nameOne), new ArrayList<>());
        DeleteCommand deleteSecondCommand = new DeleteCommand(
                new FullNameMatchesKeywordPredicate(nameTwo), new ArrayList<>());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(
                new FullNameMatchesKeywordPredicate(nameOne), new ArrayList<>());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
