package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

<<<<<<< Updated upstream:src/main/java/seedu/address/storage/AddressBookStorage.java
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.AddressBook}.
=======
import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.model.McGymmy;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;

/**
 * Represents a storage for {@link McGymmy}.
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/storage/AddressBookStorage.java
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
<<<<<<< Updated upstream:src/main/java/seedu/address/storage/AddressBookStorage.java
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
=======
     * Returns McGymmy data as a {@link ReadOnlyMcGymmy}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/storage/AddressBookStorage.java
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMcGymmy> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyMcGymmy> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
<<<<<<< Updated upstream:src/main/java/seedu/address/storage/AddressBookStorage.java
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
=======
     * Saves the given {@link ReadOnlyMcGymmy} to the storage.
     *
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/storage/AddressBookStorage.java
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyMcGymmy addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyMcGymmy)
     */
    void saveAddressBook(ReadOnlyMcGymmy addressBook, Path filePath) throws IOException;

}
