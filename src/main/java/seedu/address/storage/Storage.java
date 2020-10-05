package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

<<<<<<< Updated upstream:src/main/java/seedu/address/storage/Storage.java
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
=======
import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;
import jimmy.mcgymmy.model.ReadOnlyUserPrefs;
import jimmy.mcgymmy.model.UserPrefs;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/storage/Storage.java

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyMcGymmy> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyMcGymmy addressBook) throws IOException;

}
