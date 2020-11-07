package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.APPLE_PIE;
import static seedu.simplykitchen.testutil.TypicalFood.BREAD;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodBuilder;

public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void isSameFood() {
        // same object -> returns true
        assertTrue(APPLE_PIE.isSameFood(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.isSameFood(null));

        // different description -> returns false
        Food editedApplePie = new FoodBuilder(APPLE_PIE).withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(APPLE_PIE.isSameFood(editedApplePie));

        // different casing for description -> returns true
        editedApplePie = new FoodBuilder(APPLE_PIE).withDescription(VALID_DESCRIPTION_APPLE_PIE.toLowerCase()).build();
        assertTrue(APPLE_PIE.isSameFood(editedApplePie));

        // different expiry dates -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_BREAD).build();
        assertFalse(APPLE_PIE.isSameFood(editedApplePie));

        // different tags -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withTags(VALID_TAG_WHOLEMEAL).build();
        assertFalse(APPLE_PIE.isSameFood(editedApplePie));

        // different casing for tags -> returns true
        editedApplePie = new FoodBuilder(APPLE_PIE).withTags(VALID_TAG_FROZEN.toLowerCase()).build();
        assertTrue(APPLE_PIE.isSameFood(editedApplePie));

        // same description, same expiry date, different attributes -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withPriority(VALID_PRIORITY_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).withTags(VALID_TAG_WHOLEMEAL).build();
        assertFalse(APPLE_PIE.isSameFood(editedApplePie));

        // same description, same tags, different attributes -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).withPriority(VALID_PRIORITY_BREAD).build();
        assertFalse(APPLE_PIE.isSameFood(editedApplePie));

        // same description, same expiry date, same tags, different attributes -> returns true
        editedApplePie = new FoodBuilder(APPLE_PIE).withPriority(VALID_PRIORITY_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).build();
        assertTrue(APPLE_PIE.isSameFood(editedApplePie));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Food applePieCopy = new FoodBuilder(APPLE_PIE).build();
        assertTrue(APPLE_PIE.equals(applePieCopy));

        // same object -> returns true
        assertTrue(APPLE_PIE.equals(APPLE_PIE));

        // null -> returns false
        assertFalse(APPLE_PIE.equals(null));

        // different type -> returns false
        assertFalse(APPLE_PIE.equals(5));

        // different food -> returns false
        assertFalse(APPLE_PIE.equals(BREAD));

        // different description -> returns false
        Food editedApplePie = new FoodBuilder(APPLE_PIE).withDescription(VALID_DESCRIPTION_BREAD).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different casing for description -> returns true
        editedApplePie = new FoodBuilder(APPLE_PIE).withDescription(VALID_DESCRIPTION_APPLE_PIE.toLowerCase()).build();
        assertTrue(APPLE_PIE.equals(editedApplePie));

        // different priority -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withPriority(VALID_PRIORITY_BREAD).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different expiry date -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_BREAD).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different quantity -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withQuantity(VALID_QUANTITY_BREAD).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different tags -> returns false
        editedApplePie = new FoodBuilder(APPLE_PIE).withTags(VALID_TAG_WHOLEMEAL).build();
        assertFalse(APPLE_PIE.equals(editedApplePie));

        // different casing for tags -> returns true
        editedApplePie = new FoodBuilder(APPLE_PIE).withTags(VALID_TAG_FROZEN.toLowerCase()).build();
        assertTrue(APPLE_PIE.equals(editedApplePie));
    }
}
