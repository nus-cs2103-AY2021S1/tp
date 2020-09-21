package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemTest {

    /**
     * test for unsupported operation
     */
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Item item = new ItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    }

    /**
     * test for same item
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
     * equals test
     */
    @Test
    public void equals() {
        // same values -> returns true
        Item aliceCopy = new ItemBuilder(APPLE).build();
        assertTrue(APPLE.equals(aliceCopy));

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
