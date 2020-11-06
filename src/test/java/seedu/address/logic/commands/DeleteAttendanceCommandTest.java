package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
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
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteAttendanceCommand(null, new int[]{2}));
    }

    @Test
    public void constructor_nullWeeksToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteAttendanceCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexValidWeek_success() {
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
    public void execute_validIndexInvalidNegativeWeek_failure() {
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
    public void execute_validIndexInvalidWeek_failure() {
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
    public void execute_invalidIndexValidWeek_failure() {
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

    @Test
    public void equals() {
        int[] weekOne = new int[] {1};
        int[] weekTwo = new int[] {2};
        DeleteAttendanceCommand deleteAttendanceCommandIndexOneWeekOne =
                new DeleteAttendanceCommand(INDEX_FIRST_PERSON, weekOne);
        DeleteAttendanceCommand deleteAttendanceCommandIndexOneWeekTwo =
                new DeleteAttendanceCommand(INDEX_FIRST_PERSON, weekTwo);
        DeleteAttendanceCommand deleteAttendanceCommandIndexTwoWeekOne =
                new DeleteAttendanceCommand(INDEX_SECOND_PERSON, weekOne);

        // same object -> returns true
        assertTrue(deleteAttendanceCommandIndexOneWeekOne.equals(deleteAttendanceCommandIndexOneWeekOne));

        // same values -> returns true
        DeleteAttendanceCommand deleteAttendanceCommandIndexOneWeekOneCopy =
                new DeleteAttendanceCommand(INDEX_FIRST_PERSON, weekOne);
        assertTrue(deleteAttendanceCommandIndexOneWeekOne.equals(deleteAttendanceCommandIndexOneWeekOneCopy));

        // different types -> returns false
        assertFalse(deleteAttendanceCommandIndexOneWeekOne.equals(1));

        // null -> returns false
        assertFalse(deleteAttendanceCommandIndexOneWeekOne.equals(null));

        // different week -> returns false
        assertFalse(deleteAttendanceCommandIndexOneWeekOne.equals(deleteAttendanceCommandIndexOneWeekTwo));

        // different index -> returns false
        assertFalse(deleteAttendanceCommandIndexOneWeekOne.equals(deleteAttendanceCommandIndexTwoWeekOne));
    }
}
