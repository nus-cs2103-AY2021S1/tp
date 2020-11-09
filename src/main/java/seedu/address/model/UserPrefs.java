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
    private Path attractionListFilePath = Paths.get("data" , "attractionlist.json");
    private Path itineraryListFilePath = Paths.get("data" , "itinerarylist.json");

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
        setAttractionListFilePath(newUserPrefs.getAttractionListFilePath());
        setItineraryListFilePath(newUserPrefs.getItineraryListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getAttractionListFilePath() {
        return attractionListFilePath;
    }

    public void setAttractionListFilePath(Path attractionListFilePath) {
        requireNonNull(attractionListFilePath);
        this.attractionListFilePath = attractionListFilePath;
    }

    public Path getItineraryListFilePath() {
        return itineraryListFilePath;
    }

    public void setItineraryListFilePath(Path itineraryListFilePath) {
        requireNonNull(itineraryListFilePath);
        this.itineraryListFilePath = itineraryListFilePath;
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
                && attractionListFilePath.equals(o.attractionListFilePath)
                && itineraryListFilePath.equals(o.itineraryListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, attractionListFilePath, itineraryListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal attraction data file location : " + attractionListFilePath);
        sb.append("\nLocal itinerary data file location : " + itineraryListFilePath);
        return sb.toString();
    }

}
