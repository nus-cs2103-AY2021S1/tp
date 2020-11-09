package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_MATH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ISBN_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LANGUAGE_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PUBLISHER_BOOK1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMES_BOOK1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditBookDescriptorBuilder;

public class EditBookDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditBookDescriptor descriptorWithSameValues = new EditCommand.EditBookDescriptor(DESC_BOOK1);
        assertTrue(DESC_BOOK1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BOOK1.equals(DESC_BOOK1));

        // null -> returns false
        assertFalse(DESC_BOOK1.equals(null));

        // different types -> returns false
        assertFalse(DESC_BOOK1.equals(5));

        // different values -> returns false
        assertFalse(DESC_BOOK1.equals(DESC_BOOK2));

        // different name -> returns false
        EditCommand.EditBookDescriptor editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withName(VALID_NAME_BOOK2)
                .build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different isbn -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withIsbn(VALID_ISBN_BOOK2).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withEmail(VALID_EMAIL_BOOK2).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different language -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withLanguage(VALID_LANGUAGE_BOOK2).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different categories -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withCategories(VALID_CATEGORY_MATH).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different times -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withCategories(VALID_TIMES_BOOK1).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different authors -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withCategories(VALID_AUTHOR_BOOK1).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));

        // different publishers -> returns false
        editedAmy = new EditBookDescriptorBuilder(DESC_BOOK1).withCategories(VALID_PUBLISHER_BOOK1).build();
        assertFalse(DESC_BOOK1.equals(editedAmy));
    }
}
