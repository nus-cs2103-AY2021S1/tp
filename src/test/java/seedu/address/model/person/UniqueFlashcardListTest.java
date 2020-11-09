package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEFINITION_HEAPING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DIFFICULT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.BUBBLE_SORT;
import static seedu.address.testutil.TypicalFlashcards.HEAPING;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicateFlashcardException;
import seedu.address.model.person.exceptions.FlashcardNotFoundException;
import seedu.address.testutil.FlashcardBuilder;

public class UniqueFlashcardListTest {

    private final UniqueFlashcardList uniqueFlashcardList = new UniqueFlashcardList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueFlashcardList.contains(BUBBLE_SORT));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        assertTrue(uniqueFlashcardList.contains(BUBBLE_SORT));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_DIFFICULT).build();
        assertTrue(uniqueFlashcardList.contains(editedBubbleSort));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.add(BUBBLE_SORT));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(null, BUBBLE_SORT));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcard(BUBBLE_SORT, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList
            .setFlashcard(BUBBLE_SORT, BUBBLE_SORT));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        uniqueFlashcardList.setFlashcard(BUBBLE_SORT, BUBBLE_SORT);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(BUBBLE_SORT);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        Flashcard editedBubbleSort = new FlashcardBuilder(BUBBLE_SORT)
                .withDefinition(VALID_DEFINITION_HEAPING).withTags(VALID_TAG_DIFFICULT)
                .build();
        uniqueFlashcardList.setFlashcard(BUBBLE_SORT, editedBubbleSort);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(editedBubbleSort);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        uniqueFlashcardList.setFlashcard(BUBBLE_SORT, HEAPING);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(HEAPING);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        uniqueFlashcardList.add(HEAPING);
        assertThrows(DuplicateFlashcardException.class, () -> uniqueFlashcardList.setFlashcard(BUBBLE_SORT, HEAPING));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(FlashcardNotFoundException.class, () -> uniqueFlashcardList.remove(BUBBLE_SORT));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        uniqueFlashcardList.remove(BUBBLE_SORT);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((UniqueFlashcardList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(HEAPING);
        uniqueFlashcardList.setFlashcards(expectedUniquePersonList);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFlashcardList.setFlashcards((List<Flashcard>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueFlashcardList.add(BUBBLE_SORT);
        List<Flashcard> flashcardList = Collections.singletonList(HEAPING);
        uniqueFlashcardList.setFlashcards(flashcardList);
        UniqueFlashcardList expectedUniquePersonList = new UniqueFlashcardList();
        expectedUniquePersonList.add(HEAPING);
        assertEquals(expectedUniquePersonList, uniqueFlashcardList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Flashcard> listWithDuplicateFlashcards = Arrays.asList(BUBBLE_SORT, BUBBLE_SORT);
        assertThrows(DuplicateFlashcardException.class, () ->
                uniqueFlashcardList.setFlashcards(listWithDuplicateFlashcards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueFlashcardList.asUnmodifiableObservableList().remove(0));
    }
}
