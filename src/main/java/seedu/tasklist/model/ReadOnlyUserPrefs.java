package seedu.tasklist.model;

import java.nio.file.Path;

import seedu.tasklist.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getProductiveNusFilePath();

}
