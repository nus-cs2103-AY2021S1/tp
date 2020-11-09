package seedu.medibook.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.medibook.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path mediBookFilePath = Paths.get("data" , "medibook.json");
    private Path medicalNotesDirPath = Paths.get("data");
    private Path userAccountPath = Paths.get("data", "accounts.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

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
        setMediBookFilePath(newUserPrefs.getMediBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getMediBookFilePath() {
        return mediBookFilePath;
    }

    public void setMediBookFilePath(Path mediBookFilePath) {
        requireNonNull(mediBookFilePath);
        this.mediBookFilePath = mediBookFilePath;
    }

    public Path getMedicalNotesDirPath() {
        return medicalNotesDirPath;
    }

    public void setMedicalNotesDirPath(Path medicalNotesDirPath) {
        requireNonNull(medicalNotesDirPath);
        this.medicalNotesDirPath = medicalNotesDirPath;
    }

    public Path getUserAccountPath() {
        return userAccountPath;
    }

    public void setUserAccountPath(Path userAccountPath) {
        requireNonNull(userAccountPath);
        this.userAccountPath = userAccountPath;
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
                && mediBookFilePath.equals(o.mediBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, mediBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + mediBookFilePath);
        return sb.toString();
    }

}
