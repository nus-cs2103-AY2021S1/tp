package seedu.taskmaster.logic.commands;

import static seedu.taskmaster.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.taskmaster.testutil.TypicalStudents.getTypicalTaskmaster;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.taskmaster.model.Model;
import seedu.taskmaster.model.ModelManager;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.record.AttendanceType;
import seedu.taskmaster.model.student.Student;

public class MarkAllCommandTest {
    private Model model = new ModelManager(getTypicalTaskmaster(), new UserPrefs());

    @Test
    public void execute_markAllStudentsAsPresent_success() {
        List<Student> students = model.getFilteredStudentList();
        MarkAllCommand markAllCommand = new MarkAllCommand(AttendanceType.PRESENT);
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.markAllStudents(students, AttendanceType.PRESENT);
        String expectedMessage = String.format(MarkAllCommand.MESSAGE_MARK_ALL_SUCCESS, AttendanceType.PRESENT);
        assertCommandSuccess(markAllCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markAllStudentsAsAbsent_success() {
        List<Student> students = model.getFilteredStudentList();
        MarkAllCommand markAllCommand = new MarkAllCommand(AttendanceType.ABSENT);
        Model expectedModel = new ModelManager(getTypicalTaskmaster(), new UserPrefs());
        expectedModel.markAllStudents(students, AttendanceType.ABSENT);
        String expectedMessage = String.format(MarkAllCommand.MESSAGE_MARK_ALL_SUCCESS, AttendanceType.ABSENT);
        assertCommandSuccess(markAllCommand, model, expectedMessage, expectedModel);
    }
}
