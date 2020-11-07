package seedu.simplykitchen.testutil;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * A utility class to help with building FoodInventory objects.
 * Example usage: <br>
 *     {@code FoodInventory ab = new FoodInventoryBuilder().withFood("John", "Doe").build();}
 */
public class FoodInventoryBuilder {

    private FoodInventory foodInventory;

    public FoodInventoryBuilder() {
        foodInventory = new FoodInventory();
    }

    public FoodInventoryBuilder(FoodInventory foodInventory) {
        this.foodInventory = foodInventory;
    }

    /**
     * Adds a new {@code Food} to the {@code FoodInventory} that we are building.
     */
    public FoodInventoryBuilder withFood(Food food) {
        foodInventory.addFood(food);
        return this;
    }

    public FoodInventory build() {
        return foodInventory;
    }
}
