package seedu.flashcard.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.flashcard.testutil.Assert.assertThrows;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.flashcard.testutil.FlashcardBuilder;

public class FlashcardDeckTest {

    private final FlashcardDeck flashcardDeck = new FlashcardDeck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), flashcardDeck.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardDeck.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFlashcardDeck_replacesData() {
        FlashcardDeck newData = getTypicalFlashcardDeck();
        flashcardDeck.resetData(newData);
        assertEquals(newData, flashcardDeck);
    }

    @Test
    public void resetData_withDuplicateFlashcard_throwsDuplicateFlashcardException() {
        Flashcard copiedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).build();
        List<Flashcard> newFlashcards = Arrays.asList(FLASHCARD_1, copiedFlashcardOne);
        FlashcardDeckStub newData = new FlashcardDeckStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> flashcardDeck.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> flashcardDeck.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInFlashcardDeck_returnsFalse() {
        assertFalse(flashcardDeck.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void hasFlashcard_flashcardInFlashcardDeck_returnsTrue() {
        flashcardDeck.addFlashcard(FLASHCARD_1);
        assertTrue(flashcardDeck.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void hasFlashcard_flashcardWithSameIdentityFieldsInFlashcardDeck_returnsTrue() {
        flashcardDeck.addFlashcard(FLASHCARD_1);
        Flashcard editedFlashcardTwo = new FlashcardBuilder(FLASHCARD_2).withQuestion(VALID_QUESTION_1)
                .build();
        assertTrue(flashcardDeck.hasFlashcard(editedFlashcardTwo));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> flashcardDeck.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyFlashcardDeck whose flashcards list can violate interface constraints.
     */
    private static class FlashcardDeckStub implements ReadOnlyFlashcardDeck {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        FlashcardDeckStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
