package seedu.internhunter.model;

import java.nio.file.Path;

import seedu.internhunter.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /**
     * Retrieves the user prefs' GUI settings.
     *
     * @return the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Retrieves the application item list file path.
     *
     * @return the application item list file path.
     */
    Path getApplicationItemListFilePath();

    /**
     * Retrieves the company item list file path.
     *
     * @return the company item list file path.
     */
    Path getCompanyItemListFilePath();

    /**
     * Retrieves the profile item list file path.
     *
     * @return the profile item list file path.
     */
    Path getProfileItemListFilePath();

}
