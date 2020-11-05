package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.AttendanceBelowSpecifiedScorePredicate;

public class AttendanceBelowCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackr(), new UserPrefs());

    @Test
    public void equals() {
        int firstUpperBound = 1;
        int secondUpperBound = 14;
        AttendanceBelowSpecifiedScorePredicate firstPredicate =
                new AttendanceBelowSpecifiedScorePredicate(firstUpperBound);
        AttendanceBelowSpecifiedScorePredicate secondPredicate =
                new AttendanceBelowSpecifiedScorePredicate(secondUpperBound);

        AttendanceBelowCommand firstAttendanceBelow = new AttendanceBelowCommand(firstPredicate, firstUpperBound);
        AttendanceBelowCommand secondAttendanceBelow = new AttendanceBelowCommand(secondPredicate, secondUpperBound);

        // same object -> returns true
        assertTrue(firstAttendanceBelow.equals(firstAttendanceBelow));

        // same values -> returns true
        AttendanceBelowCommand firstAttendanceBelowCommandCopy =
                new AttendanceBelowCommand(firstPredicate, firstUpperBound);
        assertTrue(firstAttendanceBelow.equals(firstAttendanceBelowCommandCopy));

        // different types -> returns false
        assertFalse(firstAttendanceBelow.equals(1));

        // null -> returns false
        assertFalse(firstAttendanceBelow.equals(null));

        // different command -> returns false
        assertFalse(firstAttendanceBelow.equals(secondAttendanceBelow));
    }

    @Test
    public void execute_zeroUpperBound_noModuleFound() {
        model.setViewToTutorialGroup(CS2103T);
        model.setViewToStudent(T05);
        expectedModel.setViewToTutorialGroup(CS2103T);
        expectedModel.setViewToStudent(T05);
        String expectedMessage = String.format(AttendanceBelowCommand.MESSAGE_ATTENDANCE_BELOW_SUCCESS, 0);
        AttendanceBelowSpecifiedScorePredicate predicate = preparePredicate(0);
        AttendanceBelowCommand command = new AttendanceBelowCommand(predicate, 0);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    //    todo
    //    @Test
    //    public void execute_validUpperBound_multipleModulesFound() {
    //        model.setViewToTutorialGroup(CS2103T);
    //        model.setViewToStudent(T05);
    //        expectedModel.setViewToTutorialGroup(CS2103T);
    //        expectedModel.setViewToStudent(T05);
    //        String expectedMessage = String.format(AttendanceBelowCommand.MESSAGE_ATTENDANCE_BELOW_SUCCESS, 4);
    //        AttendanceBelowSpecifiedScorePredicate predicate = preparePredicate(4);
    //        AttendanceBelowCommand command = new AttendanceBelowCommand(predicate, 4);
    //        expectedModel.updateFilteredStudentList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(CHARLIE, ELIZABETH), model.getFilteredStudentList());
    //    }
    //
    //    @Test
    //    public void execute_topUpperBound_multipleModulesFound() {
    //        model.setViewToTutorialGroup(CS2103T);
    //        model.setViewToStudent(T05);
    //        expectedModel.setViewToTutorialGroup(CS2103T);
    //        expectedModel.setViewToStudent(T05);
    //        String expectedMessage = String.format(AttendanceBelowCommand.MESSAGE_ATTENDANCE_BELOW_SUCCESS, 14);
    //        AttendanceBelowSpecifiedScorePredicate predicate = preparePredicate(14);
    //        AttendanceBelowCommand command = new AttendanceBelowCommand(predicate, 14);
    //        expectedModel.updateFilteredStudentList(predicate);
    //        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    //        assertEquals(Arrays.asList(ALEX, BENG, CHARLIE, DAVID, ELIZABETH, FIONA), model.getFilteredStudentList());
    //    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private AttendanceBelowSpecifiedScorePredicate preparePredicate(int upperBound) {
        return new AttendanceBelowSpecifiedScorePredicate(upperBound);
    }
}
