package seedu.address.storage.deliverable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;
import seedu.address.storage.UserPrefsStorage;

/**
 * API of the Storage component for Deliverable
 */
public interface StorageDeliverable extends DeliverableBookStorage, UserPrefsStorage {
    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getDeliverableBookFilePath();

    @Override
    Optional<ReadOnlyDeliverableBook> readDeliverableBook() throws DataConversionException, IOException;

    @Override
    void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook) throws IOException;
}
