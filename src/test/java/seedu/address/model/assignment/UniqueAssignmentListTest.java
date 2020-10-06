package seedu.address.model.assignment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_HW;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.HW;
import static seedu.address.testutil.TypicalAssignments.LAB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

public class UniqueAssignmentListTest {

    private final UniqueAssignmentList uniqueAssignmentList = new UniqueAssignmentList();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(uniqueAssignmentList.contains(HW));
    }

    @Test
    public void contains_assignmentInList_returnsTrue() {
        uniqueAssignmentList.add(HW);
        assertTrue(uniqueAssignmentList.contains(HW));
    }

    @Test
    public void contains_assignmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssignmentList.add(HW);
        Assignment editedHW = new AssignmentBuilder(HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        assertTrue(uniqueAssignmentList.contains(editedHW));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.add(null));
    }

    @Test
    public void add_duplicateAssignment_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(HW);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.add(HW));
    }

    @Test
    public void setAssignment_nullTargetAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(null, HW));
    }

    @Test
    public void setAssignment_nullEditedAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(HW, null));
    }

    @Test
    public void setAssignment_targetAssignmentNotInList_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.setAssignment(HW, HW));
    }

    @Test
    public void setAssignment_editedAssignmentIsSameAssignment_success() {
        uniqueAssignmentList.add(HW);
        uniqueAssignmentList.setAssignment(HW, HW);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(HW);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasSameIdentity_success() {
        uniqueAssignmentList.add(HW);
        Assignment editedHW = new AssignmentBuilder(HW).withModuleCode(VALID_MODULE_CODE_HW).build();
        uniqueAssignmentList.setAssignment(HW, editedHW);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(editedHW);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasDifferentIdentity_success() {
        uniqueAssignmentList.add(HW);
        uniqueAssignmentList.setAssignment(HW, LAB);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(LAB);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasNonUniqueIdentity_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(HW);
        uniqueAssignmentList.add(LAB);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.setAssignment(HW, LAB));
    }

    @Test
    public void remove_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.remove(null));
    }

    @Test
    public void remove_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.remove(HW));
    }

    @Test
    public void remove_existingAssignment_removesAssignment() {
        uniqueAssignmentList.add(HW);
        uniqueAssignmentList.remove(HW);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullUniqueAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList
                .setAssignments((UniqueAssignmentList) null));
    }

    @Test
    public void setAssignments_uniqueAssignmentList_replacesOwnListWithProvidedUniqueAssignmentList() {
        uniqueAssignmentList.add(HW);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(LAB);
        uniqueAssignmentList.setAssignments(expectedUniqueAssignmentList);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((List<Assignment>) null));
    }

    @Test
    public void setAssignments_list_replacesOwnListWithProvidedList() {
        uniqueAssignmentList.add(HW);
        List<Assignment> assignmentList = Collections.singletonList(LAB);
        uniqueAssignmentList.setAssignments(assignmentList);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(LAB);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_listWithDuplicateAssignments_throwsDuplicateAssignmentException() {
        List<Assignment> listWithDuplicateAssignments = Arrays.asList(HW, HW);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList
                .setAssignments(listWithDuplicateAssignments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAssignmentList.asUnmodifiableObservableList().remove(0));
    }
}
