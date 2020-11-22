package seedu.forgetfulnus.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.forgetfulnus.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path glossaryFilePath = Paths.get("data" , "glossary.json");
    private Path scoresFilePath = Paths.get("data" , "scores.json");

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
        setGlossaryFilePath(newUserPrefs.getGlossaryFilePath());
        setScoresFilePath(newUserPrefs.getScoresFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getGlossaryFilePath() {
        return glossaryFilePath;
    }

    @Override
    public Path getScoresFilePath() {
        return scoresFilePath;
    }

    public void setGlossaryFilePath(Path glossaryFilePath) {
        requireNonNull(glossaryFilePath);
        this.glossaryFilePath = glossaryFilePath;
    }

    public void setScoresFilePath(Path scoresFilePath) {
        requireNonNull(scoresFilePath);
        this.scoresFilePath = scoresFilePath;
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
                && glossaryFilePath.equals(o.glossaryFilePath)
                && scoresFilePath.equals(o.scoresFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, glossaryFilePath, scoresFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + glossaryFilePath);
        sb.append("\nLocal scores file location : " + scoresFilePath);
        return sb.toString();
    }

}
