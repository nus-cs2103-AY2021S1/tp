package seedu.resireg.model;

import java.nio.file.Path;
import java.util.List;

import seedu.resireg.commons.core.GuiSettings;
import seedu.resireg.model.alias.CommandWordAlias;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    List<CommandWordAlias> getCommandWordAliases();

    Path getAddressBookFilePath();

}
