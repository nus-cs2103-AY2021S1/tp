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
    private Path addressBookFilePath = Paths.get("data" , "addressbook.json");
<<<<<<< HEAD
    private Path salesBookFilePath = Paths.get("data" , "salesbook.json");
=======
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
    private Path ingredientBookFilePath = Paths.get("data" , "ingredientbook.json");

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
        setAddressBookFilePath(newUserPrefs.getAddressBookFilePath());
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

<<<<<<< HEAD
    public Path getSalesBookFilePath() {
        return salesBookFilePath;
    }

=======
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
    public Path getIngredientBookFilePath() {
        return ingredientBookFilePath;
    }

    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.addressBookFilePath = addressBookFilePath;
    }

<<<<<<< HEAD

    public void setSalesBookFilePath(Path salesBookFilePath) {
        requireNonNull(salesBookFilePath);
        this.salesBookFilePath = salesBookFilePath;
    }

=======
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
    public void setIngredientBookFilePath(Path ingredientBookFilePath) {
        requireNonNull(ingredientBookFilePath);
        this.ingredientBookFilePath = ingredientBookFilePath;
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
<<<<<<< HEAD
                && salesBookFilePath.equals(o.salesBookFilePath)
=======
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
                && ingredientBookFilePath.equals(o.ingredientBookFilePath);
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(guiSettings, addressBookFilePath, salesBookFilePath, ingredientBookFilePath);
=======
        return Objects.hash(guiSettings, addressBookFilePath, ingredientBookFilePath);
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + addressBookFilePath);
<<<<<<< HEAD
        sb.append("\nLocal data file location : " + salesBookFilePath);
        sb.append("\nLocal data file location : " + ingredientBookFilePath);

=======
        sb.append("\nLocal data file location : " + ingredientBookFilePath);
>>>>>>> 352641ac0dadb52cd407beec806d2b44fbabc0f2
        return sb.toString();
    }

}