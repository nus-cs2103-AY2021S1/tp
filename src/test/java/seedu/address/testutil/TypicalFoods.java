package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalFoods {

    public static final Food PRATA = new FoodBuilder().withName("Prata").withPrice(1.00).withTags("classic").build();
    public static final Food MILO = new FoodBuilder().withName("Milo").withPrice(1.50).withTags("Iced").build();
    public static final Food NASI_GORENG = new FoodBuilder().withName("Nasi Goreng").withPrice(4.50)
            .withTags("Bestseller").build();

    // Manually added
    public static final Food NUGGETS = new FoodBuilder().withName("Nuggets").withPrice(3.20).withTags("six").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Food CHEESE_PRATA = new FoodBuilder().withName("Cheese Prata")
            .withPrice(1.60).withTags("cheesy").build();

    private TypicalFoods() {
    } // prevents instantiation

    /**
     * Returns an {@code MenuManager} with all the typical foods.
     */
    public static MenuManager getTypicalMenuManager() {
        MenuManager menuManager = new MenuManager();
        for (Food food : getTypicalFoods()) {
            menuManager.addFood(food);
        }
        return menuManager;
    }

    public static List<Food> getTypicalFoods() {
        return new ArrayList<>(Arrays.asList(PRATA, MILO, NASI_GORENG, NUGGETS, CHEESE_PRATA));
    }
}

