package seedu.jarvis.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.jarvis.commons.exceptions.DataConversionException;
import seedu.jarvis.commons.util.JsonUtil;
import seedu.jarvis.model.ReadOnlyUserLogin;
import seedu.jarvis.model.UserLogin;


public class JsonUserLoginStorage implements UserLoginStorage {
    private Path filePath;

    public JsonUserLoginStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserLoginFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserLogin> readUserLogin() throws DataConversionException {
        return readUserLogin(filePath);
    }

    /**
     * Similar to {@link #readUserLogin()}
     * @param loginFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<UserLogin> readUserLogin(Path loginFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(loginFilePath, UserLogin.class);
    }

    @Override
    public void saveUserLogin(ReadOnlyUserLogin userLoginDetails) throws IOException {
        JsonUtil.saveJsonFile(userLoginDetails, filePath);
    }
}
