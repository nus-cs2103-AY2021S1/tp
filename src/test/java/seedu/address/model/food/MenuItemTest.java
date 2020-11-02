package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMenuItems.CHEESE_PRATA;
import static seedu.address.testutil.TypicalMenuItems.PRATA;
import static seedu.address.testutil.TypicalMenuItems.getTypicalMenuItems;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MenuItemBuilder;


public class MenuItemTest {

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        MenuItem item = new MenuItemBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    //    }

    @Test
    public void equals() {
        getTypicalMenuItems();
        // same values -> returns true
        Food prataCopy = new MenuItemBuilder(PRATA).build();
        assertTrue(PRATA.equals(prataCopy));

        // same object -> returns true
        assertTrue(PRATA.equals(PRATA));

        // null -> returns false
        assertFalse(PRATA.equals(null));

        // different type -> returns false
        assertFalse(PRATA.equals(5));

        // different food -> returns false
        assertFalse(PRATA.equals(CHEESE_PRATA));
        // different name -> returns false
        Food editedPrata = new MenuItemBuilder(PRATA).withName("Cheese Prata").build();
        assertFalse(PRATA.equals(editedPrata));

        // different price -> returns false
        editedPrata = new MenuItemBuilder(PRATA).withPrice(2.3).build();
        assertFalse(PRATA.equals(editedPrata));

        // different tags -> returns false
        editedPrata = new MenuItemBuilder(PRATA).withTags("false").build();
        assertFalse(PRATA.equals(editedPrata));
    }

    @Test
    public void getPriceString() {
        assertEquals(PRATA.getPriceString(), "$1.00");
        assertNotEquals(PRATA.getPriceString(), "$1.20");
    }

    @Test
    public void invalidPriceTest() {
        assertFalse(Food.isValidPrice(-2));

        assertTrue(Food.isValidPrice(2));
        assertTrue(Food.isValidPrice(2.54));
        assertTrue(Food.isValidPrice(1.2));

        // Price should have only 2 decimal points
        assertFalse(Food.isValidPrice(2.555));
    }
}
