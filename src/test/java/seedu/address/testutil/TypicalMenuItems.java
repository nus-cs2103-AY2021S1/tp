package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.MenuManager;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalMenuItems {

    public static final MenuItem PRATA = new MenuItemBuilder().withName("Prata").withPrice(1.00)
            .withTags("classic").build();
    public static final MenuItem MILO = new MenuItemBuilder().withName("Milo").withPrice(1.50).withTags("Iced").build();
    public static final MenuItem NASI_GORENG = new MenuItemBuilder().withName("Nasi Goreng").withPrice(4.50)
            .withTags("Bestseller").build();

    // Manually added
    public static final MenuItem NUGGETS = new MenuItemBuilder().withName("Nuggets").withPrice(3.20)
            .withTags("six").build();

    // Manually added
    public static final MenuItem CHEESE_PRATA = new MenuItemBuilder().withName("Cheese Prata")
            .withPrice(1.60).withTags("cheesy").build();

    private TypicalMenuItems() {
    } // prevents instantiation

    /**
     * Returns an {@code MenuManager} with all the typical foods.
     */
    public static MenuManager getTypicalMenuManager() {
        MenuManager menuManager = new MenuManager();
        for (MenuItem item : getTypicalMenuItems()) {
            menuManager.addMenuItem(item);
        }
        return menuManager;
    }

    public static List<MenuItem> getTypicalMenuItems() {
        return new ArrayList<>(Arrays.asList(PRATA, MILO, NASI_GORENG, NUGGETS, CHEESE_PRATA));
    }
}

