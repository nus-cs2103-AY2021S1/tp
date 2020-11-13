package seedu.jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.jarvis.commons.exceptions.DataConversionException;
import seedu.jarvis.model.ReadOnlyAddressBook;
import seedu.jarvis.model.ReadOnlyUserLogin;
import seedu.jarvis.model.ReadOnlyUserPrefs;
import seedu.jarvis.model.UserLogin;
import seedu.jarvis.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage, UserLoginStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Optional<UserLogin> readUserLogin() throws DataConversionException, IOException;

    @Override
    void saveUserLogin(ReadOnlyUserLogin userLogin) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

}
