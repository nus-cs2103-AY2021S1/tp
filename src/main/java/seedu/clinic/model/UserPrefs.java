package seedu.clinic.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.clinic.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path clinicFilePath = Paths.get("data" , "clinic.json");
    private Path userMacrosFilePath = Paths.get("data" , "userMacros.json");
    private Path commandHistoryFilePath = Paths.get("data" , "commandHistory.txt");

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
        setClinicFilePath(newUserPrefs.getClinicFilePath());
        setUserMacrosFilePath(newUserPrefs.getUserMacrosFilePath());
        setCommandHistoryFilePath(newUserPrefs.getCommandHistoryFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getUserMacrosFilePath() {
        return userMacrosFilePath;
    }

    public void setUserMacrosFilePath(Path userMacrosFilePath) {
        requireNonNull(userMacrosFilePath);
        this.userMacrosFilePath = userMacrosFilePath;
    }

    public Path getClinicFilePath() {
        return clinicFilePath;
    }

    public void setClinicFilePath(Path clinicFilePath) {
        requireNonNull(clinicFilePath);
        this.clinicFilePath = clinicFilePath;
    }

    @Override
    public Path getCommandHistoryFilePath() {
        return commandHistoryFilePath;
    }

    public void setCommandHistoryFilePath(Path commandHistoryFilePath) {
        requireNonNull(commandHistoryFilePath);
        this.commandHistoryFilePath = commandHistoryFilePath;
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
                && clinicFilePath.equals(o.clinicFilePath)
                && userMacrosFilePath.equals(o.userMacrosFilePath)
                && commandHistoryFilePath.equals(o.commandHistoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, clinicFilePath, userMacrosFilePath, commandHistoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nClinic data file location : " + clinicFilePath);
        sb.append("\nUser macros file location : " + userMacrosFilePath);
        sb.append("\nCommand history file location : " + commandHistoryFilePath);
        return sb.toString();
    }

}
