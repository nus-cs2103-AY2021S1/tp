package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class ItemListTest {

    private ItemList itemList;
    private Item apple;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
        apple = new ItemBuilder(APPLE).build();
    }
    @Test
    public void constructor() {
        assertEquals(new ItemList(), itemList);
        assertEquals(Collections.emptyList(), itemList.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> itemList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyItemList_replacesData() {
        ItemList data = getTypicalItemList();
        itemList.resetData(data);
        assertEquals(data, itemList);
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> itemList.hasItem(null));
    }

    @Test
    public void hasItem_itemNotInItemList_returnsFalse() {
        assertFalse(itemList.hasItem(apple));
    }

    @Test
    public void hasItem_itemInItemList_returnsTrue() {
        itemList.addItem(apple);
        assertTrue(itemList.hasItem(apple));
    }

    @Test
    public void getItemList_modifyList_throwsUnsupportedOperationExpcetion() {
        assertThrows(UnsupportedOperationException.class, () -> itemList.getItemList().remove(0));
    }

    @Test
    public void deleteItem_itemInItemList_returnsTrue() {
        itemList.addItem(apple);
        itemList.deleteItem(apple);
        assertFalse(itemList.hasItem(apple));
    }

    /**
     * A stub ReadOnlyItemList
     */
    private static class ItemListStub implements ReadOnlyItemList {
        private final ObservableList<Item> items = FXCollections.observableArrayList();

        ItemListStub(Collection<Item> items) {
            this.items.setAll(items);
        }

        @Override
        public ObservableList<Item> getItemList() {
            return items;
        }
    }
}
