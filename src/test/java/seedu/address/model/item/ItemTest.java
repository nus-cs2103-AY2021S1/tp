package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUPPLIER_DUCK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CHICKEN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.CHICKEN;
import static seedu.address.testutil.TypicalItems.DUCK_WITH_MAX_QUANTITY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemBuilder;

public class ItemTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Item item = new ItemBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> item.getTags().remove(0));
    }

    @Test
    public void isSameItem() {
        // same object -> returns true
        assertTrue(CHICKEN.isSameItem(CHICKEN));

        // null -> returns false
        assertFalse(CHICKEN.isSameItem(null));

        // different quantity -> returns true
        Item editedChicken = new ItemBuilder(CHICKEN)
                .withQuantity(VALID_QUANTITY_DUCK)
                .build();
        assertTrue(CHICKEN.isSameItem(editedChicken));

        // different name -> returns false
        editedChicken = new ItemBuilder(CHICKEN)
                .withName(VALID_NAME_DUCK)
                .build();
        assertFalse(CHICKEN.isSameItem(editedChicken));

        // same name, same quantity, different supplier -> returns false
        editedChicken = new ItemBuilder(CHICKEN)
                .withSupplier(VALID_SUPPLIER_DUCK)
                .build();
        assertFalse(CHICKEN.isSameItem(editedChicken));

        // same name, same supplier, different quantity -> returns true
        editedChicken = new ItemBuilder(CHICKEN)
                .withQuantity(VALID_QUANTITY_DUCK)
                .build();
        assertTrue(CHICKEN.isSameItem(editedChicken));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Item chickenCopy = new ItemBuilder(CHICKEN).build();
        assertTrue(CHICKEN.equals(chickenCopy));

        // same object -> returns true
        assertTrue(CHICKEN.equals(CHICKEN));

        // null -> returns false
        assertFalse(CHICKEN.equals(null));

        // different type -> returns false
        assertFalse(CHICKEN.equals(5));

        // different Item -> returns false
        assertFalse(CHICKEN.equals(DUCK_WITH_MAX_QUANTITY));

        // different name -> returns false
        Item editedChicken = new ItemBuilder(CHICKEN).withName(VALID_NAME_DUCK).build();
        assertFalse(CHICKEN.equals(editedChicken));

        // different quantity -> returns false
        editedChicken = new ItemBuilder(CHICKEN).withQuantity(VALID_QUANTITY_DUCK).build();
        assertFalse(CHICKEN.equals(editedChicken));

        // different supplier -> returns false
        editedChicken = new ItemBuilder(CHICKEN).withSupplier(VALID_SUPPLIER_DUCK).build();
        assertFalse(CHICKEN.equals(editedChicken));

        // different tags -> returns false
        editedChicken = new ItemBuilder(CHICKEN).withTags(VALID_TAG_CHICKEN).build();
        assertFalse(CHICKEN.equals(editedChicken));
    }
}

