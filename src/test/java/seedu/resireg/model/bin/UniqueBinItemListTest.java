package seedu.resireg.model.bin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_DATE_DELETED_A;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_A;
import static seedu.resireg.testutil.TypicalBinItems.BIN_ITEM_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.bin.exceptions.BinItemNotFoundException;
import seedu.resireg.model.student.exceptions.DuplicateBinItemException;
import seedu.resireg.testutil.BinItemBuilder;

public class UniqueBinItemListTest {

    private final UniqueBinItemList uniqueBinItemList = new UniqueBinItemList();

    @Test
    public void contains_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.contains(null));
    }

    @Test
    public void contains_binItemNotInList_returnsFalse() {
        assertFalse(uniqueBinItemList.contains(BIN_ITEM_A));
    }

    @Test
    public void contains_binItemInList_returnsTrue() {
        uniqueBinItemList.add(BIN_ITEM_A);
        assertTrue(uniqueBinItemList.contains(BIN_ITEM_A));
    }

    @Test
    public void contains_binItemWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBinItemList.add(BIN_ITEM_A);
        BinItem editedBinItemA = new BinItemBuilder(BIN_ITEM_A).withDateDeleted(VALID_DATE_DELETED_A).build();
        assertTrue(uniqueBinItemList.contains(editedBinItemA));
    }

    @Test
    public void add_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.add(null));
    }

    @Test
    public void add_duplicatebinItem_throwsDuplicatebinItemException() {
        uniqueBinItemList.add(BIN_ITEM_A);
        assertThrows(DuplicateBinItemException.class, () -> uniqueBinItemList.add(BIN_ITEM_A));
    }

    @Test
    public void setbinItem_nullTargetbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.setBinItem(null, BIN_ITEM_A));
    }

    @Test
    public void setbinItem_nullEditedbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.setBinItem(BIN_ITEM_A, null));
    }

    @Test
    public void setbinItem_targetbinItemNotInList_throwsbinItemNotFoundException() {
        assertThrows(BinItemNotFoundException.class, () -> uniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_A));
    }

    @Test
    public void setbinItem_editedbinItemIsSamebinItem_success() {
        uniqueBinItemList.add(BIN_ITEM_A);
        uniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_A);
        UniqueBinItemList expectedUniqueBinItemList = new UniqueBinItemList();
        expectedUniqueBinItemList.add(BIN_ITEM_A);
        assertEquals(expectedUniqueBinItemList, uniqueBinItemList);
    }


    @Test
    public void setbinItem_editedbinItemHasDifferentIdentity_success() {
        uniqueBinItemList.add(BIN_ITEM_A);
        uniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_B);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        assertEquals(expectedUniquebinItemList, uniqueBinItemList);
    }

    @Test
    public void setbinItem_editedbinItemHasNonUniqueIdentity_throwsDuplicatebinItemException() {
        uniqueBinItemList.add(BIN_ITEM_A);
        uniqueBinItemList.add(BIN_ITEM_B);
        assertThrows(DuplicateBinItemException.class, () -> uniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_B));
    }

    @Test
    public void remove_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.remove(null));
    }

    @Test
    public void remove_binItemDoesNotExist_throwsbinItemNotFoundException() {
        assertThrows(BinItemNotFoundException.class, () -> uniqueBinItemList.remove(BIN_ITEM_A));
    }

    @Test
    public void remove_existingbinItem_removesbinItem() {
        uniqueBinItemList.add(BIN_ITEM_A);
        uniqueBinItemList.remove(BIN_ITEM_A);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        assertEquals(expectedUniquebinItemList, uniqueBinItemList);
    }

    @Test
    public void setbinItems_nullUniquebinItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.setBinItems((UniqueBinItemList) null));
    }

    @Test
    public void setbinItems_uniquebinItemList_replacesOwnListWithProvidedUniquebinItemList() {
        uniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        uniqueBinItemList.setBinItems(expectedUniquebinItemList);
        assertEquals(expectedUniquebinItemList, uniqueBinItemList);
    }

    @Test
    public void setbinItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBinItemList.setBinItems((List<BinItem>) null));
    }

    @Test
    public void setbinItems_list_replacesOwnListWithProvidedList() {
        uniqueBinItemList.add(BIN_ITEM_A);
        List<BinItem> binItemList = Collections.singletonList(BIN_ITEM_B);
        uniqueBinItemList.setBinItems(binItemList);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        assertEquals(expectedUniquebinItemList, uniqueBinItemList);
    }

    @Test
    public void setbinItems_listWithDuplicatebinItems_throwsDuplicatebinItemException() {
        List<BinItem> listWithDuplicateBinitems = Arrays.asList(BIN_ITEM_A, BIN_ITEM_A);
        assertThrows(DuplicateBinItemException.class, () -> uniqueBinItemList.setBinItems(listWithDuplicateBinitems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBinItemList.asUnmodifiableObservableList().remove(0));
    }
}
