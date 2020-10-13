package seedu.expense.model;

import java.nio.file.Path;

import seedu.expense.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getExpenseBookFilePath();

}
