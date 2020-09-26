package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.flashcard.Flashcard;
import seedu.address.model.person.Person;

public class QuickCacheTest {

    private final QuickCache quickCache = new QuickCache();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), quickCache.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickCache.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        QuickCache newData = getTypicalAddressBook();
        quickCache.resetData(newData);
        assertEquals(newData, quickCache);
    }

    //    @Test
    //    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
    //        // Two persons with the same identity fields
    //        Flashcard editedAlice = new FlashcardBuilder(ALICE).withAnswer(VALID_ANSWER_BOB)
    //                .build();
    //        List<Person> newPersons = Arrays.asList();
    //        List<Flashcard> newFlashcards = Arrays.asList(ALICE, editedAlice);
    //        QuickCacheStub newData = new QuickCacheStub(newPersons, newFlashcards);
    //
    //        assertThrows(DuplicatePersonException.class, () -> quickCache.resetData(newData));
    //    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickCache.hasFlashcard(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(quickCache.hasFlashcard(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        quickCache.addFlashcard(ALICE);
        assertTrue(quickCache.hasFlashcard(ALICE));
    }

    //    @Test
    //    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
    //        quickCache.addPerson(ALICE);
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        assertTrue(quickCache.hasPerson(editedAlice));
    //    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quickCache.getFlashcardList().remove(0));
    }

    /**
     * A stub ReadOnlyQuickCache whose persons list can violate interface constraints.
     */
    private static class QuickCacheStub implements ReadOnlyQuickCache {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        QuickCacheStub(Collection<Person> persons, Collection<Flashcard> flashcards) {
            this.persons.setAll(persons);
            this.flashcards.setAll(flashcards);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
