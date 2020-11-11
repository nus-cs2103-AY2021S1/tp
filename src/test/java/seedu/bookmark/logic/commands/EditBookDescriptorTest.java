package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_1984;
import static seedu.bookmark.logic.commands.CommandTestUtil.DESC_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_BOOKMARK_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_NAME_JANE_EYRE;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TAG_GOOD;
import static seedu.bookmark.logic.commands.CommandTestUtil.VALID_TOTAL_PAGES_JANE_EYRE;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.EditCommand.EditBookDescriptor;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

public class EditBookDescriptorTest {

    @Test
    public void equals() {
        // The book 1984 is a different book from JANE_EYRE, and hence the values for each property are different.
        // same values -> returns true
        EditBookDescriptor descriptorWithSameValues = new EditBookDescriptor(DESC_1984);
        assertTrue(DESC_1984.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_1984.equals(DESC_1984));

        // null -> returns false
        assertFalse(DESC_1984.equals(null));

        // different types -> returns false
        assertFalse(DESC_1984.equals(5));

        // different values -> returns false
        assertFalse(DESC_1984.equals(DESC_JANE_EYRE));

        // different name -> returns false
        EditCommand.EditBookDescriptor editedAmy = new EditBookDescriptorBuilder(DESC_1984)
                .withName(VALID_NAME_JANE_EYRE).build();
        assertFalse(DESC_1984.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_1984).withTags(VALID_TAG_GOOD).build();
        assertFalse(DESC_1984.equals(editedAmy));

        // different totalPages -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_1984).withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE).build();
        assertFalse(DESC_1984.equals(editedAmy));

        // different bookmark -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_1984).withBookmark(VALID_BOOKMARK_JANE_EYRE).build();
        assertFalse(DESC_1984.equals(editedAmy));
    }
}
