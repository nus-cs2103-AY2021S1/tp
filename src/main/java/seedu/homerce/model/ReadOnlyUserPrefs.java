package seedu.homerce.model;

import java.nio.file.Path;

import seedu.homerce.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getClientManagerFilePath();

    Path getAppointmentStorageFilePath();

    Path getServiceStorageFilePath();

    Path getRevenueStorageFilePath();
}
