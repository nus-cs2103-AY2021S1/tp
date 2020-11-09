package seedu.taskmaster.model;

import java.nio.file.Path;

import seedu.taskmaster.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskmasterFilePath();

    Path getSessionListFilePath();
}
