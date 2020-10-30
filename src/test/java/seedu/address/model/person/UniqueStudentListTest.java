package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALEX;
import static seedu.address.testutil.TypicalStudents.BENG;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.exceptions.DuplicateShowableException;
import seedu.address.model.exceptions.ShowableNotFoundException;
import seedu.address.testutil.StudentBuilder;

public class UniqueStudentListTest {

    private final UniqueStudentList uniqueStudentList = new UniqueStudentList();

    @Test
    public void contains_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.contains(null));
    }

    @Test
    public void contains_studentNotInList_returnsFalse() {
        assertFalse(uniqueStudentList.contains(ALEX));
    }

    @Test
    public void contains_studentInList_returnsTrue() {
        uniqueStudentList.addStudent(ALEX);
        assertTrue(uniqueStudentList.contains(ALEX));
    }

    @Test
    public void contains_studentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStudentList.addStudent(ALEX);
        Student editedAlex = new StudentBuilder(ALEX).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStudentList.contains(editedAlex));
    }

    @Test
    public void add_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.addStudent(null));
    }

    @Test
    public void add_duplicateStudent_throwsDuplicateShowableException() {
        uniqueStudentList.addStudent(ALEX);
        assertThrows(DuplicateShowableException.class, () -> uniqueStudentList.addStudent(ALEX));
    }

    @Test
    public void setStudent_nullTargetStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(null, ALEX));
    }

    @Test
    public void setStudent_nullEditedStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudent(ALEX, null));
    }

    @Test
    public void setStudent_targetStudentNotInList_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueStudentList.setStudent(ALEX, ALEX));
    }

    @Test
    public void setStudent_editedStudentIsSameStudent_success() {
        uniqueStudentList.addStudent(ALEX);
        uniqueStudentList.setStudent(ALEX, ALEX);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.addStudent(ALEX);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasSameIdentity_success() {
        uniqueStudentList.addStudent(ALEX);
        Student editedAlex = new StudentBuilder(ALEX).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStudentList.setStudent(ALEX, editedAlex);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.addStudent(editedAlex);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasDifferentIdentity_success() {
        uniqueStudentList.addStudent(ALEX);
        uniqueStudentList.setStudent(ALEX, BENG);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.addStudent(BENG);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudent_editedStudentHasNonUniqueIdentity_throwsDuplicateShowableException() {
        uniqueStudentList.addStudent(ALEX);
        uniqueStudentList.addStudent(BENG);
        assertThrows(DuplicateShowableException.class, () -> uniqueStudentList.setStudent(ALEX, BENG));
    }

    @Test
    public void remove_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.removeStudent(null));
    }

    @Test
    public void remove_studentDoesNotExist_throwsShowableNotFoundException() {
        assertThrows(ShowableNotFoundException.class, () -> uniqueStudentList.removeStudent(ALEX));
    }

    @Test
    public void remove_existingStudent_removesStudent() {
        uniqueStudentList.addStudent(ALEX);
        uniqueStudentList.removeStudent(ALEX);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudentList_nullUniqueStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudentList((UniqueStudentList) null));
    }

    @Test
    public void setStudentList_uniqueStudentList_replacesOwnListWithProvidedUniqueStudentList() {
        uniqueStudentList.addStudent(ALEX);

        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.addStudent(BENG);
        uniqueStudentList.setStudentList(expectedUniqueStudentList);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudentList_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStudentList.setStudentList((List<Student>) null));
    }

    @Test
    public void setStudentList_list_replacesOwnListWithProvidedList() {
        uniqueStudentList.addStudent(ALEX);
        List<Student> studentList = Collections.singletonList(BENG);
        uniqueStudentList.setStudentList(studentList);
        UniqueStudentList expectedUniqueStudentList = new UniqueStudentList();
        expectedUniqueStudentList.addStudent(BENG);
        assertEquals(expectedUniqueStudentList, uniqueStudentList);
    }

    @Test
    public void setStudentList_listWithDuplicateStudents_throwsDuplicateShowableException() {
        List<Student> listWithDuplicateStudents = Arrays.asList(ALEX, ALEX);
        assertThrows(DuplicateShowableException.class, ()
                -> uniqueStudentList.setStudentList(listWithDuplicateStudents)
        );
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueStudentList.asUnmodifiableObservableList().remove(0)
        );
    }
}
