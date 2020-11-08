package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.menu.MenuManager;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.vendor.Vendor;

/**
 * Stub to load in a sample menu to be used in MainApp class.
 */
public class MenuItemStorage {
    /**
     * Generates a List of ReadOnlyMenuManager wrapped in Optional class
     */
    public List<Optional<ReadOnlyMenuManager>> readMenuManagers(ObservableList<Vendor> vendorObservableList) {

        List<Optional<ReadOnlyMenuManager>> menuManagers = new ArrayList<>();

        for (Vendor vendor : vendorObservableList) {
            MenuManager menuManager1 = new MenuManager(vendor.getMenu());
            menuManagers.add(Optional.of(menuManager1));
        }

        return menuManagers;
    }
}
