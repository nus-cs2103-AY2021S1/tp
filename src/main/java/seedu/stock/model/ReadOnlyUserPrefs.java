package seedu.stock.model;

import java.nio.file.Path;

import seedu.stock.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStockBookFilePath();

    Path getCsvFilePath();

    Path getSerialNumberSetsBookFilePath();
}
