package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.profile.Profile;

public interface ProfileManagerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getProfileManagerFilePath();

    /**
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<Profile> readProfileManager() throws DataConversionException;

    /**
     * @see #getProfileManagerFilePath()
     */
    Optional<Profile> readProfileManager(Path filePath) throws DataConversionException;

    void saveProfileManager(Profile profile) throws IOException;

    void saveProfileManager(Profile profile, Path filePath) throws IOException;

}
