package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditAssignmentDescriptor;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

public class EditAssignmentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditAssignmentDescriptor descriptorWithSameValues = new EditAssignmentDescriptor(DESC_HW);
        assertTrue(DESC_HW.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_HW.equals(DESC_HW));

        // null -> returns false
        assertFalse(DESC_HW.equals(null));

        // different types -> returns false
        assertFalse(DESC_HW.equals(5));

        // different values -> returns false
        assertFalse(DESC_HW.equals(DESC_LAB));

        // different name -> returns false
        EditAssignmentDescriptor editedAmy = new EditAssignmentDescriptorBuilder(DESC_HW)
                .withName(VALID_NAME_LAB).build();
        assertFalse(DESC_HW.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditAssignmentDescriptorBuilder(DESC_HW).withPhone(VALID_PHONE_LAB).build();
        assertFalse(DESC_HW.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditAssignmentDescriptorBuilder(DESC_HW).withEmail(VALID_EMAIL_LAB).build();
        assertFalse(DESC_HW.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditAssignmentDescriptorBuilder(DESC_HW).withAddress(VALID_ADDRESS_LAB).build();
        assertFalse(DESC_HW.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditAssignmentDescriptorBuilder(DESC_HW).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_HW.equals(editedAmy));
    }
}
