package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import seedu.address.model.food.Food;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;

/**
 * Stub to load in a sample menu to be used in MainApp class.
 */
public class FoodStorageStub {
    public static final Food EGG_PRATA_1 = new Food("Egg Prata", 1.20, new HashSet<>());
    public static final Food CHEESE_PRATA_1 = new Food("Cheese Prata", 1, new HashSet<>());
    public static final Food MILO_1 = new Food("Milo", 1.5, new HashSet<>());

    public static final Food PLAIN_PRATA_2 = new Food("Plain Prata", 0.9, new HashSet<>());
    public static final Food CHEESE_PRATA_2 = new Food("Cheese Prata", 1.8, new HashSet<>());

    public static final Food NASI_GORENG = new Food("Nasi Goreng", 4.5, new HashSet<>());
    /**
     * Generates a List of ReadOnlyMenuManager wrapped in Optional class
     */
    public List<Optional<ReadOnlyMenuManager>> readMenuManagers() {
        MenuManager menuManager1 = new MenuManager();
        menuManager1.addFood(EGG_PRATA_1);
        menuManager1.addFood(CHEESE_PRATA_1);
        menuManager1.addFood(MILO_1);

        MenuManager menuManager2 = new MenuManager();
        menuManager2.addFood(PLAIN_PRATA_2);
        menuManager2.addFood(CHEESE_PRATA_2);
        menuManager2.addFood(NASI_GORENG);

        List<Optional<ReadOnlyMenuManager>> menuManagers = new ArrayList<>();
        menuManagers.add(Optional.of(menuManager1));
        menuManagers.add(Optional.of(menuManager2));

        return menuManagers;
    }
}
