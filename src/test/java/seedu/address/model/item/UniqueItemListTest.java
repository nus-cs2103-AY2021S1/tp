package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.exceptions.DuplicateItemException;
import seedu.address.model.item.exceptions.ItemNotFoundException;

public class UniqueItemListTest {

    @Test
    public void contains() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);

        //Contains a expected item
        assertTrue(uList.contains(APPLE));
        // Does not contain unexpected item
        assertFalse(uList.contains(BANANA));
    }

    @Test
    public void add() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);

        assertEquals(1, uList.asUnmodifiableObservableList().size());

        // duplicate add -> duplicate error
        assertThrows(DuplicateItemException.class, () -> uList.add(APPLE));
    }

    @Test
    public void setItem() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        uList.setItem(APPLE, BANANA);
        assertTrue(uList.contains(BANANA));
        assertFalse(uList.contains(APPLE));
    }

    /**
     * Tests that setItem throws an exception when item is not found.
     */
    @Test
    public void setItem_throwsItemNotFoundException() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);

        assertThrows(ItemNotFoundException.class, () -> uList.setItem(BANANA, APPLE));
    }

    /**
     * Tests that setItem throws an exception when input is a duplicate item.
     */
    @Test
    public void setItem_throwsDuplicateItemException() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        uList.add(BANANA);

        assertThrows(DuplicateItemException.class, () -> uList.setItem(BANANA, APPLE));
    }

    @Test
    public void delete() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        uList.remove(APPLE);
        // Make sure item is not in the list
        assertFalse(uList.contains(APPLE));
    }

    @Test
    public void delete_throwsItemNotFoundException() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        assertThrows(ItemNotFoundException.class, ()-> uList.remove(BANANA));
    }

    @Test
    public void remove() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        uList.remove(APPLE);
        assertFalse(uList.contains(APPLE));
    }
    /**
     * Tests that exception is thrown when attempting to
     * remove item which does not exist.
     */

    @Test
    public void remove_throwsItemNotFoundException() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);
        assertThrows(ItemNotFoundException.class, ()-> uList.remove(BANANA));
    }

    /**
     * Tests replacing a UniqueItemList with another.
     */
    @Test
    public void setItems() {
        UniqueItemList uList = new UniqueItemList();
        UniqueItemList uList2 = new UniqueItemList();
        uList2.add(APPLE);
        uList.setItems(uList2);
        assertTrue(uList.contains(APPLE));
    }

    /**
     * Tests replacing a UniqueItemList with a list.
     */
    @Test
    public void setItems_success() {
        UniqueItemList uList = new UniqueItemList();
        ArrayList<Item> arr = new ArrayList<>();
        arr.add(APPLE);
        uList.setItems(arr);

        assertTrue(uList.contains(APPLE));
    }

    @Test
    public void setItems_throwsDuplicateItemException() {
        UniqueItemList uList = new UniqueItemList();
        ArrayList<Item> arr = new ArrayList<>();
        arr.add(APPLE);
        arr.add(APPLE);

        assertThrows(DuplicateItemException.class, () -> uList.setItems(arr));
    }

    @Test
    public void equals() {
        UniqueItemList uList = new UniqueItemList();
        uList.add(APPLE);

        // same object -> returns true
        assertTrue(uList.equals(uList));

        // null -> returns false
        assertFalse(uList.equals(null));

        // same internal lists
        UniqueItemList uList2 = new UniqueItemList();
        uList2.add(APPLE);

        assertTrue(uList.equals(uList2));

        // different internal lists
        UniqueItemList uList3 = new UniqueItemList();
        uList3.add(BANANA);

        assertFalse(uList.equals(uList3));
    }
}
