package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getPropertyBookFilePath();

    Path getBidBookFilePath();

    Path getSellerAddressBookFilePath();

    Path getBidderAddressBookFilePath();

    Path getMeetingBookFilePath();

}
