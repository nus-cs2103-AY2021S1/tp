package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESC_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.DESC_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;

public class EditFoodDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditFoodDescriptor descriptorWithSameValues = new EditCommand.EditFoodDescriptor(DESC_APPLE_PIE);
        assertTrue(DESC_APPLE_PIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE_PIE.equals(DESC_APPLE_PIE));

        // null -> returns false
        assertFalse(DESC_APPLE_PIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE_PIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE_PIE.equals(DESC_BREAD));

        // different description -> returns false
        EditCommand.EditFoodDescriptor editedApplePie = new EditFoodDescriptorBuilder(DESC_APPLE_PIE)
                .withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different priority -> returns false
        editedApplePie = new EditFoodDescriptorBuilder(DESC_APPLE_PIE).withPriority(VALID_PRIORITY_BREAD).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different expiry date -> returns false
        editedApplePie = new EditFoodDescriptorBuilder(DESC_APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_BREAD).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));

        // different tags -> returns false
        editedApplePie = new EditFoodDescriptorBuilder(DESC_APPLE_PIE).withTags(VALID_TAG_WHOLEMEAL).build();
        assertFalse(DESC_APPLE_PIE.equals(editedApplePie));
    }
}
