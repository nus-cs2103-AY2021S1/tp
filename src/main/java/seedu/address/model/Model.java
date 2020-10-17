package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

/**
 * API of a Model component
 */
public interface Model {
    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Commits the current model to the list of model states
     */
    void commit();

    /**
     * Reverts the current model to the previous one in the list of model states
     */
    void undo();

    /**
     * Changes an undone model back to the previous one
     */
    void redo();
}
