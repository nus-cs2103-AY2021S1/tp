package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.CS2103T;
import static seedu.address.testutil.TypicalModulesPopulatedWithTutorialGroups.getTypicalTrackr;
import static seedu.address.testutil.TypicalTutorialGroups.V04;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.Trackr;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TypicalStudents;

public class ViewAttendanceCommandTest {
    private Model model = new ModelManager(getTypicalTrackr(), new UserPrefs());
    private Module moduleInView = CS2103T;
    private TutorialGroup tgInView = V04;

    @Test
    public void execute_tutorialGroupView_failure() {
        model.setCurrentViewToTutorialGroup();
        ViewAttendanceCommand command = new ViewAttendanceCommand(INDEX_FIRST_PERSON);
        String expectedMessage = ViewAttendanceCommand.MESSAGE_WRONG_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_moduleView_failure() {
        model.setCurrentViewToModule();
        ViewAttendanceCommand command = new ViewAttendanceCommand(INDEX_FIRST_PERSON);
        String expectedMessage = ViewAttendanceCommand.MESSAGE_WRONG_VIEW;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_studentView_success() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        model.addStudent(TypicalStudents.ALEX);
        Student student = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewAttendanceCommand command = new ViewAttendanceCommand(INDEX_FIRST_PERSON);
        ModelManager expectedModel = new ModelManager(new Trackr(model.getModuleList()), new UserPrefs());
        expectedModel.setViewToTutorialGroup(moduleInView);
        expectedModel.setViewToStudent(tgInView);
        String expectedMessage = String.format(ViewAttendanceCommand.MESSAGE_SUCCESS,
                student.getName().toString(),
                student.getAttendance().listOutAttendedWeeks());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentInvalidIndex_failure() {
        model.setViewToTutorialGroup(moduleInView);
        model.setViewToStudent(tgInView);
        ViewAttendanceCommand command = new ViewAttendanceCommand(INDEX_THIRD_PERSON);
        String expectedMessage = Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void equals() {
        ViewAttendanceCommand viewAttendanceCommandFirst = new ViewAttendanceCommand(INDEX_FIRST_PERSON);
        ViewAttendanceCommand viewAttendanceCommandSecond = new ViewAttendanceCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewAttendanceCommandFirst.equals(viewAttendanceCommandFirst));

        // same values -> returns true
        ViewAttendanceCommand viewAttendanceCommandFirstCopy = new ViewAttendanceCommand(INDEX_FIRST_PERSON);
        assertTrue(viewAttendanceCommandFirst.equals(viewAttendanceCommandFirstCopy));

        // different types -> returns false
        assertFalse(viewAttendanceCommandFirst.equals(1));

        // null -> returns false
        assertFalse(viewAttendanceCommandFirst.equals(null));

        // different person -> returns false
        assertFalse(viewAttendanceCommandFirst.equals(viewAttendanceCommandSecond));
    }
}
