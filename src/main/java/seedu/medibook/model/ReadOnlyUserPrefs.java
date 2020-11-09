package seedu.medibook.model;

import java.nio.file.Path;

import seedu.medibook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMediBookFilePath();

    Path getMedicalNotesDirPath();

}
