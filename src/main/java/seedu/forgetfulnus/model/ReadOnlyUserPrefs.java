package seedu.forgetfulnus.model;

import java.nio.file.Path;

import seedu.forgetfulnus.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getGlossaryFilePath();

    Path getScoresFilePath();

}
