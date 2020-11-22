package seedu.forgetfulnus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.forgetfulnus.commons.exceptions.DataConversionException;
import seedu.forgetfulnus.model.ReadOnlyGlossary;
import seedu.forgetfulnus.model.ReadOnlyScoreList;
import seedu.forgetfulnus.model.ReadOnlyUserPrefs;
import seedu.forgetfulnus.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    Path getGlossaryFilePath();

    Optional<ReadOnlyGlossary> readGlossary() throws DataConversionException, IOException;

    Optional<ReadOnlyGlossary> readGlossary(Path filePath) throws DataConversionException, IOException;

    void saveGlossary(ReadOnlyGlossary glossary) throws IOException;

    void saveGlossary(ReadOnlyGlossary glossary, Path filePath) throws IOException;

    Path getScoreFilePath();

    Optional<ReadOnlyScoreList> readScores() throws DataConversionException, IOException;

    Optional<ReadOnlyScoreList> readScores(Path filePath) throws DataConversionException, IOException;

    void saveScores(ReadOnlyScoreList scoreList) throws IOException;

    void saveScores(ReadOnlyScoreList scoreList, Path filePath) throws IOException;

}
