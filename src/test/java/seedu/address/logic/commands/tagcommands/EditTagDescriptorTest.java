package seedu.address.logic.commands.tagcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HANDBALL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_NAME_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditTagDescriptorBuilder;

public class EditTagDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        TagEditCommand.EditTagDescriptor descriptorWithSameValues =
                new TagEditCommand.EditTagDescriptor(TAG_DESC_COLLEAGUE);
        assertTrue(TAG_DESC_COLLEAGUE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(TAG_DESC_COLLEAGUE.equals(TAG_DESC_COLLEAGUE));

        // null -> returns false
        assertFalse(TAG_DESC_COLLEAGUE.equals(null));

        // different types -> returns false
        assertFalse(TAG_DESC_COLLEAGUE.equals(5));

        // different values -> returns false
        assertFalse(TAG_DESC_COLLEAGUE.equals(TAG_DESC_HANDBALL));

        // different tag name -> returns false
        TagEditCommand.EditTagDescriptor editedTagColleague =
                new EditTagDescriptorBuilder(TAG_DESC_COLLEAGUE).withTagName(VALID_TAG_NAME_HUSBAND).build();
        assertFalse(TAG_DESC_COLLEAGUE.equals(editedTagColleague));
    }
}
