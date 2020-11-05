package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalStudents.ATTENDANCE_TEST;
import static seedu.address.testutil.TypicalStudents.ATTENDANCE_TEST_WEEK_2;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.AddModuleCommandTest.ModelStub;
import seedu.address.model.student.Student;

public class AddAttendanceCommandTest {
    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAttendanceCommand(null, new int[]{2}));
    }

    @Test
    public void constructor_nullWeeksToAdd_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddAttendanceCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_addAttendanceUpdatesAttendance_addSuccessful() throws Exception {
        Student zeroAttendance = ATTENDANCE_TEST;
        Student validOutcome = ATTENDANCE_TEST_WEEK_2;
        int[] validWeek = new int[]{2};
        ModelStub modelStub = new ModelStubWithStudentZeroAttendance(zeroAttendance);
        CommandResult commandResult = new AddAttendanceCommand(INDEX_FIRST_PERSON, validWeek).execute(modelStub);

        assertEquals(String.format(AddAttendanceCommand.MESSAGE_ADD_ATTENDANCE_SUCCESS,
                zeroAttendance.getName().toString(),
                zeroAttendance.getAttendance().listOutAttendedWeeks()),
                commandResult.getFeedbackToUser());
        assertEquals(modelStub.getFilteredStudentList().get(1), validOutcome);
    }

    /**
     * A Model stub that contains a single student with zero attendance.
     */
    private class ModelStubWithStudentZeroAttendance extends ModelStub {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final Student student;

        ModelStubWithStudentZeroAttendance(Student student) {
            requireNonNull(student);
            this.student = student;
        }


        @Override
        public ObservableList<Student> getFilteredStudentList() {
            students.add(this.student);
            return students;
        }

        @Override
        public boolean isInStudentView() {
            return true;
        }

        @Override
        public void setStudent(Student student, Student edited) {
            requireNonNull(student);
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
        }
    }
}
