package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;
import seedu.address.testutil.StudentBuilder;

public class ReeveTest {

    private final Reeve reeve = new Reeve();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), reeve.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reeve.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        Reeve newData = getTypicalAddressBook();
        reeve.resetData(newData);
        assertEquals(newData, reeve);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Student editedAlice = new StudentBuilder(AMY).build();
        List<Student> newStudents = Arrays.asList(AMY, editedAlice);
        ReeveStub newData = new ReeveStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> reeve.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> reeve.hasStudent(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(reeve.hasStudent(AMY));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        reeve.addStudent(AMY);
        assertTrue(reeve.hasStudent(AMY));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        reeve.addStudent(AMY);
        Student editedAlice = new StudentBuilder(AMY).build();
        assertTrue(reeve.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> reeve.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose student list can violate interface constraints.
     */
    private static class ReeveStub implements ReadOnlyReeve {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        ReeveStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }
    }

}
