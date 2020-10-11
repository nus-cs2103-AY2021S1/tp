package seedu.address.storage;

import java.io.IOException;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.deliverymodel.ReadOnlyDeliveryBook;

public interface DeliveryBookStorage {
    // TODO

    /**
     * Returns DeliveryBook data as a {@link ReadOnlyDeliveryBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDeliveryBook> readDeliveryBook() throws DataConversionException, IOException;
}
