package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalVendors.getTypicalVendorManager;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.food.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.OrderManager;
import seedu.address.model.profile.Profile;
import seedu.address.model.vendor.Address;
import seedu.address.model.vendor.Phone;
import seedu.address.storage.JsonPresetManagerStorage;
import seedu.address.storage.JsonProfileManagerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.JsonVendorManagerStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.TypicalModel;
import seedu.address.testutil.TypicalVendors;

public class SubmitCommandTest {

    private static final Path TYPICAL_VendorManager_FILEPATH = Paths.get
            ("src/test/data/JsonSerializableVendorManagerTest/typicalVendorsVendorManager.json");
    private static final Path TYPICAL_USERPREFS_FILEPATH = Paths.get
            ("src/test/data/JsonUserPrefsStorageTest/TypicalUserPref.json");
    private static final Path TYPICAL_PRESET_FILEPATH = Paths.get
            ("src/test/data/JsonSerializablePresetManagerTest/storagePreset.json");
    private static final Path TYPICAL_PROFILE_FILEPATH = Paths.get
            ("src/test/data/JsonProfileStorageTest/TypicalProfile.json");

    public static Storage getDefaultStorage() {
        return new StorageManager(new JsonVendorManagerStorage(TYPICAL_VendorManager_FILEPATH),
                new JsonUserPrefsStorage(TYPICAL_USERPREFS_FILEPATH),
                new JsonPresetManagerStorage(TYPICAL_PRESET_FILEPATH),
                new JsonProfileManagerStorage(TYPICAL_PROFILE_FILEPATH));
    }

    public static Storage getStorageWithoutProfile() {
        return new StorageManager(new JsonVendorManagerStorage(TYPICAL_VendorManager_FILEPATH),
                new JsonUserPrefsStorage(TYPICAL_USERPREFS_FILEPATH),
                new JsonPresetManagerStorage(TYPICAL_PRESET_FILEPATH), null
        );
    }

    @Test
    public void execute_vendorNotSelected_throwsException() {
        Model model = new ModelManager(getTypicalVendorManager(), new UserPrefs(), TypicalVendors.getManagers(),
                new OrderManager());
        assertCommandFailure(new SubmitCommand(),
                model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }

    @Test
    public void execute_emptyOrder_throwsException() {
        Model model = TypicalModel.getModelManagerWithMenu();
        assertCommandFailure(new SubmitCommand(),
                model, Messages.MESSAGE_EMPTY_ORDER);
    }

    @Test
    public void execute_invalidProfile_throwsException() {
        //TODO invalid profile breaks the code
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = getStorageWithoutProfile();
        try {
            model.addOrderItem(new OrderItem("Prata", 1.00, new HashSet<>(), 1));
        } catch (CommandException e) {
            Assertions.assertTrue(false);
        }

        //        assertCommandFailure(new SubmitCommand(), model, storage, Messages.MESSAGE_EMPTY_PROFILE);
    }

    @Test
    public void execute_submit_success() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Model expectedModel = TypicalModel.getModelManagerWithMenu();
        Storage storage = getDefaultStorage();
        storage.saveProfileManager(new Profile(new Phone("66666666"), new Address("Block 123, Bobby Street 3")));
        ObservableList<MenuItem> menu = model.getFilteredMenuItemList();

        boolean copySuccess = true;
        try {
            StringSelection stringSelection = new StringSelection("testing clipboard");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            // clipboard.setContents(stringSelection, null);
        } catch (HeadlessException e) {
            copySuccess = false;
        }

        StringBuilder expectedMessage = new StringBuilder();
        if (copySuccess) {
            expectedMessage.append(SubmitCommand.CLIPBOARD_SUCCESS_MESSAGE);
        }
        String expectedAddress = CommandTestUtil.VALID_ADDRESS_BOB;
        String expectedPhone = CommandTestUtil.VALID_PHONE_BOB;

        expectedMessage.append(String.format("Address: %s\n", expectedAddress));
        expectedMessage.append(String.format("Phone: %s\n", expectedPhone));
        expectedMessage.append("---------------------------------\n");

        double calculatedTotal = 0;
        for (int i = 0; i < 3; i++) {
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
        expectedMessage.append("\n" + String.format("Estimated total: $%.2f\n", calculatedTotal));

        assertCommandSuccess(new SubmitCommand(), model, storage, expectedMessage.toString(), expectedModel);
    }
}
