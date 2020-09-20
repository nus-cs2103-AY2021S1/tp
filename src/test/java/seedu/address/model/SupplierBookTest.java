package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUPPLIER_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUPPLIER_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.CHICKEN;
import static seedu.address.testutil.TypicalItems.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.testutil.ItemBuilder;

public class SupplierBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateItem_throwsItemPersonException() {
        // Two persons with the same identity fields
        Item editedAlice = new ItemBuilder(CHICKEN).withSupplier(VALID_SUPPLIER_CHICKEN).withTags(VALID_TAG_MEAT)
                .build();
        List<Item> newItems = Arrays.asList(CHICKEN, editedAlice);
        AddressBookStub newData = new AddressBookStub(newItems);

        assertThrows(DuplicateItemException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasItem(CHICKEN));
    }

    @Test
    public void hasItem_itemInAddressBook_returnsTrue() {
        addressBook.addItem(CHICKEN);
        assertTrue(addressBook.hasItem(CHICKEN));
    }

    @Test
    public void hasItem_itemWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addItem(CHICKEN);
        Item editedChicken = new ItemBuilder(CHICKEN).withSupplier(VALID_SUPPLIER_CHICKEN).withTags(VALID_TAG_MEAT)
                .build();
        assertTrue(addressBook.hasItem(editedChicken));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getItemList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose items list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        AddressBookStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }

}
