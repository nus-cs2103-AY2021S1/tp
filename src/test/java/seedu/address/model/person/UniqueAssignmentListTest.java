package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ALICE;
import static seedu.address.testutil.TypicalAssignments.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateAssignmentException;
import seedu.address.model.person.exceptions.AssignmentNotFoundException;
import seedu.address.testutil.AssignmentBuilder;

public class UniqueAssignmentListTest {

    private final UniqueAssignmentList uniqueAssignmentList = new UniqueAssignmentList();

    @Test
    public void contains_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.contains(null));
    }

    @Test
    public void contains_assignmentNotInList_returnsFalse() {
        assertFalse(uniqueAssignmentList.contains(ALICE));
    }

    @Test
    public void contains_assignmentInList_returnsTrue() {
        uniqueAssignmentList.add(ALICE);
        assertTrue(uniqueAssignmentList.contains(ALICE));
    }

    @Test
    public void contains_assignmentWithSameIdentityFieldsInList_returnsTrue() {
        uniqueAssignmentList.add(ALICE);
        Assignment editedAlice = new AssignmentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueAssignmentList.contains(editedAlice));
    }

    @Test
    public void add_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.add(null));
    }

    @Test
    public void add_duplicateAssignment_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ALICE);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.add(ALICE));
    }

    @Test
    public void setAssignment_nullTargetAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(null, ALICE));
    }

    @Test
    public void setAssignment_nullEditedAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignment(ALICE, null));
    }

    @Test
    public void setAssignment_targetAssignmentNotInList_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.setAssignment(ALICE, ALICE));
    }

    @Test
    public void setAssignment_editedAssignmentIsSameAssignment_success() {
        uniqueAssignmentList.add(ALICE);
        uniqueAssignmentList.setAssignment(ALICE, ALICE);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(ALICE);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasSameIdentity_success() {
        uniqueAssignmentList.add(ALICE);
        Assignment editedAlice = new AssignmentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueAssignmentList.setAssignment(ALICE, editedAlice);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(editedAlice);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasDifferentIdentity_success() {
        uniqueAssignmentList.add(ALICE);
        uniqueAssignmentList.setAssignment(ALICE, BOB);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BOB);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignment_editedAssignmentHasNonUniqueIdentity_throwsDuplicateAssignmentException() {
        uniqueAssignmentList.add(ALICE);
        uniqueAssignmentList.add(BOB);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.setAssignment(ALICE, BOB));
    }

    @Test
    public void remove_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.remove(null));
    }

    @Test
    public void remove_assignmentDoesNotExist_throwsAssignmentNotFoundException() {
        assertThrows(AssignmentNotFoundException.class, () -> uniqueAssignmentList.remove(ALICE));
    }

    @Test
    public void remove_existingAssignment_removesAssignment() {
        uniqueAssignmentList.add(ALICE);
        uniqueAssignmentList.remove(ALICE);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullUniqueAssignmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((UniqueAssignmentList) null));
    }

    @Test
    public void setAssignments_uniqueAssignmentList_replacesOwnListWithProvidedUniqueAssignmentList() {
        uniqueAssignmentList.add(ALICE);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BOB);
        uniqueAssignmentList.setAssignments(expectedUniqueAssignmentList);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAssignmentList.setAssignments((List<Assignment>) null));
    }

    @Test
    public void setAssignments_list_replacesOwnListWithProvidedList() {
        uniqueAssignmentList.add(ALICE);
        List<Assignment> assignmentList = Collections.singletonList(BOB);
        uniqueAssignmentList.setAssignments(assignmentList);
        UniqueAssignmentList expectedUniqueAssignmentList = new UniqueAssignmentList();
        expectedUniqueAssignmentList.add(BOB);
        assertEquals(expectedUniqueAssignmentList, uniqueAssignmentList);
    }

    @Test
    public void setAssignments_listWithDuplicateAssignments_throwsDuplicateAssignmentException() {
        List<Assignment> listWithDuplicateAssignments = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateAssignmentException.class, () -> uniqueAssignmentList.setAssignments(listWithDuplicateAssignments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueAssignmentList.asUnmodifiableObservableList().remove(0));
    }
}
