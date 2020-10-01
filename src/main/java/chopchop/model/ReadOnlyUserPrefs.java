package chopchop.model;

import java.nio.file.Path;

import chopchop.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

<<<<<<< HEAD
    Path getRecipeBookFilePath();
=======
    Path getAddressBookFilePath();
>>>>>>> cb563d89572c21157e8c19f7640820f6f84be35a

}
