package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.core.Messages;
import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.session.SessionName;
import seedu.taskmaster.model.student.Student;


public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());

    @Test
    public void execute_markPresentWithValidIndex_success() {
        model.changeSession(new SessionName("Typical session"));
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.changeSession(new SessionName("Typical session"));
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, AttendanceType.PRESENT);
        expectedModel.markStudent(firstStudent, AttendanceType.PRESENT);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                firstStudent, AttendanceType.PRESENT);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markAbsentWithValidIndex_success() {
        Student secondStudent = model.getFilteredStudentList().get(INDEX_SECOND_STUDENT.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_SECOND_STUDENT, AttendanceType.ABSENT);
        model.changeSession(new SessionName("Typical session"));
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.changeSession(new SessionName("Typical session"));
        expectedModel.markStudent(secondStudent, AttendanceType.ABSENT);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                secondStudent, AttendanceType.ABSENT);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexTooLarge_failure() {
        model.changeSession(new SessionName("Typical session"));
        int numOfStudents = model.getFilteredStudentList().size();
        Index indexTooBig = Index.fromZeroBased(numOfStudents);
        MarkCommand markCommand = new MarkCommand(indexTooBig, AttendanceType.PRESENT);
        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_emptySessionList_exceptionThrown() {
        model.setSessions(new ArrayList<>());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, AttendanceType.PRESENT);
        String expectedMessage = "There are no sessions yet!";
        assertCommandFailure(markCommand, model, expectedMessage);
    }

    @Test
    public void execute_nullCurrentSession_exceptionThrown() {
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, AttendanceType.PRESENT);
        String expectedMessage = "Please select a session first!";
        assertCommandFailure(markCommand, model, expectedMessage);
    }

}
