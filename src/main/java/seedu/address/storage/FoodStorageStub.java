package seedu.address.storage;

import java.util.HashSet;
import java.util.Optional;

import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;

/**
 * Stub to load in a sample menu to be used in MainApp class.
 */
public class FoodStorageStub {
    public static final Food EGG_PRATA = new Food("Egg Prata", 1.20, new HashSet<>());
    public static final Food CHEESE_PRATA = new Food("Cheese Prata", 1, new HashSet<>());

    /**
     * Generates an ReadOnlyMenuManager wrapped in Optional class
     */
    public Optional<ReadOnlyMenuManager> readMenuManager() {
        MenuManager menuManager = new MenuManager();
        menuManager.addFood(EGG_PRATA);
        menuManager.addFood(CHEESE_PRATA);
        return Optional.of(menuManager);
    }
}
