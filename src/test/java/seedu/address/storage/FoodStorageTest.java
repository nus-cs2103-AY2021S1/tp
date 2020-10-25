package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.food.Food;
import seedu.address.model.menu.ReadOnlyMenuManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.testutil.TypicalVendors;

public class FoodStorageTest {
    @Test
    public void readValidMenuFromFile_equalsTypicalAdressBook() {
        ObservableList<Vendor> vendors = FXCollections.observableArrayList(TypicalVendors.getTypicalVendors());
        List<ObservableList<Food>> managers = TypicalVendors.getMenuLists();
        FoodStorage storage = new FoodStorage();
        List<Optional<ReadOnlyMenuManager>> expectedManager = storage.readMenuManagers(vendors);
        List<ObservableList<Food>> expectedMenus = expectedManager.stream().map(x ->
                x.get().getFoodList()).collect(Collectors.toList());
        assertEquals(managers, expectedMenus);
    }
}
