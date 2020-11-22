package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.logic.commands.CommandTestUtil.VALID_TAG_CHAPTER_ONE;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FLASHCARD_1;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.TABLE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.model.flashcard.exceptions.DuplicateFlashCardException;
import seedu.forgetfulnus.model.flashcard.exceptions.FlashCardNotFoundException;
import seedu.forgetfulnus.testutil.FlashCardBuilder;

public class UniqueFlashCardListTest {

    private final UniqueFlashCardList uniqueFlashCardList = new UniqueFlashCardList();

    @Test
    public void contains_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.contains(null));
    }

    @Test
    public void contains_flashCardNotInList_returnsFalse() {
        assertFalse(uniqueFlashCardList.contains(FLASHCARD_1));
    }

    @Test
    public void contains_flashCardInList_returnsTrue() {
        uniqueFlashCardList.add(FLASHCARD_1);
        assertTrue(uniqueFlashCardList.contains(FLASHCARD_1));
    }

    @Test
    public void contains_flashCardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashCardList.add(FLASHCARD_1);
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        assertTrue(uniqueFlashCardList.contains(editedAlice));
    }

    @Test
    public void add_nullflashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.add(null));
    }

    @Test
    public void add_duplicateFlashCard_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(FLASHCARD_1);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.add(FLASHCARD_1));
    }

    @Test
    public void setFlashCard_nullTargetFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(null, FLASHCARD_1));
    }

    @Test
    public void setFlashCard_nullEditedFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCard(FLASHCARD_1, null));
    }

    @Test
    public void setFlashCard_targetFlashCardNotInList_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.setFlashCard(
                FLASHCARD_1, FLASHCARD_1));
    }

    @Test
    public void setFlashCard_editedFlashCardIsSameFlashCard_success() {
        uniqueFlashCardList.add(FLASHCARD_1);
        uniqueFlashCardList.setFlashCard(FLASHCARD_1, FLASHCARD_1);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(FLASHCARD_1);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasSameIdentity_success() {
        uniqueFlashCardList.add(FLASHCARD_1);
        FlashCard editedAlice = new FlashCardBuilder(FLASHCARD_1).withTags(VALID_TAG_CHAPTER_ONE)
                .build();
        uniqueFlashCardList.setFlashCard(FLASHCARD_1, editedAlice);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(editedAlice);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasDifferentIdentity_success() {
        uniqueFlashCardList.add(FLASHCARD_1);
        uniqueFlashCardList.setFlashCard(FLASHCARD_1, TABLE);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCard_editedFlashCardHasNonUniqueIdentity_throwsDuplicateFlashCardException() {
        uniqueFlashCardList.add(FLASHCARD_1);
        uniqueFlashCardList.add(TABLE);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList.setFlashCard(FLASHCARD_1, TABLE));
    }

    @Test
    public void remove_nullFlashCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.remove(null));
    }

    @Test
    public void remove_flashCardDoesNotExist_throwsFlashCardNotFoundException() {
        assertThrows(FlashCardNotFoundException.class, () -> uniqueFlashCardList.remove(FLASHCARD_1));
    }

    @Test
    public void remove_existingFlashCard_removesFlashCard() {
        uniqueFlashCardList.add(FLASHCARD_1);
        uniqueFlashCardList.remove(FLASHCARD_1);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullUniqueFlashCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((UniqueFlashCardList) null));
    }

    @Test
    public void setFlashCards_uniqueFlashCardList_replacesOwnListWithProvidedUniqueFlashCardList() {
        uniqueFlashCardList.add(FLASHCARD_1);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        uniqueFlashCardList.setFlashCards(expectedUniqueFlashCardList);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashCardList.setFlashCards((List<FlashCard>) null));
    }

    @Test
    public void setFlashCards_list_replacesOwnListWithProvidedList() {
        uniqueFlashCardList.add(FLASHCARD_1);
        List<FlashCard> flashCardList = Collections.singletonList(TABLE);
        uniqueFlashCardList.setFlashCards(flashCardList);
        UniqueFlashCardList expectedUniqueFlashCardList = new UniqueFlashCardList();
        expectedUniqueFlashCardList.add(TABLE);
        assertEquals(expectedUniqueFlashCardList, uniqueFlashCardList);
    }

    @Test
    public void setFlashCards_listWithDuplicateFlashCards_throwsDuplicateFlashCardException() {
        List<FlashCard> listWithDuplicateFlashCards = Arrays.asList(FLASHCARD_1, FLASHCARD_1);
        assertThrows(DuplicateFlashCardException.class, () -> uniqueFlashCardList
                .setFlashCards(listWithDuplicateFlashCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashCardList.asUnmodifiableObservableList().remove(0));
    }
}
