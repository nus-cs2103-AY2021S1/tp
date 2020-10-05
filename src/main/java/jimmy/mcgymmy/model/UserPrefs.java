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
<<<<<<< Updated upstream:src/main/java/seedu/address/model/UserPrefs.java
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
=======
    private Path mcGymmyFilePath = Paths.get("data", "addressbook.json");
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/UserPrefs.java

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
        setMcGymmyFilePath(newUserPrefs.getMcGymmyFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getMcGymmyFilePath() {
        return mcGymmyFilePath;
    }

    public void setMcGymmyFilePath(Path mcGymmyFilePath) {
        requireNonNull(mcGymmyFilePath);
        this.mcGymmyFilePath = mcGymmyFilePath;
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
<<<<<<< Updated upstream:src/main/java/seedu/address/model/UserPrefs.java
                && addressBookFilePath.equals(o.addressBookFilePath);
=======
            && mcGymmyFilePath.equals(o.mcGymmyFilePath);
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/UserPrefs.java
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, mcGymmyFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + mcGymmyFilePath);
        return sb.toString();
    }

}
