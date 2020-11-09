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
    private Path personBookFilePath = Paths.get("data" , "personbook.json");
    private Path bookingBookFilePath = Paths.get("data" , "bookingbook.json");
    private Path roomServiceBookFilePath = Paths.get("data", "roomservicebook.json");


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
        setPersonBookFilePath(newUserPrefs.getPersonBookFilePath());
        setBookingBookFilePath(newUserPrefs.getBookingBookFilePath());

    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPersonBookFilePath() {
        return personBookFilePath;
    }

    public void setPersonBookFilePath(Path personBookFilePath) {
        requireNonNull(personBookFilePath);
        this.personBookFilePath = personBookFilePath;
    }

    public Path getBookingBookFilePath() {
        return bookingBookFilePath;
    }

    public void setBookingBookFilePath(Path bookingBookFilePath) {
        requireNonNull(bookingBookFilePath);
        this.bookingBookFilePath = bookingBookFilePath;
    }

    public Path getRoomServiceBookFilePath() {
        return roomServiceBookFilePath;
    }

    public void setRoomServiceBookFilePath(Path roomServiceBookFilePath) {
        requireNonNull(roomServiceBookFilePath);
        this.roomServiceBookFilePath = roomServiceBookFilePath;
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
                && personBookFilePath.equals(o.personBookFilePath)
                && bookingBookFilePath.equals(o.bookingBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, personBookFilePath, bookingBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location for personBook: " + personBookFilePath);
        sb.append("\nLocal data file location for bookingBook: " + bookingBookFilePath);
        return sb.toString();
    }

}
