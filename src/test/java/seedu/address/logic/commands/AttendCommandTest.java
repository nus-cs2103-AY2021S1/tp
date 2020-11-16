package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Tasker;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests and unit tests for AttendCommand.
 */
public class AttendCommandTest {

    private static final String NEW_ATTENDANCE_STRING = "01/01/2020";
    private static final Attendance NEW_ATTENDANCE = Attendance.fromDateString(NEW_ATTENDANCE_STRING);
    private static final Attendance AMY_ATTENDANCE = Attendance.fromDateString(VALID_ATTENDANCE_AMY);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person attendedPerson = model.getFilteredPersonList().get(0);
        attendedPerson = new PersonBuilder(attendedPerson)
                .withAttendance(VALID_ATTENDANCE_AMY, NEW_ATTENDANCE_STRING).build();
        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, NEW_ATTENDANCE);

        String expectedMessage = String.format(AttendCommand.MESSAGE_ATTEND_SUCCESS, attendedPerson);

        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), attendedPerson);

        assertCommandSuccess(attendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person attendedPerson = new PersonBuilder(personInFilteredList)
                .withAttendance(VALID_ATTENDANCE_AMY, NEW_ATTENDANCE_STRING).build();
        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, NEW_ATTENDANCE);

        String expectedMessage = String.format(AttendCommand.MESSAGE_ATTEND_SUCCESS, attendedPerson);

        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), attendedPerson);

        assertCommandSuccess(attendCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullAttendanceUnfilteredList_failure() {
        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, null);

        assertCommandFailure(attendCommand, model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_duplicateAttendanceFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToAttend = model.getAddressBook().getPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        AttendCommand attendCommand = new AttendCommand(INDEX_FIRST_PERSON, AMY_ATTENDANCE);

        assertCommandFailure(attendCommand, model, String.format(AttendCommand.MESSAGE_ALREADY_ATTENDED,
                personToAttend.getName(), AMY_ATTENDANCE));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AttendCommand attendCommand = new AttendCommand(outOfBoundIndex, NEW_ATTENDANCE);

        assertCommandFailure(attendCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Attend filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AttendCommand attendCommand = new AttendCommand(outOfBoundIndex, NEW_ATTENDANCE);

        assertCommandFailure(attendCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        AttendCommand attendCommand1 = new AttendCommand(index1, NEW_ATTENDANCE);
        AttendCommand attendCommand2 = new AttendCommand(index2, NEW_ATTENDANCE);

        // same object -> returns true
        assertTrue(attendCommand1.equals(attendCommand1));

        // same values -> returns true
        AttendCommand attendCommand1Copy = new AttendCommand(index1, NEW_ATTENDANCE);
        assertTrue(attendCommand1.equals(attendCommand1Copy));

        // different types -> returns false
        assertFalse(attendCommand1.equals(1));

        // null -> returns false
        assertFalse(attendCommand1.equals(null));

        // different person -> returns false
        assertFalse(attendCommand1.equals(attendCommand2));
    }

}
