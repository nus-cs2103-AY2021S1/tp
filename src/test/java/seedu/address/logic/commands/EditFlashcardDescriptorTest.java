package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BUBBLE_SORT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.address.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_BUBBLE_SORT);
        assertTrue(DESC_BUBBLE_SORT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BUBBLE_SORT.equals(DESC_BUBBLE_SORT));

        // null -> returns false
        assertFalse(DESC_BUBBLE_SORT.equals(null));

        // different types -> returns false
        assertFalse(DESC_BUBBLE_SORT.equals(5));

        // different values -> returns false
        assertFalse(DESC_BUBBLE_SORT.equals(DESC_HEAPING));

        // different name -> returns false
        EditFlashcardDescriptor editedAmy = new EditFlashcardDescriptorBuilder(DESC_BUBBLE_SORT)
            .withName(VALID_NAME_HEAPING).build();
        assertFalse(DESC_BUBBLE_SORT.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_BUBBLE_SORT)
            .withDefinition(VALID_DEFINITION_HEAPING).build();
        assertFalse(DESC_BUBBLE_SORT.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_BUBBLE_SORT).withTags(VALID_TAG_DIFFICULT).build();
        assertFalse(DESC_BUBBLE_SORT.equals(editedAmy));
    }
}
