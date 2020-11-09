package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS1000;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS1001;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS1001;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditModuleCommand.EditModuleDescriptor descriptorWithSameValues =
                new EditModuleCommand.EditModuleDescriptor(DESC_CS1000);
        assertTrue(DESC_CS1000.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS1000.equals(DESC_CS1000));

        // null -> returns false
        assertFalse(DESC_CS1000.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS1000.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS1000.equals(DESC_CS1001));

        // different name -> returns false
        EditModuleCommand.EditModuleDescriptor editedCS1000 =
                new EditModuleDescriptorBuilder(DESC_CS1000)
                        .withModuleName(VALID_MODULE_NAME_CS1001).build();
        assertFalse(DESC_CS1000.equals(editedCS1000));
    }
}
