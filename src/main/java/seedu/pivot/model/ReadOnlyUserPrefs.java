package seedu.pivot.model;

import java.nio.file.Path;

import seedu.pivot.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPivotFilePath();

}
