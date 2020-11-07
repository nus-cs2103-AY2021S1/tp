package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalTutorialGroups.T05;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class EditParticipationCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Module moduleInView = CS2103T;
    private TutorialGroup tgInView = T05;

    @Test
    public void execute_moduleView_failure() {
        model.setCurrentViewToModule();
        EditParticipationCommand command = new EditParticipationCommand(INDEX_FIRST_PERSON, "10");
        String expectedMessage = EditParticipationCommand.MESSAGE_WRONG_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_tutorialGroupView_failure() {
        model.setCurrentViewToModule();
        model.setCurrentViewToTutorialGroup();
        EditParticipationCommand command = new EditParticipationCommand(INDEX_FIRST_PERSON, "10");
        String expectedMessage = EditParticipationCommand.MESSAGE_WRONG_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_studentView_success() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student student = model.getFilteredStudentList().get(0);
        EditParticipationCommand command = new EditParticipationCommand(INDEX_FIRST_PERSON, "0");
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        String expectedMessage = (String.format(
                EditParticipationCommand.MESSAGE_EDIT_PARTICIPATION_SUCCESS,
                student.getName().toString(),
                student.getAttendance().getParticipationScoreAsString(),
                student.getAttendance().getMaxParticipationScore())
        );
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditParticipationCommand(null, "0"));
    }

    @Test
    public void execute_nullScore_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditParticipationCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_invalidIndex_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Index invalidIndex = Index.fromOneBased(10);
        EditParticipationCommand command = new EditParticipationCommand(invalidIndex, "10");
        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_deductFromZeroScore_successMessage() throws CommandException {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student student = model.getFilteredStudentList().get(2);
        EditParticipationCommand command = new EditParticipationCommand(INDEX_THIRD_PERSON, "-10");
        String message = command.execute(model).getFeedbackToUser();
        String expectedMessage = (String.format(
                EditParticipationCommand.MESSAGE_EDIT_PARTICIPATION_SUCCESS,
                student.getName().toString(),
                student.getAttendance().getParticipationScoreAsString(),
                student.getAttendance().getMaxParticipationScore())
        );
        assertEquals(expectedMessage, message);
    }

    @Test
    public void execute_addFromHundredScore_successMessage() throws CommandException {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student student = model.getFilteredStudentList().get(4);
        EditParticipationCommand command = new EditParticipationCommand(Index.fromOneBased(5), "10");
        String message = command.execute(model).getFeedbackToUser();
        String expectedMessage = (String.format(
                EditParticipationCommand.MESSAGE_EDIT_PARTICIPATION_SUCCESS,
                student.getName().toString(),
                student.getAttendance().getParticipationScoreAsString(),
                student.getAttendance().getMaxParticipationScore())
        );
        assertEquals(expectedMessage, message);
    }

    @Test
    public void execute_editParticipationAddScore_participationScoreChanges() throws CommandException {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student student = model.getFilteredStudentList().get(2);
        String oldParticipation = student.getAttendance().getParticipationScoreAsString();
        EditParticipationCommand command = new EditParticipationCommand(INDEX_THIRD_PERSON, "10");
        command.execute(model);
        Student updatedStudent = model.getFilteredStudentList().get(2);
        String newParticipation = updatedStudent.getAttendance().getParticipationScoreAsString();
        int scoreChange = Integer.parseInt(newParticipation) - Integer.parseInt(oldParticipation);
        assertEquals(10, scoreChange);
    }

    @Test
    public void execute_editParticipationSubtractScore_participationScoreChanges() throws CommandException {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        Student student = model.getFilteredStudentList().get(4);
        String oldParticipation = student.getAttendance().getParticipationScoreAsString();
        EditParticipationCommand command = new EditParticipationCommand(Index.fromOneBased(5), "-10");
        command.execute(model);
        Student updatedStudent = model.getFilteredStudentList().get(4);
        String newParticipation = updatedStudent.getAttendance().getParticipationScoreAsString();
        int scoreChange = Integer.parseInt(newParticipation) - Integer.parseInt(oldParticipation);
        assertEquals(-10, scoreChange);
    }

    @Test
    public void execute_addScoreMoreThanHundred_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        EditParticipationCommand command = new EditParticipationCommand(INDEX_FIRST_PERSON, "110");
        String expectedMessage = (Attendance.MESSAGE_CONSTRAINTS);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_subtractScoreMoreThanNegativeHundred_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        EditParticipationCommand command = new EditParticipationCommand(INDEX_FIRST_PERSON, "-110");
        String expectedMessage = (Attendance.MESSAGE_CONSTRAINTS);
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        String scoreTen = "10";
        String scoreZero = "0";
        EditParticipationCommand editParticipationCommandIndexOneScoreTen =
                new EditParticipationCommand(INDEX_FIRST_PERSON, scoreTen);
        EditParticipationCommand editParticipationCommandIndexTwoScoreTen =
                new EditParticipationCommand(INDEX_SECOND_PERSON, scoreTen);
        EditParticipationCommand editParticipationCommandIndexTwoScoreZero =
                new EditParticipationCommand(INDEX_SECOND_PERSON, scoreZero);

        // same object -> returns true
        assertTrue(editParticipationCommandIndexOneScoreTen.equals(editParticipationCommandIndexOneScoreTen));

        // same values -> returns true
        EditParticipationCommand editParticipationCommandIndexOneScoreTenCopy =
                new EditParticipationCommand(INDEX_FIRST_PERSON, scoreTen);
        assertTrue(editParticipationCommandIndexOneScoreTen.equals(editParticipationCommandIndexOneScoreTenCopy));

        // different types -> returns false
        assertFalse(editParticipationCommandIndexOneScoreTen.equals(1));

        // null -> returns false
        assertFalse(editParticipationCommandIndexOneScoreTen.equals(null));

        // different index -> returns false
        assertFalse(editParticipationCommandIndexOneScoreTen.equals(editParticipationCommandIndexTwoScoreTen));

        // different scores -> returns false
        assertFalse(editParticipationCommandIndexTwoScoreTen.equals(editParticipationCommandIndexTwoScoreZero));
    }
}
