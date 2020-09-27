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
    private Path addressBookFilePath = Paths.get("data", "addressbook.json");
    private Path itemListFilePath = Paths.get("data", "itemlist.json");
    private Path locationListFilePath = Paths.get("data", "locationlist.json");
    private Path recipeListFilePath = Paths.get("data", "recipelist.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
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
        setItemListFilePath(newUserPrefs.getItemListFilePath());
        setRecipeListFilePath(newUserPrefs.getRecipeListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getAddressBookFilePath() {
        return addressBookFilePath;
    }

    @Override
    public Path getItemListFilePath() {
        return itemListFilePath;
    }

    @Override
    public Path getLocationListFilePath() {
        return locationListFilePath;
    }

    @Override
    public Path getRecipeListFilePath() {
        return recipeListFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

    public void setItemListFilePath(Path itemListFilePath) {
        requireNonNull(itemListFilePath);
        this.itemListFilePath = itemListFilePath;
    }

    public void setRecipeListFilePath(Path recipeListFilePath) {
        requireNonNull(recipeListFilePath);
        this.recipeListFilePath = recipeListFilePath;
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
                && itemListFilePath.equals(o.itemListFilePath)
                && locationListFilePath.equals(o.locationListFilePath)
                && recipeListFilePath.equals(o.recipeListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, addressBookFilePath, itemListFilePath,
                locationListFilePath, recipeListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
        sb.append("\nLocal item data file location : " + itemListFilePath);
        sb.append("\nLocal location data file location : " + locationListFilePath);
        sb.append("\nLocal recipe data file location : " + recipeListFilePath);
        return sb.toString();
    }

}
