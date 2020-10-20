package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderManager;

import java.util.ArrayList;
import java.util.List;

public class TypicalModel {

    private TypicalModel() {
    } // prevents instantiation

    /**
     * Returns a {@code ModelManager} with the typical menu.
     */
    public static ModelManager getModelManagerWithMenu() {
        List<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(TypicalFoods.getTypicalMenuManager());
        return new ModelManager(new AddressBook(), new UserPrefs(), menuManagers, new OrderManager());
    }
}
