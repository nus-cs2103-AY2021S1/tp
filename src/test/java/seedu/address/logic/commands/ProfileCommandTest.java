package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
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

public class ProfileCommandTest {

    private static final Path TYPICAL_VendorManager_FILEPATH = Paths.get
            ("src/test/data/JsonSerializableVendorManagerTest/typicalVendorsVendorManager.json");
    private static final Path TYPICAL_USERPREFS_FILEPATH = Paths.get
            ("src/test/data/JsonUserPrefsStorageTest/TypicalUserPref.json");
    private static final Path TYPICAL_PRESET_FILEPATH = Paths.get
            ("src/test/data/JsonSerializablePresetManagerTest/storagePreset.json");
    private static final Path TYPICAL_PROFILE_FILEPATH = Paths.get
            ("src/test/data/JsonProfileStorageTest/TypicalProfile.json");

    private Storage getDefaultStorage() {
        return new StorageManager(new JsonVendorManagerStorage(TYPICAL_VendorManager_FILEPATH),
                new JsonUserPrefsStorage(TYPICAL_USERPREFS_FILEPATH),
                new JsonPresetManagerStorage(TYPICAL_PRESET_FILEPATH),
                new JsonProfileManagerStorage(TYPICAL_PROFILE_FILEPATH));
    }

    private Address getDefaultAddress() {
        return new Address(CommandTestUtil.VALID_ADDRESS_BOB);
    }

    private Phone getDefaultPhone() {
        return new Phone(CommandTestUtil.VALID_PHONE_BOB);
    }

    @Test
    public void toString_profile_success() {
        ProfileCommand profileCommand = new ProfileCommand(getDefaultPhone(), getDefaultAddress());
        String expectedString = "Profile added:\nAddress: "
                + CommandTestUtil.VALID_ADDRESS_BOB
                + "\nPhone Number: "
                + CommandTestUtil.VALID_PHONE_BOB;
        Assertions.assertEquals(profileCommand.toString(), expectedString);
    }

    @Test
    public void execute_incorrectPhone_throwsException() {
        try {
            ProfileCommand profileCommand = new ProfileCommand(new Phone("1234"), getDefaultAddress());
        } catch (IllegalArgumentException e) {
            String illegalArgumentException = "java.lang.IllegalArgumentException: ";
            String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
            Assertions.assertEquals(e.toString(), illegalArgumentException + expectedMessage);
        }
    }

    @Test
    public void execute_profile_success() throws IOException {
        Model model = TypicalModel.getModelManagerWithMenu();
        Storage storage = getDefaultStorage();


        Model expectedModel = TypicalModel.getModelManagerWithMenu();

        Phone expectedPhone = getDefaultPhone();
        Address expectedAddress = getDefaultAddress();

        Storage expectedStorage = getDefaultStorage();
        Profile expectedProfile = new Profile(expectedPhone, expectedAddress);
        expectedStorage.saveProfileManager(expectedProfile);


        String expectedMessage = ProfileCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(
                new ProfileCommand(expectedPhone, expectedAddress),
                model,
                storage,
                expectedMessage,
                expectedModel
        );
    }
}
