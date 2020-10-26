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

    private final UniqueBinItemList UniqueBinItemList = new UniqueBinItemList();

    @Test
    public void contains_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.contains(null));
    }

    @Test
    public void contains_binItemNotInList_returnsFalse() {
        assertFalse(UniqueBinItemList.contains(BIN_ITEM_A));
    }

    @Test
    public void contains_binItemInList_returnsTrue() {
        UniqueBinItemList.add(BIN_ITEM_A);
        assertTrue(UniqueBinItemList.contains(BIN_ITEM_A));
    }

    @Test
    public void contains_binItemWithSameIdentityFieldsInList_returnsTrue() {
        UniqueBinItemList.add(BIN_ITEM_A);
        BinItem editedBinItemA = new BinItemBuilder(BIN_ITEM_A).withDateDeleted(VALID_DATE_DELETED_A).build();
        assertTrue(UniqueBinItemList.contains(editedBinItemA));
    }

    @Test
    public void add_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.add(null));
    }

    @Test
    public void add_duplicatebinItem_throwsDuplicatebinItemException() {
        UniqueBinItemList.add(BIN_ITEM_A);
        assertThrows(DuplicateBinItemException.class, () -> UniqueBinItemList.add(BIN_ITEM_A));
    }

    @Test
    public void setbinItem_nullTargetbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.setBinItem(null, BIN_ITEM_A));
    }

    @Test
    public void setbinItem_nullEditedbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.setBinItem(BIN_ITEM_A, null));
    }

    @Test
    public void setbinItem_targetbinItemNotInList_throwsbinItemNotFoundException() {
        assertThrows(BinItemNotFoundException.class, () -> UniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_A));
    }

    @Test
    public void setbinItem_editedbinItemIsSamebinItem_success() {
        UniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_A);
        UniqueBinItemList expectedUniqueBinItemList = new UniqueBinItemList();
        expectedUniqueBinItemList.add(BIN_ITEM_A);
        assertEquals(expectedUniqueBinItemList, UniqueBinItemList);
    }


    @Test
    public void setbinItem_editedbinItemHasDifferentIdentity_success() {
        UniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_B);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        assertEquals(expectedUniquebinItemList, UniqueBinItemList);
    }

    @Test
    public void setbinItem_editedbinItemHasNonUniqueIdentity_throwsDuplicatebinItemException() {
        UniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList.add(BIN_ITEM_B);
        assertThrows(DuplicateBinItemException.class, () -> UniqueBinItemList.setBinItem(BIN_ITEM_A, BIN_ITEM_B));
    }

    @Test
    public void remove_nullbinItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.remove(null));
    }

    @Test
    public void remove_binItemDoesNotExist_throwsbinItemNotFoundException() {
        assertThrows(BinItemNotFoundException.class, () -> UniqueBinItemList.remove(BIN_ITEM_A));
    }

    @Test
    public void remove_existingbinItem_removesbinItem() {
        UniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList.remove(BIN_ITEM_A);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        assertEquals(expectedUniquebinItemList, UniqueBinItemList);
    }

    @Test
    public void setbinItems_nullUniquebinItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.setBinItems((UniqueBinItemList) null));
    }

    @Test
    public void setbinItems_uniquebinItemList_replacesOwnListWithProvidedUniquebinItemList() {
        UniqueBinItemList.add(BIN_ITEM_A);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        UniqueBinItemList.setBinItems(expectedUniquebinItemList);
        assertEquals(expectedUniquebinItemList, UniqueBinItemList);
    }

    @Test
    public void setbinItems_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> UniqueBinItemList.setBinItems((List<BinItem>) null));
    }

    @Test
    public void setbinItems_list_replacesOwnListWithProvidedList() {
        UniqueBinItemList.add(BIN_ITEM_A);
        List<BinItem> binItemList = Collections.singletonList(BIN_ITEM_B);
        UniqueBinItemList.setBinItems(binItemList);
        UniqueBinItemList expectedUniquebinItemList = new UniqueBinItemList();
        expectedUniquebinItemList.add(BIN_ITEM_B);
        assertEquals(expectedUniquebinItemList, UniqueBinItemList);
    }

    @Test
    public void setbinItems_listWithDuplicatebinItems_throwsDuplicatebinItemException() {
        List<BinItem> listWithDuplicateBinitems = Arrays.asList(BIN_ITEM_A, BIN_ITEM_A);
        assertThrows(DuplicateBinItemException.class, () -> UniqueBinItemList.setBinItems(listWithDuplicateBinitems));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> UniqueBinItemList.asUnmodifiableObservableList().remove(0));
    }
}
