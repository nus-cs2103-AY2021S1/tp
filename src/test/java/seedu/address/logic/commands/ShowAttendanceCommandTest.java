package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.Attendance;


/**
 * Contains integration and unit tests for ShowAttendanceCommandTest.
 */
public class ShowAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_presentAttendance_success() {
        Attendance attendance = Attendance.fromDateString("27/03/1998");
        String message = "Alice Pauline was present on " + attendance.date.toString()
            + "\nYou may view results in the Attendance tab.";

        ShowAttendanceCommand showAttendanceCommand = new ShowAttendanceCommand(INDEX_FIRST_PERSON, attendance);
        assertCommandSuccess(showAttendanceCommand, model, message, expectedModel);
    }

    @Test
    public void execute_absentAttendance_success() {
        Attendance attendance = Attendance.fromDateString("28/03/1998");
        String message = "Alice Pauline was absent on " + attendance.date.toString()
            + "\nYou may view results in the Attendance tab.";

        ShowAttendanceCommand showAttendanceCommand = new ShowAttendanceCommand(INDEX_FIRST_PERSON, attendance);
        assertCommandSuccess(showAttendanceCommand, model, message, expectedModel);
    }
}
