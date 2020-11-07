package seedu.simplykitchen.model;

import java.nio.file.Path;

import seedu.simplykitchen.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFoodInventoryFilePath();

    String getSortingComparatorsDescription();

}
