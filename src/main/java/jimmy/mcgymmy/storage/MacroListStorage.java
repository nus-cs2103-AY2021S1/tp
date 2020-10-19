package jimmy.mcgymmy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.logic.macro.MacroList;
import jimmy.mcgymmy.model.ReadOnlyUserPrefs;

public interface MacroListStorage {

    /**
     * Returns the file path of the UserPrefs data file.
     */
    Path getMacroListFilePath();

    /**
     * Returns UserPrefs data from storage.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<MacroList> readMacroList() throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUserPrefs} to the storage.
     *
     * @param macroList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMacroList(MacroList macroList) throws IOException;

}
