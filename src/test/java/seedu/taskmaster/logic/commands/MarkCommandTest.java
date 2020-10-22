package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.commons.core.Messages;
import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.session.AttendanceType;
import seedu.taskmaster.model.student.Student;


public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_STUDENT.getZeroBased());
        MarkCommand markCommand = new MarkCommand(INDEX_FIRST_STUDENT, AttendanceType.PRESENT);
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.markStudent(firstStudent, AttendanceType.PRESENT);
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_STUDENT_SUCCESS,
                firstStudent, AttendanceType.PRESENT);
        assertCommandSuccess(markCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_indexTooLarge_failure() {
        int numOfStudents = model.getFilteredStudentList().size();
        Index indexTooBig = Index.fromZeroBased(numOfStudents);
        MarkCommand markCommand = new MarkCommand(indexTooBig, AttendanceType.PRESENT);
        assertCommandFailure(markCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

}
