package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.VALID_QUESTION_TWO;
import static quickcache.testutil.TypicalFlashcards.RANDOM1;
import static quickcache.testutil.TypicalFlashcards.RANDOM2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import quickcache.model.flashcard.exceptions.DuplicateFlashcardException;
import quickcache.model.flashcard.exceptions.FlashcardNotFoundException;
import quickcache.testutil.FlashcardBuilder;

class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_flashcardNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(RANDOM1));
    }

    @Test
    public void contains_flashcardInList_returnsTrue() {
        uniqueFlashcardList.add(RANDOM1);
        assertTrue(uniqueFlashcardList.contains(RANDOM1));
    }

    @Test
    public void contains_flashcardWithSameIdentityFieldsInList_returnsFalse() {
        uniqueFlashcardList.add(RANDOM1);
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withQuestion(VALID_QUESTION_TWO)
            .build();
        assertFalse(uniqueFlashcardList.contains(editedRandom1));
    }

    @Test
    public void add_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicateFlashcard_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(RANDOM1);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(RANDOM1));
    }

    @Test
    public void setFlashcard_nullTargetFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, RANDOM1));
    }

    @Test
    public void setFlashcard_nullEditedFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(RANDOM1, null));
    }

    @Test
    public void setFlashcard_targetFlashcardNotInList_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.setFlashcard(RANDOM1, RANDOM1));
    }

    @Test
    public void setFlashcard_editedFlashcardIsSameFlashcard_success() {
        uniqueFlashcardList.add(RANDOM1);
        uniqueFlashcardList.setFlashcard(RANDOM1, RANDOM1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(RANDOM1);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasSameIdentity_success() {
        uniqueFlashcardList.add(RANDOM1);
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withQuestion(VALID_QUESTION_TWO)
            .build();
        uniqueFlashcardList.setFlashcard(RANDOM1, editedRandom1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(editedRandom1);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasDifferentIdentity_success() {
        uniqueFlashcardList.add(RANDOM1);
        uniqueFlashcardList.setFlashcard(RANDOM1, RANDOM2);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(RANDOM2);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcard_editedFlashcardHasNonUniqueIdentity_throwsDuplicateFlashcardException() {
        uniqueFlashcardList.add(RANDOM1);
        uniqueFlashcardList.add(RANDOM2);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(RANDOM1, RANDOM2));
    }

    @Test
    public void remove_nullFlashcard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_flashcardDoesNotExist_throwsFlashcardNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(RANDOM1));
    }

    @Test
    public void remove_existingFlashcard_removesFlashcard() {
        uniqueFlashcardList.add(RANDOM1);
        uniqueFlashcardList.remove(RANDOM1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullUniqueFlashcardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setFlashcards_uniqueFlashcardList_replacesOwnListWithProvidedUniqueFlashcardList() {
        uniqueFlashcardList.add(RANDOM1);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(RANDOM2);
        uniqueFlashcardList.setFlashcards(expectedUniqueFlashcardList);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setFlashcards_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(RANDOM1);
        List<Flashcard> flashcardList = Collections.singletonList(RANDOM2);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniqueFlashcardList = new UniqueFlashcardList();
        expectedUniqueFlashcardList.add(RANDOM2);
        assertEquals(expectedUniqueFlashcardList, uniqueFlashcardList);
    }

    @Test
    public void setFlashcards_listWithDuplicateFlashcards_throwsDuplicateFlashcardException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(RANDOM1, RANDOM1);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcards(
            listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void hashcode() {
        Question question = new OpenEndedQuestion("Question", new Answer("Answer"));
        Statistics statistics = new Statistics(5, 3);
        Set<Tag> tags = new HashSet<>();
        Flashcard flashcard = new Flashcard(question, tags, statistics);
        UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();
        UniqueFlashcardList uniqueFlashcardListToCheckAgainst = new UniqueFlashcardList();

        // no flashcards -> returns same hashcode
        assertEquals(uniqueFlashcardList.hashCode(), uniqueFlashcardListToCheckAgainst.hashCode());

        uniqueFlashcardList.add(flashcard);
        uniqueFlashcardListToCheckAgainst.add(flashcard);

        // same flashcards -> returns same hashcode
        assertEquals(uniqueFlashcardList.hashCode(), uniqueFlashcardListToCheckAgainst.hashCode());

        uniqueFlashcardListToCheckAgainst.remove(flashcard);

        // have different number of flashcards -> returns different hashcode
        assertNotEquals(uniqueFlashcardList.hashCode(), uniqueFlashcardListToCheckAgainst.hashCode());

        Flashcard differentFlashcard = new Flashcard(question, tags, new Statistics(100, 5));

        uniqueFlashcardListToCheckAgainst.add(differentFlashcard);

        //have different flashcards -> returns different hashcode
        assertNotEquals(uniqueFlashcardList.hashCode(), uniqueFlashcardListToCheckAgainst.hashCode());
    }

}
