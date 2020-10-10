package seedu.address.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFoods.CHEESE_PRATA;
import static seedu.address.testutil.TypicalFoods.PRATA;
import static seedu.address.testutil.TypicalFoods.getTypicalFoods;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.FoodBuilder;


public class FoodTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Food food = new FoodBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> food.getTags().remove(0));
    }

    @Test
    public void equals() {
        getTypicalFoods();
        // same values -> returns true
        Food prataCopy = new FoodBuilder(PRATA).build();
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
        Food editedPrata = new FoodBuilder(PRATA).withName("Cheese Prata").build();
        assertFalse(PRATA.equals(editedPrata));

        // different price -> returns false
        editedPrata = new FoodBuilder(PRATA).withPrice(2.3).build();
        assertFalse(PRATA.equals(editedPrata));

        // different tags -> returns false
        editedPrata = new FoodBuilder(PRATA).withTags("false").build();
        assertFalse(PRATA.equals(editedPrata));
    }
}
