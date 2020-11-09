package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.menu.ReadOnlyMenuManager;

/**
 * Represents a storage for {@link seedu.address.model.menu.MenuManager}.
 */
public interface MenuManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMenuManagerFilePath();

    /**
     * Returns MenuManager data as a {@link ReadOnlyMenuManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMenuManager> readMenuManager() throws DataConversionException, IOException;

    /**
     * @see #getMenuManagerFilePath()
     */
    Optional<ReadOnlyMenuManager> readMenuManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMenuManager} to the storage.
     * @param menuManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMenuManager(ReadOnlyMenuManager menuManager) throws IOException;

    /**
     * @see #saveMenuManager(ReadOnlyMenuManager)
     */
    void saveMenuManager(ReadOnlyMenuManager menuManager, Path filePath) throws IOException;

}
