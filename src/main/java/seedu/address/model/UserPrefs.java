package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.FileUtil.isFileExists;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.currentpath.CurrentPath;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
    private Path savedFilePath = Paths.get(CurrentPath.getInstance().getAddress());
    private static final Logger logger = LogsCenter.getLogger(UserPrefs.class);

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    @JsonCreator
    public UserPrefs(@JsonProperty("guiSettings") GuiSettings guiSettings,
                     @JsonProperty("addressBookFilePath") String addressBookFilePath,
                     @JsonProperty("savedFilePath") String savedFilePath) {
        this.guiSettings = guiSettings;
        this.addressBookFilePath = Paths.get(addressBookFilePath);

        Path savedPath = Paths.get(savedFilePath);
        System.out.println("Saved Path :" + savedPath);

        if (isFileExists(savedPath)) {
            this.savedFilePath = savedPath;
            CurrentPath.getInstance().setAddress(savedPath.toAbsolutePath().toString());
        } else {
            logger.info("Invalid saved file path! Starting with the default file path.");
            this.savedFilePath = Paths.get(CurrentPath.getInstance().getAddress());
        }
    }

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
        CurrentPath.getInstance().setAddress(savedFilePath.toString());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public Path getSavedFilePath() {
        return savedFilePath;
    }

    public void setSavedFilePath(Path savedFilePath) {
        this.savedFilePath = savedFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && addressBookFilePath.equals(o.addressBookFilePath)
                && savedFilePath.equals(o.savedFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nSaved file path : " + savedFilePath);
        return sb.toString();
    }

}
