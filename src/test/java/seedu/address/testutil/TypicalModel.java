package seedu.address.testutil;

import java.util.List;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.order.OrderManager;
import seedu.address.model.vendor.VendorManager;

public class TypicalModel {

    private TypicalModel() {
    } // prevents instantiation

    /**
     * Returns a {@code ModelManager} with the typical menu.
     */
    public static ModelManager getModelManagerWithMenu() {
        VendorManager vendorManager = TypicalVendors.getTypicalVendorManager();
        List<MenuManager> menuManagers = TypicalVendors.getManagers();
        ModelManager model = new ModelManager(vendorManager, new UserPrefs(), menuManagers, new OrderManager());
        model.selectVendor(0);
        return model;
    }
}
