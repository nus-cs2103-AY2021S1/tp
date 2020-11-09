package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.profile.Profile;

public class JsonProfileManagerStorage implements ProfileManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProfileManagerStorage.class);

    private Path filePath;

    public JsonProfileManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProfileManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<Profile> readProfileManager() throws DataConversionException {
        return readProfileManager(filePath);
    }

    /**
     * Similar to {@link #readProfileManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<Profile> readProfileManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProfileManager> jsonProfileManager = JsonUtil.readJsonFile(
                filePath,
                JsonSerializableProfileManager.class
        );
        if (!jsonProfileManager.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(jsonProfileManager.get().toModelType());
    }

    @Override
    public void saveProfileManager(Profile profile) throws IOException {
        saveProfileManager(profile, filePath);
    }

    @Override
    public void saveProfileManager(Profile profile, Path filePath) throws IOException {
        requireNonNull(profile);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProfileManager(profile), filePath);
    }
}
