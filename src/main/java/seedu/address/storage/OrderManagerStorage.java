package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.order.OrderItem;
import seedu.address.model.order.ReadOnlyOrderManager;

public interface OrderManagerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getOrderManagerFilePath();

    /**
     * Returns OrderManager data as a {@link ReadOnlyOrderManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<List<List<OrderItem>>> readOrderManager() throws DataConversionException, IOException;

    /**
     * @see #getOrderManagerFilePath()
     */
    Optional<List<List<OrderItem>>> readOrderManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyOrderManager} to the storage.
     * @param orderManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveOrderManager(ReadOnlyOrderManager orderManager, int index) throws IOException, DataConversionException;

    /**
     * @see #saveOrderManager(ReadOnlyOrderManager, int index)
     */
    void saveOrderManager(ReadOnlyOrderManager orderManager, Path filePath, int index) throws IOException, DataConversionException;
}
