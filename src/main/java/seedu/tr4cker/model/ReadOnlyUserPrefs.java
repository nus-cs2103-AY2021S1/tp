package seedu.tr4cker.model;

import java.nio.file.Path;

import seedu.tr4cker.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTr4ckerFilePath();

}
