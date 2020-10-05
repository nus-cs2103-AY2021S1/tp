package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAssignments.CS1231S_HW;
import static seedu.address.testutil.TypicalAssignments.LAB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AssignmentBuilder;

public class AssignmentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Assignment assignment = new AssignmentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> assignment.getTags().remove(0));
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(CS1231S_HW.isSameAssignment(CS1231S_HW));

        // null -> returns false
        assertFalse(CS1231S_HW.isSameAssignment(null));

        // different phone and email -> returns false
        Assignment editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withPhone(VALID_PHONE_LAB)
                .withEmail(VALID_EMAIL_LAB).build();
        assertFalse(CS1231S_HW.isSameAssignment(editedCs1231sHw));

        // different name -> returns false
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withName(VALID_NAME_LAB).build();
        assertFalse(CS1231S_HW.isSameAssignment(editedCs1231sHw));

        // same name, same phone, different attributes -> returns true
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withEmail(VALID_EMAIL_LAB).withAddress(VALID_ADDRESS_LAB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CS1231S_HW.isSameAssignment(editedCs1231sHw));

        // same name, same email, different attributes -> returns true
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withPhone(VALID_PHONE_LAB).withAddress(VALID_ADDRESS_LAB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CS1231S_HW.isSameAssignment(editedCs1231sHw));

        // same name, same phone, same email, different attributes -> returns true
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW)
                .withAddress(VALID_ADDRESS_LAB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(CS1231S_HW.isSameAssignment(editedCs1231sHw));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment cs1231sHwCopy = new AssignmentBuilder(CS1231S_HW).build();
        assertTrue(CS1231S_HW.equals(cs1231sHwCopy));

        // same object -> returns true
        assertTrue(CS1231S_HW.equals(CS1231S_HW));

        // null -> returns false
        assertFalse(CS1231S_HW.equals(null));

        // different type -> returns false
        assertFalse(CS1231S_HW.equals(5));

        // different assignment -> returns false
        assertFalse(CS1231S_HW.equals(LAB));

        // different name -> returns false
        Assignment editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withName(VALID_NAME_LAB).build();
        assertFalse(CS1231S_HW.equals(editedCs1231sHw));

        // different phone -> returns false
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withPhone(VALID_PHONE_LAB).build();
        assertFalse(CS1231S_HW.equals(editedCs1231sHw));

        // different email -> returns false
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withEmail(VALID_EMAIL_LAB).build();
        assertFalse(CS1231S_HW.equals(editedCs1231sHw));

        // different address -> returns false
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withAddress(VALID_ADDRESS_LAB).build();
        assertFalse(CS1231S_HW.equals(editedCs1231sHw));

        // different tags -> returns false
        editedCs1231sHw = new AssignmentBuilder(CS1231S_HW).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(CS1231S_HW.equals(editedCs1231sHw));
    }
}
