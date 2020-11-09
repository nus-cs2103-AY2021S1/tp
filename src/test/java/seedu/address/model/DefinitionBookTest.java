package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.BUBBLE_SORT;
import static seedu.address.testutil.TypicalFlashcards.getTypicalFlashcardBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.exceptions.DuplicateFlashcardException;
import seedu.address.testutil.FlashcardBuilder;

public class DefinitionBookTest {

    private final FlashcardBook flashcardBook = new FlashcardBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashcardBook.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFlashcardBook_replacesData() {
        FlashcardBook newData = getTypicalFlashcardBook();
        flashcardBook.resetData(newData);
        assertEquals(newData, flashcardBook);
    }

    @Test
    public void resetData_withDuplicateFlashcards_throwsDuplicateFlashcardsException() {
        // Two flashcards with the same identity fields
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_DIFFICULT).build();
        List<Flashcard> newFlashcards = Arrays.asList(BUBBLE_SORT, editedBubbleSort);
        FlashcardBookStub newData = new FlashcardBookStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> flashcardBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardBook.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashcardBook_returnsFalse() {
        assertFalse(flashcardBook.hasFlashcard(BUBBLE_SORT));
    }

    @Test
    public void hasFlashcard_flashcardInFlashcardBook_returnsTrue() {
        flashcardBook.addFlashcard(BUBBLE_SORT);
        assertTrue(flashcardBook.hasFlashcard(BUBBLE_SORT));
    }

    @Test
    public void hasFlashcard_flashcardWithSameIdentityFieldsInFlashcardBook_returnsTrue() {
        flashcardBook.addFlashcard(BUBBLE_SORT);
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_DIFFICULT).build();
        assertTrue(flashcardBook.hasFlashcard(editedBubbleSort));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashcardBook.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyFlashcardBook whose flashcards list can violate interface constraints.
     */
    private static class FlashcardBookStub implements ReadOnlyFlashcardBook {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        FlashcardBookStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
