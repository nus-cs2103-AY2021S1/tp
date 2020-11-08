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
    private Path vendorManagerFilePath = Paths.get("data" , "vendormanager.json");
    private Path orderManagerFilePath = Paths.get("data", "presets.json");
    private Path profileManagerFilePath = Paths.get("data", "profile.json");

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
        setVendorManagerFilePath(newUserPrefs.getVendorManagerFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getVendorManagerFilePath() {
        return vendorManagerFilePath;
    }

    public void setVendorManagerFilePath(Path vendorManagerFilePath) {
        requireNonNull(vendorManagerFilePath);
        this.vendorManagerFilePath = vendorManagerFilePath;
    }

    public Path getOrderManagerFilePath() {
        return orderManagerFilePath;
    }

    public Path getProfileManagerFilePath() {
        return profileManagerFilePath;
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
                && vendorManagerFilePath.equals(o.vendorManagerFilePath)
                && orderManagerFilePath.equals(o.orderManagerFilePath)
                && profileManagerFilePath.equals(o.profileManagerFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, vendorManagerFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + vendorManagerFilePath);
        return sb.toString();
    }

}
