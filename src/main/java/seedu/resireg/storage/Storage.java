package seedu.resireg.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ResiRegStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getResiRegFilePath();

    @Override
    Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException;

    @Override
    void saveResiReg(ReadOnlyResiReg resiReg) throws IOException;

}
