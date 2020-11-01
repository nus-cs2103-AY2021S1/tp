package trackitnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import trackitnus.commons.exceptions.DataConversionException;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.ReadOnlyUserPrefs;
import trackitnus.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TrackIterStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTrackIterFilePath();

    @Override
    Optional<ReadOnlyTrackIter> readTrackIter() throws DataConversionException, IOException;

    @Override
    void saveTrackIter(ReadOnlyTrackIter trackIter) throws IOException;

}
