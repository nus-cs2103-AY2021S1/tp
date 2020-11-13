package seedu.bagel.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.bagel.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_DESC_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_LINK_BOB;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.bagel.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.bagel.testutil.EditFlashcardDescriptorBuilder;

public class EditFlashcardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFlashcardDescriptor descriptorWithSameValues = new EditFlashcardDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different title -> returns false
        EditFlashcardDescriptor editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withTitle(VALID_TITLE_BOB)
                .build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withDescription(VALID_DESC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different link -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withLink(VALID_LINK_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFlashcardDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
