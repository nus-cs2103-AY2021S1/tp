package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HW;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB;

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
        EditAssignmentDescriptor editedHw = new EditAssignmentDescriptorBuilder(DESC_HW)
                .withName(VALID_NAME_LAB).build();
        assertFalse(DESC_HW.equals(editedHw));

        // different deadline -> returns false
        editedHw = new EditAssignmentDescriptorBuilder(DESC_HW).withDeadline(VALID_DEADLINE_LAB).build();
        assertFalse(DESC_HW.equals(editedHw));

        // different module code -> returns false
        editedHw = new EditAssignmentDescriptorBuilder(DESC_HW).withModuleCode(VALID_MODULE_CODE_LAB).build();
        assertFalse(DESC_HW.equals(editedHw));
    }
}
