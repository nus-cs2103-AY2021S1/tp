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

public class AddressBookTest {

    private final FlashcardDeck addressBook = new FlashcardDeck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        FlashcardDeck newData = getTypicalFlashcardDeck();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateFlashcard_throwsDuplicateFlashcardException() {
        Flashcard copiedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).build();
        List<Flashcard> newFlashcards = Arrays.asList(FLASHCARD_1, copiedFlashcardOne);
        FlashcardDeckStub newData = new FlashcardDeckStub(newFlashcards);

        assertThrows(DuplicateFlashcardException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasFlashcard_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasFlashcard(null));
    }

    @Test
    public void hasFlashcard_flashcardNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void hasFlashcard_flashcardInAddressBook_returnsTrue() {
        addressBook.addFlashcard(FLASHCARD_1);
        assertTrue(addressBook.hasFlashcard(FLASHCARD_1));
    }

    @Test
    public void hasFlashcard_flashcardWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addFlashcard(FLASHCARD_1);
        Flashcard editedFlashcardTwo = new FlashcardBuilder(FLASHCARD_2).withQuestion(VALID_QUESTION_1)
                .build();
        assertTrue(addressBook.hasFlashcard(editedFlashcardTwo));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose flashcards list can violate interface constraints.
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
