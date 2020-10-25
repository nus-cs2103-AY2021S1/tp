package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.DESC_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.DESC_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_NAME_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.EditCommand.EditCaseDescriptor;
import seedu.pivot.testutil.EditCaseDescriptorBuilder;

public class EditCaseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCaseDescriptor descriptorWithSameValues = new EditCommand.EditCaseDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditCaseDescriptor editedAmy = new EditCaseDescriptorBuilder(DESC_AMY).withTitle(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditCaseDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
