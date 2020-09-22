package seedu.address.model.flashcard;

import org.junit.jupiter.api.Test;
import seedu.address.model.flashcard.exceptions.DuplicateFlashcardException;
import seedu.address.model.flashcard.exceptions.FlashcardNotFoundException;
import seedu.address.testutil.FlashcardBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.address.testutil.TypicalFlashcards.FLASHCARD_2;

public class UniqueFlashcardListTest {


    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_flashcardNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(FLASHCARD_1));
    }

    @Test
    public void contains_flashcardInList_returnsTrue() {
        uniqueFlashcardList.add(FLASHCARD_1);
        assertTrue(uniqueFlashcardList.contains(FLASHCARD_1));
    }

    @Test
    public void contains_flashcardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(FLASHCARD_1);
        Flashcard editedFlashcard = new FlashcardBuilder(FLASHCARD_1).withQuestion("What does SDLC stand for?")
                .withAnswer("Software development life cycle").withCategory("SDLC")
                .build();
        assertTrue(uniqueFlashcardList.contains(editedFlashcard));
    }

    @Test
    public void add_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicateFlashcard_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(FLASHCARD_1);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(FLASHCARD_1));
    }

    @Test
    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, FLASHCARD_1));
    }

    @Test
    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(FLASHCARD_1, null));
    }

    @Test
    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(FLASHCARD_1, FLASHCARD_1));
    }

    @Test
    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
        uniqueFlashcardList.add(FLASHCARD_1);
        uniqueFlashcardList.setFlashcard(FLASHCARD_1, FLASHCARD_1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(FLASHCARD_1);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
        uniqueFlashcardList.add(FLASHCARD_1);
        Flashcard editedOne = new FlashcardBuilder(FLASHCARD_1).withQuestion("What does SDLC stand for?")
                .withAnswer("Software development life cycle").withCategory("SDLC")
                .build();
        uniqueFlashcardList.setFlashcard(FLASHCARD_1, editedOne);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(editedOne);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
        uniqueFlashcardList.add(FLASHCARD_1);
        uniqueFlashcardList.setFlashcard(FLASHCARD_1, FLASHCARD_2);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(FLASHCARD_2);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(FLASHCARD_1);
        uniqueFlashcardList.add(FLASHCARD_2);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(FLASHCARD_1, FLASHCARD_2));
    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(FLASHCARD_1));
    }

    @Test
    public void remove_existingFlashcard_removesFlashcard() {
        uniqueFlashcardList.add(FLASHCARD_1);
        uniqueFlashcardList.remove(FLASHCARD_1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
        uniqueFlashcardList.add(FLASHCARD_1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(FLASHCARD_2);
        uniqueFlashcardList.setFlashcards(expectedUniqueFlashcardList);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setFlashcards_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(FLASHCARD_1);
        List<Flashcard> flashcardList = Collections.singletonList(FLASHCARD_2);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(FLASHCARD_2);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(FLASHCARD_1, FLASHCARD_1);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }

}
