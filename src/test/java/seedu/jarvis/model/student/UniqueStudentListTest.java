package seedu.jarvis.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;
import static seedu.jarvis.testutil.TypicalStudents.ALICE;
import static seedu.jarvis.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.jarvis.model.student.exceptions.DuplicateStudentException;
import seedu.jarvis.model.student.exceptions.StudentNotFoundException;
import seedu.jarvis.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentsList uniqueStudentsList = new UniqueStudentsList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueStudentsList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueStudentsList.add(ALICE);
        assertTrue(uniqueStudentsList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentsList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(uniqueStudentsList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueStudentsList.add(ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentsList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.setPerson(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.setPerson(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentsList.setPerson(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueStudentsList.add(ALICE);
        uniqueStudentsList.setPerson(ALICE, ALICE);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        expectedUniqueStudentsList.add(ALICE);
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueStudentsList.add(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).build();
        uniqueStudentsList.setPerson(ALICE, editedAlice);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        expectedUniqueStudentsList.add(editedAlice);
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueStudentsList.add(ALICE);
        uniqueStudentsList.setPerson(ALICE, BOB);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        expectedUniqueStudentsList.add(BOB);
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueStudentsList.add(ALICE);
        uniqueStudentsList.add(BOB);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentsList.setPerson(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(StudentNotFoundException.class, () -> uniqueStudentsList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueStudentsList.add(ALICE);
        uniqueStudentsList.remove(ALICE);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.setPersons((UniqueStudentsList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueStudentsList.add(ALICE);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        expectedUniqueStudentsList.add(BOB);
        uniqueStudentsList.setPersons(expectedUniqueStudentsList);
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentsList.setPersons((List<Student>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueStudentsList.add(ALICE);
        List<Student> studentList = Collections.singletonList(BOB);
        uniqueStudentsList.setPersons(studentList);
        UniqueStudentsList expectedUniqueStudentsList = new UniqueStudentsList();
        expectedUniqueStudentsList.add(BOB);
        assertEquals(expectedUniqueStudentsList, uniqueStudentsList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStudentException.class, () -> uniqueStudentsList.setPersons(listWithDuplicateStudents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStudentsList.asUnmodifiableObservableList().remove(0));
    }
}
