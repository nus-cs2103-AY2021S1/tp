package seedu.bookmark.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.bookmark.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path bookmarkFilePath = Paths.get("data" , "library.json");
    private String sortingPreference = "";

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
        setBookmarkFilePath(newUserPrefs.getBookmarkFilePath());
        setSortingPreference(newUserPrefs.getSortingPreference());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getBookmarkFilePath() {
        return bookmarkFilePath;
    }

    public void setBookmarkFilePath(Path bookmarkFilePath) {
        requireNonNull(bookmarkFilePath);
        this.bookmarkFilePath = bookmarkFilePath;
    }

    public String getSortingPreference() {
        return sortingPreference;
    }

    public void setSortingPreference(String newSortingPreference) {
        assert newSortingPreference != null;
        this.sortingPreference = newSortingPreference;
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
                && bookmarkFilePath.equals(o.bookmarkFilePath)
                && sortingPreference.equals(o.sortingPreference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, bookmarkFilePath, sortingPreference);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + bookmarkFilePath);
        sb.append("\nSorting Preference: " + sortingPreference);
        return sb.toString();
    }

}
