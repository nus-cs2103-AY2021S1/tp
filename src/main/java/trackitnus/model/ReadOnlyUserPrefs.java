package trackitnus.model;

import java.nio.file.Path;

import trackitnus.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTrackIterFilePath();

}
