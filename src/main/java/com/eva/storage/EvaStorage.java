package com.eva.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.eva.commons.exceptions.DataConversionException;
import com.eva.model.ReadOnlyEvaDatabase;


/**
 * Represents a storage for {@link com.eva.model.EvaDatabase}.
 */
public interface EvaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEvaDatabaseFilePath();

    /**
     * Returns Eva data as a {@link ReadOnlyEvaDatabase}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEvaDatabase> readEvaDatabase() throws DataConversionException, IOException;

    /**
     * @see #getEvaFilePath()
     */
    Optional<ReadOnlyEvaDatabase> readEvaDatabase(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEvaDatabase} to the storage.
     * @param evaDatabase cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEvaDatabase(ReadOnlyEvaDatabase evaDatabase) throws IOException;

    /**
     * @see #saveEvaDatabase(ReadOnlyEvaDatabase)
     */
    void saveEvaDatabase(ReadOnlyEvaDatabase evaDatabase, Path filePath) throws IOException;

}
