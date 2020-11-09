package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJobAddressBook;
import seedu.address.model.ReadOnlyPersonAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends PersonAddressBookStorage, JobAddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    //=========== PersonAddressBookStorage ============================================================================

    @Override
    Path getPersonAddressBookFilePath();

    @Override
    Optional<ReadOnlyPersonAddressBook> readPersonAddressBook() throws DataConversionException, IOException;

    @Override
    void savePersonAddressBook(ReadOnlyPersonAddressBook personAddressBook) throws IOException;

    //=========== JobAddressBookStorage ===============================================================================

    @Override
    Path getJobAddressBookFilePath();

    @Override
    Optional<ReadOnlyJobAddressBook> readJobAddressBook() throws DataConversionException, IOException;

    @Override
    void saveJobAddressBook(ReadOnlyJobAddressBook jobAddressBook) throws IOException;

}
