package quickcache.model;

import java.nio.file.Path;

import quickcache.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getQuickCacheFilePath();

}
