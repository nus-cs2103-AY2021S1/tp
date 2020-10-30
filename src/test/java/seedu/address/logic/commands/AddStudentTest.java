package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommandTest.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.Trackr;
import seedu.address.model.module.Module;
import seedu.address.model.person.Student;
import seedu.address.testutil.StudentBuilder;

public class AddStudentTest {

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStudentCommand(null));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStudentAdded modelStub = new ModelStubAcceptingStudentAdded();
        Student validStudent = new StudentBuilder().build();

        CommandResult commandResult = new AddStudentCommand(validStudent).execute(modelStub);

        assertEquals(String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStudent), modelStub.studentsAdded);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student validStudent = new StudentBuilder().build();
        AddStudentCommand addStudentCommand = new AddStudentCommand(validStudent);
        ModelStub modelStub = new ModelStubWithStudent(validStudent);

        assertThrows(CommandException.class, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT, ()
                -> addStudentCommand.execute(modelStub)
        );
    }

    @Test
    public void equals() {
        Student alex = new StudentBuilder().withName("Alex").build();
        Student beng = new StudentBuilder().withName("Beng").build();
        AddStudentCommand addAlexCommand = new AddStudentCommand(alex);
        AddStudentCommand addBengCommand = new AddStudentCommand(beng);

        // same object -> returns true
        assertTrue(addAlexCommand.equals(addAlexCommand));

        // same values -> returns true
        AddStudentCommand addAlexCommandCopy = new AddStudentCommand(alex);
        assertTrue(addAlexCommand.equals(addAlexCommandCopy));

        // different types -> returns false
        assertFalse(addAlexCommand.equals(1));

        // null -> returns false
        assertFalse(addAlexCommand.equals(null));

        // different person -> returns false
        assertFalse(addAlexCommand.equals(addBengCommand));
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithStudent extends ModelStub {
        private final Student student;

        ModelStubWithStudent(Student student) {
            requireNonNull(student);
            this.student = student;
        }

        @Override
        public boolean isInStudentView() {
            return true;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return this.student.isSame(student);
        }
    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentAdded extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public boolean isInStudentView() {
            return true;
        }

        @Override
        public boolean hasStudent(Student student) {
            requireNonNull(student);
            return studentsAdded.stream().anyMatch(student::isSame);
        }

        @Override
        public void addStudent(Student student) {
            requireNonNull(student);
            studentsAdded.add(student);
        }

        @Override
        public ReadOnlyTrackr<Module> getModuleList() {
            return new Trackr();
        }
    }
}
