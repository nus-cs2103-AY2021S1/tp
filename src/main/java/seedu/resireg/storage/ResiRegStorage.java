package seedu.resireg.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;

/**
 * Represents a storage for {@link ResiReg}.
 */
public interface ResiRegStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getResiRegFilePath();

    /**
     * Returns ResiReg data as a {@link ReadOnlyResiReg}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException;

    /**
     * @see #getResiRegFilePath()
     */
    Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyResiReg} to the storage.
     *
     * @param resiReg cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveResiReg(ReadOnlyResiReg resiReg) throws IOException;

    /**
     * @see #saveResiReg(ReadOnlyResiReg)
     */
    void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException;

}
