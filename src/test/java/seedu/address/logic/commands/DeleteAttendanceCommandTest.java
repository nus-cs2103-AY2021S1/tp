package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalStudents.ATTENDANCE_TEST;
import static seedu.address.testutil.TypicalStudents.ATTENDANCE_TEST_WEEK_2;
import static seedu.address.testutil.TypicalTutorialGroups.B06;
import static seedu.address.testutil.TypicalTutorialGroups.S12;
import static seedu.address.testutil.TypicalTutorialGroups.V04;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.student.Attendance;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteAttendanceCommandTest {

    @Test
    public void execute_validIndexValidWeekDeleteAttendance_success() {
        Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
        Module moduleInView = CS2103T;
        TutorialGroup tgInView = B06;
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        model.addStudent(ATTENDANCE_TEST_WEEK_2);
        int[] validWeek = new int[]{2};
        DeleteAttendanceCommand command = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, validWeek);
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        String expectedMessage = String.format(DeleteAttendanceCommand.MESSAGE_DELETE_ATTENDANCE_SUCCESS,
                ATTENDANCE_TEST_WEEK_2.getName(), "week(s): ");
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexInvalidNegativeWeekDeleteAttendance_failure() {
        Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
        Module moduleInView = CS2103T;
        TutorialGroup tgInView = S12;
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        model.addStudent(ATTENDANCE_TEST);
        int[] invalidNegativeWeek = new int[]{-1};
        DeleteAttendanceCommand command = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, invalidNegativeWeek);
        assertCommandFailure(command, model, Attendance.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_validIndexInvalidWeekDeleteAttendance_failure() {
        Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
        Module moduleInView = CS2103T;
        TutorialGroup tgInView = V04;
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        model.addStudent(ATTENDANCE_TEST);
        int[] invalidNegativeWeek = new int[]{14};
        DeleteAttendanceCommand command = new DeleteAttendanceCommand(INDEX_FIRST_PERSON, invalidNegativeWeek);
        assertCommandFailure(command, model, Attendance.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_invalidIndexValidWeekDeleteAttendance_failure() {
        Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
        Module moduleInView = CS2103T;
        TutorialGroup tgInView = V04;
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        int[] validWeek = new int[]{2};
        Index invalidIndex = Index.fromZeroBased(1);
        DeleteAttendanceCommand command = new DeleteAttendanceCommand(invalidIndex, validWeek);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);

    }
}
