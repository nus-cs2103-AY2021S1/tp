package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIngredients.INGREDIENT_1;
import static seedu.address.testutil.TypicalItems.APPLE;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;

public class IngredientTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ingredient(null, null));
    }

    @Test
    public void constructor_invalidIngredient_throwsAssertionException() {
        Integer x = 1;
        Integer y = -1;

        assertThrows(AssertionError.class, () -> new Ingredient(x, y));
    }

    @Test
    public void isItem() {
        int sameItemId = 1;
        int differentItemId = 2;

        // Ensure that a known item id and equivalent int value returns true
        assertTrue(INGREDIENT_1.isItem(sameItemId));
        // Known item id and non equivalent int value returns false.
        assertFalse(INGREDIENT_1.isItem(differentItemId));
    }

    @Test
    public void testToString_success() {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(APPLE);
        ObservableList<Item> itemObservableList = FXCollections.observableList(itemArrayList);
        Ingredient ind = new Ingredient(APPLE.getId(), 5);
        assertEquals("Apple [5]", ind.toString(itemObservableList));
    }

    @Test
    public void testToString_throwsIngredientNotFoundException() {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(APPLE);
        ObservableList<Item> itemObservableList = FXCollections.observableList(itemArrayList);
        Ingredient ind = new Ingredient(APPLE.getId() + 1, 5);
        // ingredient should not be found in the list
        assertThrows(IngredientNotFoundException.class, () -> ind.toString(itemObservableList));
    }
}
