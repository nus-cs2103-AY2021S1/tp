package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.address.testutil.TypicalFlashcards.getTypicalAddressBook;

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

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

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
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateFlashcard_throwsDuplicateFlashcardException() {
        Flashcard copiedFlashcardOne = new FlashcardBuilder(FLASHCARD_1).build();
        List<Flashcard> newFlashcards = Arrays.asList(FLASHCARD_1, copiedFlashcardOne);
        AddressBookStub newData = new AddressBookStub(newFlashcards);

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
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        AddressBookStub(Collection<Flashcard> flashcards) {
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
