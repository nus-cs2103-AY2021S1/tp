package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.ItemBuilder;

public class ItemTest {

    @BeforeEach
    public void setUp() {
        // set static variables back to default
        Item.setIdCounter(0);
    }

    /**
     * Test Set Recipe
     */
    @Test
    public void setItemCounter_expectedItemCounter() {
        // Default value of counter is 0
        assertEquals(Item.getIdCounter(), 0);
        // Set value of counter to some other value
        Item.setIdCounter(10);
        assertEquals(Item.getIdCounter(), 10);

        Item i = new ItemBuilder(APPLE).build();
        // After building new item, recipe counter updates
        assertEquals(Item.getIdCounter(), 11);

        Assertions.assertThrows(AssertionError.class, () -> Item.setIdCounter(-1));
    }

    /**
     * Tests that UnsupportedOperationException is thrown when
     * operation is not supported.
     */
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Item item = new ItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    }

    /**
     * Tests that creation of an edited item does not increase the id counter.
     */
    @Test
    public void testEditItemConstructor() {
        int initialIdCounter = Item.getIdCounter();
        Item editedItem = new Item(1, "x", new Quantity("1"), "x", new HashSet<Integer>(),
                new HashSet<Integer>(), new HashSet<Tag>(), true);
        int updatedIdCounter = Item.getIdCounter();
        assertEquals(initialIdCounter, updatedIdCounter);
    }

    /**
     * Tests for item equality, defined as two items having the same name.
     */
    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(APPLE.isSameItem(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameItem(null));

        // different quantity and description -> returns true
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA)
                .withDescription(VALID_ITEM_DESCRIPTION_BANANA).build();
        assertTrue(APPLE.isSameItem(editedApple));

        // different name -> returns false
        editedApple = new ItemBuilder(APPLE).withName(VALID_ITEM_NAME_BANANA).build();
        assertFalse(APPLE.isSameItem(editedApple));

        // same name, same quantity -> returns true
        editedApple = new ItemBuilder(APPLE).withDescription(VALID_ITEM_DESCRIPTION_BANANA).build();
        assertTrue(APPLE.isSameItem(editedApple));

        // same name, same description -> returns true
        editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA).build();
        assertTrue(APPLE.isSameItem(editedApple));
    }

    /**
     * Test for strict item equality, defined as two items having the exact
     * same fields.
     */
    @Test
    public void equals() {
        // same values -> returns true
        Item appleCopy = new ItemBuilder(APPLE).build();
        assertTrue(APPLE.equals(appleCopy));

        // same object -> returns true
        assertTrue(APPLE.equals(APPLE));

        // null -> returns false
        assertFalse(APPLE.equals(null));

        // different type -> returns false
        assertFalse(APPLE.equals(5));

        // different item -> returns false
        assertFalse(APPLE.equals(BANANA));

        // different name -> returns false
        Item editedApple = new ItemBuilder(APPLE).withName(VALID_ITEM_NAME_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different quantity -> returns false
        editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA).build();
        assertFalse(APPLE.equals(editedApple));

        // different description -> returns false
        editedApple = new ItemBuilder(APPLE).withDescription(VALID_ITEM_DESCRIPTION_BANANA).build();
        assertFalse(APPLE.equals(editedApple));
    }
}
