package seedu.address.model.person;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.exceptions.DuplicateFlashcardException;
import seedu.address.model.person.exceptions.FlashcardNotFoundException;
import seedu.address.testutil.FlashcardBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalFlashcards.ONE;
import static seedu.address.testutil.TypicalFlashcards.TWO;

public class UniqueFlashcardListTest {


    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_flashcardNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(ONE));
    }

    @Test
    public void contains_flashcardInList_returnsTrue() {
        uniqueFlashcardList.add(ONE);
        assertTrue(uniqueFlashcardList.contains(ONE));
    }

    @Test
    public void contains_flashcardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(ONE);
        Flashcard editedFlashcard = new FlashcardBuilder(ONE).withQuestion("What does SDLC stand for?")
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
        uniqueFlashcardList.add(ONE);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(ONE));
    }

    @Test
    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, ONE));
    }

    @Test
    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(ONE, null));
    }

    @Test
    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(ONE, ONE));
    }

    @Test
    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
        uniqueFlashcardList.add(ONE);
        uniqueFlashcardList.setFlashcard(ONE, ONE);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(ONE);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
        uniqueFlashcardList.add(ONE);
        Flashcard editedOne = new FlashcardBuilder(ONE).withQuestion("What does SDLC stand for?")
                .withAnswer("Software development life cycle").withCategory("SDLC")
                .build();
        uniqueFlashcardList.setFlashcard(ONE, editedOne);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(editedOne);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
        uniqueFlashcardList.add(ONE);
        uniqueFlashcardList.setFlashcard(ONE, TWO);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(TWO);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(ONE);
        uniqueFlashcardList.add(TWO);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(ONE, TWO));
    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(ONE));
    }

    @Test
    public void remove_existingFlashcard_removesFlashcard() {
        uniqueFlashcardList.add(ONE);
        uniqueFlashcardList.remove(ONE);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
        uniqueFlashcardList.add(ONE);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(TWO);
        uniqueFlashcardList.setFlashcards(expectedUniqueFlashcardList);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setFlashcards_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(ONE);
        List<Flashcard> flashcardList = Collections.singletonList(TWO);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(TWO);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(ONE, ONE);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }

}
