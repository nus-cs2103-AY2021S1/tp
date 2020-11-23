package seedu.address.logic.commands.deck.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JAPANESE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_SPANISH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.entry.EditCommand.EditEntryDescriptor;
import seedu.address.testutil.entry.EditEntryDescriptorBuilder;

public class EditEntryDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEntryDescriptor descriptorWithSameValues = new EditEntryDescriptor(DESC_JAPANESE);
        assertTrue(DESC_JAPANESE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_JAPANESE.equals(DESC_JAPANESE));

        // null -> returns false
        assertFalse(DESC_JAPANESE.equals(null));

        // different types -> returns false
        assertFalse(DESC_JAPANESE.equals(5));

        // different values -> returns false
        assertFalse(DESC_JAPANESE.equals(DESC_SPANISH));

        // different word -> returns false
        EditEntryDescriptor editedAmy = new EditEntryDescriptorBuilder(DESC_JAPANESE)
                .withWord(VALID_WORD_SPANISH).build();
        assertFalse(DESC_JAPANESE.equals(editedAmy));

        // different translation -> returns false
        editedAmy = new EditEntryDescriptorBuilder(DESC_JAPANESE).withTranslation(VALID_TRANSLATION_SPANISH).build();
        assertFalse(DESC_JAPANESE.equals(editedAmy));

    }
}
