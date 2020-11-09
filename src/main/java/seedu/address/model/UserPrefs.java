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
    private Path clientListFilePath = Paths.get("data" , "clientlist.json");
    private Path policyListFilePath = Paths.get("data", "policylist.json");

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
        setClientListFilePath(newUserPrefs.getClientListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getClientListFilePath() {
        return clientListFilePath;
    }

    public void setClientListFilePath(Path clientListFilePath) {
        requireNonNull(clientListFilePath);
        this.clientListFilePath = clientListFilePath;
    }

    public Path getPolicyListFilePath() {
        return policyListFilePath;
    }

    public void setPolicyListFilePath(Path policyListFilePath) {
        requireNonNull((policyListFilePath));
        this.policyListFilePath = policyListFilePath;
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
                && clientListFilePath.equals(o.clientListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, clientListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + clientListFilePath);
        return sb.toString();
    }

}
