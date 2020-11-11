package seedu.bookmark.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.bookmark.commons.exceptions.DataConversionException;
import seedu.bookmark.model.ReadOnlyLibrary;
import seedu.bookmark.model.ReadOnlyUserPrefs;
import seedu.bookmark.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LibraryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLibraryFilePath();

    @Override
    Optional<ReadOnlyLibrary> readLibrary() throws DataConversionException, IOException;

    @Override
    void saveLibrary(ReadOnlyLibrary library) throws IOException;

}
