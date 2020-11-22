package nustorage.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import nustorage.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path financeAccountFilePath = Paths.get("data", "financeAccount.json");
    private Path inventoryFilePath = Paths.get("data", "inventory.json");

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
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getInventoryFilePath() {
        return inventoryFilePath;
    }

    public void setInventoryFilePath(Path inventoryFilePath) {
        this.inventoryFilePath = inventoryFilePath;
    }

    public Path getFinanceAccountFilePath() {
        return financeAccountFilePath;
    }

    public void setFinanceAccountFilePath(Path financeAccountFilePath) {
        this.financeAccountFilePath = financeAccountFilePath;
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
                && inventoryFilePath.equals(o.inventoryFilePath)
                && financeAccountFilePath.equals(o.financeAccountFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, inventoryFilePath, financeAccountFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal inventory data file location : " + inventoryFilePath);
        sb.append("\nLocal finance data file location : " + financeAccountFilePath);
        return sb.toString();
    }

}
