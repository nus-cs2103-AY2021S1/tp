package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.UPDESC_1;
import static seedu.address.logic.commands.CommandTestUtil.UPDESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.UpdateProfileDescriptorBuilder;


public class UpdateProfileDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        UpdateCommand.UpdateProfileDescriptor descriptorWithSameValues = new UpdateCommand
                .UpdateProfileDescriptor(UPDESC_1);
        assertTrue(UPDESC_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(UPDESC_1.equals(UPDESC_1));

        // null -> returns false
        assertFalse(UPDESC_1.equals(null));

        // different types -> returns false
        assertFalse(UPDESC_1.equals(5));

        // different values -> returns false
        assertFalse(UPDESC_1.equals(UPDESC_2));

        // different name -> returns false
        UpdateCommand.UpdateProfileDescriptor editedProfile = new UpdateProfileDescriptorBuilder(UPDESC_1)
                .withName(VALID_NAME_2).build();
        assertFalse(UPDESC_1.equals(editedProfile));

        // different id -> returns false
        editedProfile = new UpdateProfileDescriptorBuilder(UPDESC_1).withId(VALID_ID_2).build();
        assertFalse(UPDESC_1.equals(editedProfile));

        // different height -> returns false
        editedProfile = new UpdateProfileDescriptorBuilder(UPDESC_1).withHeight(VALID_HEIGHT_2).build();
        assertFalse(UPDESC_1.equals(editedProfile));

        // different weight -> returns false
        editedProfile = new UpdateProfileDescriptorBuilder(UPDESC_1).withWeight(VALID_WEIGHT_2).build();
        assertFalse(UPDESC_1.equals(editedProfile));
    }
}
