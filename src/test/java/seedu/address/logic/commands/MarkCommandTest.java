package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.getTypicalTaskmaster;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.AttendanceType;
import seedu.address.model.student.Student;


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
