package tp.cap5buddy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import tp.cap5buddy.modules.ModuleList;



/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
 */
public interface ModuleListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModuleListFilePath();

    /**
     * Returns ModuleList data as a {@link ModuleList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ModuleList> readModuleList() throws DataConversionException, IOException;

    /**
     * @see #getModuleListFilePath()
     */
    Optional<ModuleList> readModuleList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ModuleList} to the storage.
     * @param moduleList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModuleList(ModuleList moduleList) throws IOException;

    /**
     * @see #saveModuleList(ModuleList)
     */
    void saveModuleList(ModuleList moduleList, Path filePath) throws IOException;

}
