package seedu.address.commons.core;

import static seedu.address.commons.util.FileUtil.isFileExists;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *  A Serializable class that contains the saved file path of the user.
 */
public class SavedFilePath {
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.dir");
    private static final Logger logger = LogsCenter.getLogger(SavedFilePath.class);

    private String value;

    /**
     * Constructs a {@code SavedFilePath} with the default value.
     */
    public SavedFilePath() {
        this.value = DEFAULT_FILE_PATH;
    }

    /**
     * Constructs a {@code SavedFilePath} with the given value.
     */
    @JsonCreator
    public SavedFilePath(@JsonProperty("value") String value) {
        Path path = Paths.get(value);
        if (isFileExists(path)) {
            this.value = path.toAbsolutePath().toString();
        } else {
            logger.warning("Invalid saved file path! Starting with the default file path.");
            this.value = DEFAULT_FILE_PATH;
        }
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SavedFilePath // instanceof handles nulls
                && value.equals(((SavedFilePath) other).value)); // state check
    }

    @Override
    public String toString() {
        return this.value;
    }
}
