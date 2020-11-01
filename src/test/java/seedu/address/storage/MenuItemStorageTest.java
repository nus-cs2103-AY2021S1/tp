package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.MenuItem;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.testutil.TypicalVendors;

public class MenuItemStorageTest {
    @Test
    public void readValidMenuFromFile_equalsTypicalAddressBook() {
        ObservableList<Vendor> vendors = FXCollections.observableArrayList(TypicalVendors.getTypicalVendors());
        List<ObservableList<MenuItem>> managers = TypicalVendors.getMenus();
        MenuItemStorage storage = new MenuItemStorage();
        List<Optional<ReadOnlyMenuManager>> expectedManager = storage.readMenuManagers(vendors);
        List<ObservableList<MenuItem>> expectedMenus = expectedManager.stream().map(x ->
                x.get().getMenuItemList()).collect(Collectors.toList());
        assertEquals(managers, expectedMenus);
    }
}
