package quickcache.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.logic.commands.CommandTestUtil.VALID_TAG_LSM1301;
import static quickcache.logic.commands.CommandTestUtil.VALID_TAG_MODULE;
import static quickcache.testutil.Assert.assertThrows;
import static quickcache.testutil.TypicalFlashcards.RANDOM1;
import static quickcache.testutil.TypicalFlashcards.getTypicalQuickCache;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.exceptions.DuplicateFlashcardException;
import quickcache.testutil.FlashcardBuilder;

public class QuickCacheTest {

    private final QuickCache quickCache = new QuickCache();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), quickCache.getFlashcardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickCache.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        QuickCache newData = getTypicalQuickCache();
        quickCache.resetData(newData);
        assertEquals(newData, quickCache);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withTags(VALID_TAG_LSM1301).build();
        List<Flashcard> newPersons = Arrays.asList(RANDOM1, editedRandom1);
        QuickCacheStub newData = new QuickCacheStub(newPersons);

        assertThrows(DuplicateFlashcardException.class, () -> quickCache.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> quickCache.hasFlashcard(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(quickCache.hasFlashcard(RANDOM1));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        quickCache.addFlashcard(RANDOM1);
        assertTrue(quickCache.hasFlashcard(RANDOM1));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        quickCache.addFlashcard(RANDOM1);
        Flashcard editedRandom1 = new FlashcardBuilder(RANDOM1).withTags(VALID_TAG_MODULE).build();
        assertTrue(quickCache.hasFlashcard(editedRandom1));
    }

    @Test
    public void getFlashcardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> quickCache.getFlashcardList()
                .remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class QuickCacheStub implements ReadOnlyQuickCache {
        private final ObservableList<Flashcard> flashcards = FXCollections.observableArrayList();

        QuickCacheStub(Collection<Flashcard> persons) {
            this.flashcards.setAll(persons);
        }

        @Override
        public ObservableList<Flashcard> getFlashcardList() {
            return flashcards;
        }
    }

}
