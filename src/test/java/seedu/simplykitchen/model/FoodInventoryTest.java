package seedu.simplykitchen.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_BREAD;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.APPLE_PIE;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.model.food.exceptions.DuplicateFoodException;
import seedu.simplykitchen.testutil.FoodBuilder;

public class FoodInventoryTest {

    private final FoodInventory foodInventory = new FoodInventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), foodInventory.getFoods());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodInventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyFoodInventory_replacesData() {
        FoodInventory newData = getTypicalFoodInventory();
        foodInventory.resetData(newData);
        assertEquals(newData, foodInventory);
    }

    @Test
    public void resetData_withDuplicateFoods_throwsDuplicateFoodException() {
        // Two foods with the same identity fields
        Food editedApplePie = new FoodBuilder(APPLE_PIE).withPriority(VALID_PRIORITY_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).build();
        List<Food> newFoods = Arrays.asList(APPLE_PIE, editedApplePie);
        FoodInventoryStub newData = new FoodInventoryStub(newFoods);

        assertThrows(DuplicateFoodException.class, () -> foodInventory.resetData(newData));
    }

    @Test
    public void hasFood_nullFood_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> foodInventory.hasFood(null));
    }

    @Test
    public void hasFood_foodNotInFoodInventory_returnsFalse() {
        assertFalse(foodInventory.hasFood(APPLE_PIE));
    }

    @Test
    public void hasFood_foodInFoodInventory_returnsTrue() {
        foodInventory.addFood(APPLE_PIE);
        assertTrue(foodInventory.hasFood(APPLE_PIE));
    }

    @Test
    public void hasFood_foodWithSameIdentityFieldsInFoodInventory_returnsTrue() {
        foodInventory.addFood(APPLE_PIE);
        Food editedApplePie = new FoodBuilder(APPLE_PIE).withPriority(VALID_PRIORITY_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).build();
        assertTrue(foodInventory.hasFood(editedApplePie));
    }

    @Test
    public void getFoodList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> foodInventory.getFoods().remove(0));
    }

    /**
     * A stub ReadOnlyFoodInventory whose foods list can violate interface constraints.
     */
    private static class FoodInventoryStub implements ReadOnlyFoodInventory {
        private final ObservableList<Food> foods = FXCollections.observableArrayList();

        FoodInventoryStub(Collection<Food> foods) {
            this.foods.setAll(foods);
        }

        @Override
        public ObservableList<Food> getFoods() {
            return foods;
        }

        @Override
        public void addListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeListener(InvalidationListener listener) {
            throw new AssertionError("This method should not be called.");
        }
    }

}
