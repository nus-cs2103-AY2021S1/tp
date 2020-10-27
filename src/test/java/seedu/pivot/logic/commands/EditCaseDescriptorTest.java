package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.EDIT_CASE_DESCRIPTOR_AMY;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.EDIT_CASE_DESCRIPTOR_BOB;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.VALID_TITLE_BOB;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.commands.EditCommand.EditCaseDescriptor;
import seedu.pivot.testutil.EditCaseDescriptorBuilder;

public class EditCaseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCaseDescriptor descriptorWithSameValues = new EditCommand.EditCaseDescriptor(EDIT_CASE_DESCRIPTOR_AMY);
        assertTrue(EDIT_CASE_DESCRIPTOR_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_CASE_DESCRIPTOR_AMY.equals(EDIT_CASE_DESCRIPTOR_AMY));

        // null -> returns false
        assertFalse(EDIT_CASE_DESCRIPTOR_AMY.equals(null));

        // different types -> returns false
        assertFalse(EDIT_CASE_DESCRIPTOR_AMY.equals(5));

        // different values -> returns false
        assertFalse(EDIT_CASE_DESCRIPTOR_AMY.equals(EDIT_CASE_DESCRIPTOR_BOB));

        // different name -> returns false
        EditCaseDescriptor editedAmy = new EditCaseDescriptorBuilder(EDIT_CASE_DESCRIPTOR_AMY)
                .withTitle(VALID_TITLE_BOB).build();
        assertFalse(EDIT_CASE_DESCRIPTOR_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditCaseDescriptorBuilder(EDIT_CASE_DESCRIPTOR_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(EDIT_CASE_DESCRIPTOR_AMY.equals(editedAmy));
    }
}
