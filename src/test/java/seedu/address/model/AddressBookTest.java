package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.ALICE;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.exceptions.DuplicateAssignmentException;
import seedu.address.testutil.AssignmentBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getAssignmentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateAssignments_throwsDuplicateAssignmentException() {
        // Two assignments with the same identity fields
        Assignment editedAlice = new AssignmentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Assignment> newAssignments = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newAssignments);

        assertThrows(DuplicateAssignmentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasAssignment_nullAssignment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasAssignment(null));
    }

    @Test
    public void hasAssignment_assignmentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasAssignment(ALICE));
    }

    @Test
    public void hasAssignment_assignmentInAddressBook_returnsTrue() {
        addressBook.addAssignment(ALICE);
        assertTrue(addressBook.hasAssignment(ALICE));
    }

    @Test
    public void hasAssignment_assignmentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addAssignment(ALICE);
        Assignment editedAlice = new AssignmentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasAssignment(editedAlice));
    }

    @Test
    public void getAssignmentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getAssignmentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose assignments list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Assignment> assignments = FXCollections.observableArrayList();

        AddressBookStub(Collection<Assignment> assignments) {
            this.assignments.setAll(assignments);
        }

        @Override
        public ObservableList<Assignment> getAssignmentList() {
            return assignments;
        }
    }

}
