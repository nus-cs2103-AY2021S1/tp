package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.FAIRPRICE;
import static seedu.stock.testutil.TypicalSerialNumberSets.NTUC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.model.stock.exceptions.DuplicateSerialNumberSetException;
import seedu.stock.model.stock.exceptions.SerialNumberSetNotFoundException;
import seedu.stock.testutil.SerialNumberSetBuilder;

public class UniqueSerialNumberSetListTest {

    private final UniqueSerialNumberSetList uniqueSerialNumberSetList = new UniqueSerialNumberSetList();

    @Test
    public void contains_nullSerialNumberSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSerialNumberSetList.contains(null));
    }

    @Test
    public void contains_serialNumberSetNotInList_returnsFalse() {
        assertFalse(uniqueSerialNumberSetList.contains(NTUC));
    }

    @Test
    public void contains_serialNumberSetInList_returnsTrue() {
        uniqueSerialNumberSetList.add(NTUC);
        assertTrue(uniqueSerialNumberSetList.contains(NTUC));
    }

    @Test
    public void add_nullSerialNumberSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSerialNumberSetList.add(null));
    }

    @Test
    public void add_duplicateSerialNumberSet_throwsDuplicateSerialNumberSetException() {
        uniqueSerialNumberSetList.add(NTUC);
        assertThrows(DuplicateSerialNumberSetException.class, () -> uniqueSerialNumberSetList.add(NTUC));
    }

    @Test
    public void setSerialNumberSet_nullTargetSerialNumberSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSerialNumberSetList.setSerialNumberSet(null, NTUC));
    }

    @Test
    public void setSerialNumberSet_nullEditedSerialNumberSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSerialNumberSetList.setSerialNumberSet(NTUC, null));
    }

    @Test
    public void setSerialNumberSet_targetSerialNumberSetNotInList_throwsSerialNumberSetNotFoundException() {
        assertThrows(SerialNumberSetNotFoundException.class, () ->
                uniqueSerialNumberSetList.setSerialNumberSet(NTUC, NTUC));
    }

    @Test
    public void setSerialNumberSet_editedSerialNumberSetIsSameSerialNumberSet_success() {
        uniqueSerialNumberSetList.add(NTUC);
        uniqueSerialNumberSetList.setSerialNumberSet(NTUC, NTUC);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        expectedUniqueSerialNumberSetList.add(NTUC);
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSet_editedSerialNumberSetHasSameIdentity_success() {
        uniqueSerialNumberSetList.add(NTUC);
        SerialNumberSet editedNtuc = new SerialNumberSetBuilder(NTUC).withAccumulatedQuantity("100")
                .build();
        uniqueSerialNumberSetList.setSerialNumberSet(NTUC, editedNtuc);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        expectedUniqueSerialNumberSetList.add(editedNtuc);
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSet_editedSerialNumberSetHasDifferentIdentity_success() {
        uniqueSerialNumberSetList.add(NTUC);
        uniqueSerialNumberSetList.setSerialNumberSet(NTUC, FAIRPRICE);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        expectedUniqueSerialNumberSetList.add(FAIRPRICE);
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSet_editedSerialNumberSetHasNonUniqueIdentity_throwsDuplicateSerialNumberSetException() {
        uniqueSerialNumberSetList.add(NTUC);
        uniqueSerialNumberSetList.add(FAIRPRICE);
        assertThrows(DuplicateSerialNumberSetException.class, () ->
                            uniqueSerialNumberSetList.setSerialNumberSet(NTUC, FAIRPRICE));
    }

    @Test
    public void remove_nullSerialNumberSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueSerialNumberSetList.remove(null));
    }

    @Test
    public void remove_serialNumberSetDoesNotExist_throwsSerialNumberSetNotFoundException() {
        assertThrows(SerialNumberSetNotFoundException.class, () -> uniqueSerialNumberSetList.remove(NTUC));
    }

    @Test
    public void remove_existingSerialNumberSet_removesSerialNumberSet() {
        uniqueSerialNumberSetList.add(NTUC);
        uniqueSerialNumberSetList.remove(NTUC);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSets_nullUniqueSerialNumberSetList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                                      uniqueSerialNumberSetList.setSerialNumberSets((UniqueSerialNumberSetList) null));
    }

    @Test
    public void setSerialNumberSets_uniqueSerialNumberSetList_replacesOwnListWithProvidedUniqueSerialNumberSetList() {
        uniqueSerialNumberSetList.add(NTUC);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        expectedUniqueSerialNumberSetList.add(FAIRPRICE);
        uniqueSerialNumberSetList.setSerialNumberSets(expectedUniqueSerialNumberSetList);
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSets_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueSerialNumberSetList.setSerialNumberSets((List<SerialNumberSet>) null));
    }

    @Test
    public void setSerialNumberSets_list_replacesOwnListWithProvidedList() {
        uniqueSerialNumberSetList.add(NTUC);
        List<SerialNumberSet> serialNumberSetList = Collections.singletonList(FAIRPRICE);
        uniqueSerialNumberSetList.setSerialNumberSets(serialNumberSetList);
        UniqueSerialNumberSetList expectedUniqueSerialNumberSetList = new UniqueSerialNumberSetList();
        expectedUniqueSerialNumberSetList.add(FAIRPRICE);
        assertEquals(expectedUniqueSerialNumberSetList, uniqueSerialNumberSetList);
    }

    @Test
    public void setSerialNumberSets_listWithDuplicateSerialNumberSets_throwsDuplicateSerialNumberSetException() {
        List<SerialNumberSet> listWithDuplicateSerialNumberSets = Arrays.asList(NTUC, NTUC);
        assertThrows(DuplicateSerialNumberSetException.class, () ->
                uniqueSerialNumberSetList.setSerialNumberSets(listWithDuplicateSerialNumberSets));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, (
        ) -> uniqueSerialNumberSetList.asUnmodifiableObservableList().remove(0));
    }
}
