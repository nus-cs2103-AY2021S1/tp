package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.schedule.ScheduleStorage;

/**
 * API of the Storage component
 */
public interface Storage extends ReeveStorage, UserPrefsStorage, ScheduleStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyReeve> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyReeve addressBook) throws IOException;


}
