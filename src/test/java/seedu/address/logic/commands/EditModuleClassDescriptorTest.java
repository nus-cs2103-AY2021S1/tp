package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103T_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS2103T_TUTORIAL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditModuleClassDescriptorBuilder;

public class EditModuleClassDescriptorTest {

    @Test
    public void equals() {
        // same value -> return true
        EditModuleClassCommand.EditModuleClassDescriptor descriptorWithSameValues =
                new EditModuleClassCommand.EditModuleClassDescriptor(DESC_CS2100_TUTORIAL);
        assertTrue(DESC_CS2100_TUTORIAL.equals(descriptorWithSameValues));

        // same object -> return true
        assertTrue(DESC_CS2100_TUTORIAL.equals(DESC_CS2100_TUTORIAL));

        // null -> return false
        assertFalse(DESC_CS2100_TUTORIAL.equals(null));

        // different type -> return false
        assertFalse(DESC_CS2100_TUTORIAL.equals(5));

        // different value -> return false
        assertFalse(DESC_CS2100_TUTORIAL.equals(DESC_CS2103T_TUTORIAL));

        // different name -> return false
        EditModuleClassCommand.EditModuleClassDescriptor editedCs2100Tutorial =
                new EditModuleClassDescriptorBuilder(DESC_CS2100_TUTORIAL).withName(VALID_NAME_CS2103T_TUTORIAL)
                        .build();
        assertFalse(DESC_CS2100_TUTORIAL.equals(editedCs2100Tutorial));
    }
}
