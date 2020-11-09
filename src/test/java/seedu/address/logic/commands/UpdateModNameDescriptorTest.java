package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateCommand.UpdateModNameDescriptor;
import seedu.address.testutil.UpdateModNameDescriptorBuilder;

public class UpdateModNameDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UpdateCommand.UpdateModNameDescriptor descriptorWithSameValues =
                new UpdateCommand.UpdateModNameDescriptor(DESC_A);
        assertTrue(DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_A.equals(DESC_A));

        // null -> returns false
        assertFalse(DESC_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_A.equals(DESC_B));

        // different name -> returns false
        UpdateModNameDescriptor updatedMod =
                new UpdateModNameDescriptorBuilder(DESC_A).withName(VALID_MOD_NAME_B).build();
        assertFalse(DESC_A.equals(updatedMod));

        // different address -> returns false
        updatedMod = new UpdateModNameDescriptorBuilder(DESC_A).withGrade(VALID_GRADE_B).build();
        assertFalse(DESC_A.equals(updatedMod));
    }
}
