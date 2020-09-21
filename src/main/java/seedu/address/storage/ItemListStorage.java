package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;


/**
 * Represents a storage for {@link seedu.address.model.ItemList}.
 */
public interface ItemListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getItemListFilePath();

    /**
     * Returns ItemList data as a {@link ReadOnlyItemList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyItemList> readItemList() throws DataConversionException, IOException;

    /**
     * @see #getItemListFilePath()
     */
    Optional<ReadOnlyItemList> readItemList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyItemList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveItemList(ReadOnlyItemList addressBook) throws IOException;

    /**
     * @see #saveItemList(ReadOnlyItemList)
     */
    void saveItemList(ReadOnlyItemList addressBook, Path filePath) throws IOException;

    /**
     * Saves the given {@link ReadOnlyLocationList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLocationList(ReadOnlyLocationList addressBook) throws IOException;

    /**
     * @see #saveLocationList(ReadOnlyLocationList)
     */
    void saveLocationList(ReadOnlyLocationList addressBook, Path filePath) throws IOException;

}
