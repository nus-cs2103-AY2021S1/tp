package tp.acecs2103.model;

import java.nio.file.Path;

import tp.acecs2103.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskListFilePath();

}
