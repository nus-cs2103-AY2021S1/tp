package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.simplykitchen.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path foodInventoryFilePath = Paths.get("data" , "foodInventory.json");
    private String sortingComparatorsDescription = "default without ordering";

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
        setFoodInventoryFilePath(newUserPrefs.getFoodInventoryFilePath());
        setSortingComparatorsDescription(newUserPrefs.getSortingComparatorsDescription());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFoodInventoryFilePath() {
        return foodInventoryFilePath;
    }

    public void setFoodInventoryFilePath(Path foodInventoryFilePath) {
        requireNonNull(foodInventoryFilePath);
        this.foodInventoryFilePath = foodInventoryFilePath;
    }

    public String getSortingComparatorsDescription() {
        return sortingComparatorsDescription;
    }

    public void setSortingComparatorsDescription(String sortingComparatorsDescription) {
        requireNonNull(sortingComparatorsDescription);
        this.sortingComparatorsDescription = sortingComparatorsDescription;
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
                && foodInventoryFilePath.equals(o.foodInventoryFilePath)
                && sortingComparatorsDescription.equals(o.sortingComparatorsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, foodInventoryFilePath, sortingComparatorsDescription);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + foodInventoryFilePath);
        sb.append("\n" + sortingComparatorsDescription);
        return sb.toString();
    }

}
