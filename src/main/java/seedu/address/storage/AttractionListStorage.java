package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.AttractionList;
import seedu.address.model.ReadOnlyAttractionList;

/**
 * Represents a storage for {@link AttractionList}.
 */
public interface AttractionListStorage {

    /**
     * Returns the file path of the attraction list data file.
     */
    Path getAttractionListFilePath();

    /**
     * Returns AttractionList data as a {@link ReadOnlyAttractionList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAttractionList> readAttractionList() throws DataConversionException, IOException;

    /**
     * @see #getAttractionListFilePath()
     */
    Optional<ReadOnlyAttractionList> readAttractionList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAttractionList} to the storage.
     * @param attractionList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAttractionList(ReadOnlyAttractionList attractionList) throws IOException;

    /**
     * @see #saveAttractionList(ReadOnlyAttractionList)
     */
    void saveAttractionList(ReadOnlyAttractionList attractionList, Path filePath) throws IOException;

}
