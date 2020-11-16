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
 * Contains integration tests and unit tests for UnattendCommand.
 */
public class UnattendCommandTest {

    private static final String NEW_ATTENDANCE_STRING = "01/01/2020";
    private static final Attendance NEW_ATTENDANCE = Attendance.fromDateString(NEW_ATTENDANCE_STRING);
    private static final Attendance AMY_ATTENDANCE = Attendance.fromDateString(VALID_ATTENDANCE_AMY);

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person personToUnattend = model.getFilteredPersonList().get(0);
        personToUnattend = new PersonBuilder(personToUnattend)
                .withAttendance(VALID_ATTENDANCE_AMY, NEW_ATTENDANCE_STRING).build();
        UnattendCommand unattend = new UnattendCommand(INDEX_FIRST_PERSON, NEW_ATTENDANCE);
        String expectedMessage = String.format(UnattendCommand.MESSAGE_UNATTEND_SUCCESS, personToUnattend);

        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        // remove attendance
        model.setPerson(model.getFilteredPersonList().get(0), personToUnattend);

        assertCommandSuccess(unattend, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person unattendedPerson = new PersonBuilder(person)
                .withAttendance(VALID_ATTENDANCE_AMY, NEW_ATTENDANCE_STRING).build();
        UnattendCommand unattend = new UnattendCommand(INDEX_FIRST_PERSON, NEW_ATTENDANCE);

        String expectedMessage = String.format(UnattendCommand.MESSAGE_UNATTEND_SUCCESS, unattendedPerson);

        Model expectedModel = new ModelManager(new Tasker(model.getAddressBook()), new UserPrefs());
        // remove attendance
        model.setPerson(model.getFilteredPersonList().get(0), unattendedPerson);

        assertCommandSuccess(unattend, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullAttendanceUnfilteredList_failure() {
        UnattendCommand unattend = new UnattendCommand(INDEX_FIRST_PERSON, null);
        System.out.println(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE));
        assertCommandFailure(unattend, model,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_duplicateAttendanceFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        int personIndex = INDEX_FIRST_PERSON.getZeroBased();
        Person personToUnattend = model.getAddressBook().getPersonList().get(personIndex);

        personToUnattend = new PersonBuilder(personToUnattend)
                .withAttendance().build();
        model.setPerson(model.getFilteredPersonList().get(personIndex), personToUnattend);

        UnattendCommand unattend = new UnattendCommand(INDEX_FIRST_PERSON, AMY_ATTENDANCE);

        assertCommandFailure(unattend, model, String.format(UnattendCommand.MESSAGE_ALREADY_UNATTENDED,
                personToUnattend.getName(), AMY_ATTENDANCE));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnattendCommand unattend = new UnattendCommand(outOfBoundIndex, NEW_ATTENDANCE);

        assertCommandFailure(unattend, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Unattend filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnattendCommand unattend = new UnattendCommand(outOfBoundIndex, NEW_ATTENDANCE);

        assertCommandFailure(unattend, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        UnattendCommand unattend1 = new UnattendCommand(index1, NEW_ATTENDANCE);
        UnattendCommand unattend2 = new UnattendCommand(index2, NEW_ATTENDANCE);

        // same object -> returns true
        assertTrue(unattend1.equals(unattend1));

        // same values -> returns true
        UnattendCommand unattendCopy = new UnattendCommand(index1, NEW_ATTENDANCE);
        assertTrue(unattend1.equals(unattendCopy));

        // different types -> returns false
        assertFalse(unattend1.equals(1));

        // null -> returns false
        assertFalse(unattend1.equals(null));

        // different person -> returns false
        assertFalse(unattend1.equals(unattend2));
    }

}
