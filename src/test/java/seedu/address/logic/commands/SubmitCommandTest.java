package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.profile.Profile;
import seedu.address.storage.*;
import seedu.address.testutil.TypicalModel;
import seedu.address.testutil.TypicalVendors;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SubmitCommandTest {

    final static Path TYPICAL_ADDRESSBOOK_FILEPATH = Paths.get
            ("src/data/JsonSerializableAddressBookTest/typicalVendorsAddressBook.json");
    final static Path TYPICAL_USERPREFS_FILEPATH = Paths.get
            ("src/data/JsonUserPrefsStorageTest/TypicalUserPref.json");
    final static Path TYPICAL_PRESET_FILEPATH = Paths.get
            ("src/data/JsonSerializablePresetManagerTest/storagePreset.json");
    final static Path TYPICAL_PROFILE_FILEPATH = Paths.get
            ("src/data/JsonProfileStorageTest/TypicalProfile.json");

    public static Storage getDefaultStorage() {
        return new StorageManager(new JsonAddressBookStorage(TYPICAL_ADDRESSBOOK_FILEPATH),
                new JsonUserPrefsStorage(TYPICAL_USERPREFS_FILEPATH),
                new JsonPresetManagerStorage(TYPICAL_PRESET_FILEPATH),
                new JsonProfileManagerStorage(TYPICAL_PROFILE_FILEPATH));
    }

    @Test
    public void execute_submit_success() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        Storage storage = getDefaultStorage();
        storage.saveProfileManager(new Profile("Block 123, Bobby Street 3", "22222222"));
        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();

        StringBuilder expectedMessage = new StringBuilder();
        String expectedAddress = CommandTestUtil.VALID_ADDRESS_BOB;
        String expectedPhone = CommandTestUtil.VALID_PHONE_BOB;

        expectedMessage.append(String.format("Address: %s\n", expectedAddress));
        expectedMessage.append(String.format("Phone: %s\n", expectedPhone));
        expectedMessage.append("---------------------------------\n");

        double calculatedTotal = 0;
        for (int i = 0; i < 5; i++) {
            OrderItem orderItem = new OrderItem(menu.get(i), i + 6);
            expectedMessage.append(String.format("%s x %d\n", orderItem.getName(), i + 6));
            calculatedTotal += orderItem.getPrice() * (i + 6);
            try {
                model.addOrderItem(orderItem);
                expectedModel.addOrderItem(orderItem);
            } catch (CommandException e) {
                Assertions.assertTrue(false);
            }
        }
        expectedMessage.append(String.format("Estimated total: $%.2f\n", calculatedTotal));

        // Code broken here (I think because StorageManager doesn't have a Profile)
        assertCommandSuccess(new SubmitCommand(), model, storage, expectedMessage.toString(), expectedModel);
    }
}
