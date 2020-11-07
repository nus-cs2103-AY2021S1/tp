package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path covigentAppFilePath = Paths.get("data" , "covigentapp.json");
    private Path numberOfRoomsFilePath = Paths.get("data", "numberOfRooms.txt");
    private Path roomsOccupiedFilePath = Paths.get("data", "roomInformation.json");
    private Path taskFilePath = Paths.get("data", "task.json");
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
        setCovigentAppFilePath(newUserPrefs.getCovigentAppFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getCovigentAppFilePath() {
        return covigentAppFilePath;
    }

    public void setCovigentAppFilePath(Path covigentAppFilePath) {
        requireNonNull(covigentAppFilePath);
        this.covigentAppFilePath = covigentAppFilePath;
    }

    public Path getNumberOfRoomsFilePath() {
        return numberOfRoomsFilePath;
    }

    public Path getRoomsOccupiedFilePath() {
        return roomsOccupiedFilePath;
    }

    public Path getTaskOccupiedFilePath() {
        return taskFilePath;
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
                && covigentAppFilePath.equals(o.covigentAppFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, covigentAppFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + covigentAppFilePath);
        return sb.toString();
    }

}
