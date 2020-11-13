package seedu.bagel.model;

import java.nio.file.Path;

import seedu.bagel.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBagelFilePath();

}
