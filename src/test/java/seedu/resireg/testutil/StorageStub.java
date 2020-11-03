package seedu.resireg.testutil;


import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.storage.Storage;

/**
 * A stub class for Storage with all of the methods failing.
 */
public class StorageStub implements Storage {
    @Override
    public Path getUserPrefsFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getResiRegFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {

        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void archiveResiReg(ReadOnlyResiReg resiReg) throws IOException {
        throw new AssertionError("This method should not be called.");
    }
}
