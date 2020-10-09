package seedu.address.model.recipe;

import static seedu.address.testutil.TypicalItems.APPLE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.exceptions.IngredientNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

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
        Ingredient ind = new Ingredient(1, 5);
        assertThrows(IngredientNotFoundException.class, () -> ind.toString(itemObservableList));
    }
}