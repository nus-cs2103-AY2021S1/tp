package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.BUBBLE_SORT;
import static seedu.address.testutil.TypicalFlashcards.HEAPING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FlashcardBuilder;

public class FlashcardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Flashcard flashcard = new FlashcardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> flashcard.getTags().remove(0));
    }

    @Test
    public void isSameFlashcard() {
        // same object -> returns true
        assertTrue(BUBBLE_SORT.isSameFlashcard(BUBBLE_SORT));

        // null -> returns false
        assertFalse(BUBBLE_SORT.isSameFlashcard(null));

        // different address -> returns true
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withDefinition(VALID_DEFINITION_HEAPING).build();
        assertTrue(editedBubbleSort.isSameFlashcard(BUBBLE_SORT));

        // different name -> returns false
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withTitle(VALID_NAME_HEAPING).build();
        assertFalse(BUBBLE_SORT.isSameFlashcard(editedBubbleSort));

        // same name, different attributes -> returns true
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withDefinition(VALID_DEFINITION_HEAPING)
                .withTags(VALID_TAG_DIFFICULT).build();
        assertTrue(BUBBLE_SORT.isSameFlashcard(editedBubbleSort));

        // same name, different attributes -> returns true
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withDefinition(VALID_DEFINITION_HEAPING)
                .withTags(VALID_TAG_DIFFICULT).build();
        assertTrue(BUBBLE_SORT.isSameFlashcard(editedBubbleSort));

        // same name, different attributes -> returns true
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_DIFFICULT).build();
        assertTrue(BUBBLE_SORT.isSameFlashcard(editedBubbleSort));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Flashcard bubbleSortCopy = new FlashcardBuilder(BUBBLE_SORT).build();
        assertTrue(BUBBLE_SORT.equals(bubbleSortCopy));

        // same object -> returns true
        assertTrue(BUBBLE_SORT.equals(BUBBLE_SORT));

        // null -> returns false
        assertFalse(BUBBLE_SORT.equals(null));

        // different type -> returns false
        assertFalse(BUBBLE_SORT.equals(5));

        // different flashcard -> returns false
        assertFalse(BUBBLE_SORT.equals(HEAPING));

        // different name -> returns false
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withTitle(VALID_NAME_HEAPING).build();
        assertFalse(BUBBLE_SORT.equals(editedBubbleSort));

        // different address -> returns false
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withDefinition(VALID_DEFINITION_HEAPING).build();
        assertFalse(BUBBLE_SORT.equals(editedBubbleSort));

        // different tags -> returns false
        editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT).withTags(VALID_TAG_FINAL).build();
        assertFalse(BUBBLE_SORT.equals(editedBubbleSort));
    }
}
