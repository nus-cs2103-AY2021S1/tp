package seedu.address.model.deck.entry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.entry.TypicalEntries.JAPANESE_1;
import static seedu.address.testutil.entry.TypicalEntries.SPANISH;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.deck.exceptions.DuplicateEntryException;
import seedu.address.model.deck.exceptions.EntryNotFoundException;
import seedu.address.testutil.entry.EntryBuilder;

public class UniqueEntryListTest {

    private final UniqueEntryList uniqueEntryList = new UniqueEntryList();

    @Test
    public void contains_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.contains(null));
    }

    @Test
    public void contains_entryNotInList_returnsFalse() {
        assertFalse(uniqueEntryList.contains(JAPANESE_1));
    }

    @Test
    public void contains_entryInList_returnsTrue() {
        uniqueEntryList.add(JAPANESE_1);
        assertTrue(uniqueEntryList.contains(JAPANESE_1));
    }

    @Test
    public void contains_entryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEntryList.add(JAPANESE_1);
        Entry editedJapanese = new EntryBuilder(JAPANESE_1).build();
        assertTrue(uniqueEntryList.contains(editedJapanese));
    }

    @Test
    public void add_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.add(null));
    }

    @Test
    public void add_duplicateEntry_throwsDuplicateEntryException() {
        uniqueEntryList.add(JAPANESE_1);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.add(JAPANESE_1));
    }

    @Test
    public void setEntry_nullTargetEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(null, JAPANESE_1));
    }

    @Test
    public void setEntry_nullEditedEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntry(JAPANESE_1, null));
    }

    @Test
    public void setEntry_targetEntryNotInList_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.setEntry(JAPANESE_1, JAPANESE_1));
    }

    @Test
    public void setEntry_editedEntryIsSameEntry_success() {
        uniqueEntryList.add(JAPANESE_1);
        uniqueEntryList.setEntry(JAPANESE_1, JAPANESE_1);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(JAPANESE_1);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasSameIdentity_success() {
        Entry editedJapanese = new EntryBuilder(JAPANESE_1).build();
        uniqueEntryList.add(JAPANESE_1);
        uniqueEntryList.setEntry(JAPANESE_1, editedJapanese);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(editedJapanese);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasDifferentIdentity_success() {
        uniqueEntryList.add(JAPANESE_1);
        uniqueEntryList.setEntry(JAPANESE_1, SPANISH);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(SPANISH);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntry_editedEntryHasNonUniqueIdentity_throwsDuplicateEntryException() {
        uniqueEntryList.add(JAPANESE_1);
        uniqueEntryList.add(SPANISH);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntry(JAPANESE_1, SPANISH));
    }

    @Test
    public void remove_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.remove(null));
    }

    @Test
    public void remove_entryDoesNotExist_throwsEntryNotFoundException() {
        assertThrows(EntryNotFoundException.class, () -> uniqueEntryList.remove(JAPANESE_1));
    }

    @Test
    public void remove_existingEntry_removesEntry() {
        uniqueEntryList.add(JAPANESE_1);
        uniqueEntryList.remove(JAPANESE_1);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullUniqueEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntries((UniqueEntryList) null));
    }

    @Test
    public void setEntries_uniqueEntryList_replacesOwnListWithProvidedUniqueEntryList() {
        uniqueEntryList.add(JAPANESE_1);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(SPANISH);
        uniqueEntryList.setEntries(expectedUniqueEntryList);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEntryList.setEntries((List<Entry>) null));
    }

    @Test
    public void setEntries_list_replacesOwnListWithProvidedList() {
        uniqueEntryList.add(JAPANESE_1);
        List<Entry> entryList = Collections.singletonList(SPANISH);
        uniqueEntryList.setEntries(entryList);
        UniqueEntryList expectedUniqueEntryList = new UniqueEntryList();
        expectedUniqueEntryList.add(SPANISH);
        assertEquals(expectedUniqueEntryList, uniqueEntryList);
    }

    @Test
    public void setEntries_listWithDuplicateEntries_throwsDuplicateEntryException() {
        List<Entry> listWithDuplicateEntries = Arrays.asList(JAPANESE_1, JAPANESE_1);
        assertThrows(DuplicateEntryException.class, () -> uniqueEntryList.setEntries(listWithDuplicateEntries));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEntryList.asUnmodifiableObservableList().remove(INDEX_FIRST.getZeroBased()));
    }

    @Test
    public void length_emptyList_returnsListLengthZero() {
        assertEquals(uniqueEntryList.length(), 0);
    }

    @Test
    public void length_oneEntryList_returnsListLengthOne() {
        uniqueEntryList.add(new EntryBuilder().build());
        assertEquals(uniqueEntryList.length(), 1);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(uniqueEntryList.isEmpty());
    }

    @Test
    public void isEmpty_oneEntryList_returnsFalse() {
        uniqueEntryList.add(new EntryBuilder().build());
        assertFalse(uniqueEntryList.isEmpty());
    }

    @Test
    public void get_emptyList_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> uniqueEntryList.get(INDEX_FIRST.getZeroBased()));
    }
}
