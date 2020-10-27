package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderManager;

public class TypicalModel {

    private TypicalModel() {
    } // prevents instantiation

    /**
     * Returns a {@code ModelManager} with the typical menu.
     */
    public static ModelManager getModelManagerWithMenu() {
        List<MenuManager> menuManagers = new ArrayList<>();
        menuManagers.add(TypicalFoods.getTypicalMenuManager());
        AddressBook addressBook = new AddressBook();
        ModelManager model = new ModelManager(addressBook, new UserPrefs(), menuManagers, new OrderManager());
        model.selectVendor(0);
        return model;
    }
}
