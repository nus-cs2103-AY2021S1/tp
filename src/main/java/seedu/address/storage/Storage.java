package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.preset.Preset;
import seedu.address.model.profile.Profile;
import seedu.address.model.vendor.ReadOnlyVendorManager;

/**
 * API of the Storage component
 */
public interface Storage extends VendorManagerStorage, UserPrefsStorage, PresetManagerStorage, ProfileManagerStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getVendorManagerFilePath();

    @Override
    Optional<ReadOnlyVendorManager> readVendorManager() throws DataConversionException, IOException;

    @Override
    void saveVendorManager(ReadOnlyVendorManager vendorManager) throws IOException;

    @Override
    Path getPresetManagerFilePath();

    @Override
    Optional<List<List<Preset>>> readPresetManager() throws DataConversionException, IOException;

    @Override
    void savePresetManager(List<List<Preset>> allPresets, Path filePath) throws IOException;

    @Override
    Path getProfileManagerFilePath();

    @Override
    Optional<Profile> readProfileManager() throws DataConversionException;

    @Override
    void saveProfileManager(Profile profile, Path filepath) throws IOException;

}
