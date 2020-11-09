package seedu.cc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.model.ReadOnlyCommonCents;
import seedu.cc.model.ReadOnlyUserPrefs;
import seedu.cc.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CommonCentsStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getCommonCentsFilePath();

    @Override
    Optional<ReadOnlyCommonCents> readCommonCents() throws DataConversionException, IOException;

    @Override
    void saveCommonCents(ReadOnlyCommonCents commonCents) throws IOException;

}
