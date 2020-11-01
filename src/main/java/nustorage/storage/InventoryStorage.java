package nustorage.storage;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import nustorage.commons.exceptions.DataConversionException;
import nustorage.model.Inventory;
import nustorage.model.ReadOnlyInventory;


/**
 * A storage interface for {@link nustorage.model.Inventory}
 */
public interface InventoryStorage {

    /**
     * @return file path to json data file.
     */
    Path getInventoryFilePath();


    /**
     * Read inventory from json storage file.
     *
     * @return InventoryWindow data, or {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInventory> readInventory() throws DataConversionException, IOException;


    /**
     * @param filePath overrides default storage filepath.
     * @see #readInventory()
     */
    Optional<ReadOnlyInventory> readInventory(Path filePath) throws DataConversionException, IOException;


    /**
     * Saves the given {@link Inventory} to storage in Json format.
     *
     * @param inventory given inventory to be stored, cannot be null.
     * @throws IOException if there's any problems writing to file.
     */
    void saveInventory(ReadOnlyInventory inventory) throws IOException;


    /**
     * @param filePath overrides default storage filepath.
     * @see #saveInventory(ReadOnlyInventory, Path)
     */
    void saveInventory(ReadOnlyInventory inventory, Path filePath) throws IOException;

}

