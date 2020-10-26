package seedu.zookeep.model;

import java.nio.file.Path;

import seedu.zookeep.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getZooKeepBookFilePath();

}
