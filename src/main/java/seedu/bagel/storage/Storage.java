package seedu.bagel.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.bagel.commons.exceptions.DataConversionException;
import seedu.bagel.model.ReadOnlyBagel;
import seedu.bagel.model.ReadOnlyUserPrefs;
import seedu.bagel.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends BagelStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getBagelFilePath();

    @Override
    Optional<ReadOnlyBagel> readBagel() throws DataConversionException, IOException;

    @Override
    void saveBagel(ReadOnlyBagel bagel) throws IOException;

}
