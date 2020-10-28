package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.order.ReadOnlyOrderManager;
import seedu.address.model.preset.Preset;

public interface PresetManagerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPresetManagerFilePath();

    /**
     * Returns OrderManager data as a {@link ReadOnlyOrderManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<List<List<Preset>>> readPresetManager() throws DataConversionException, IOException;

    /**
     * @see #getPresetManagerFilePath()
     */
    Optional<List<List<Preset>>> readPresetManager(Path filePath) throws DataConversionException, IOException;

    void savePresetManager(List<List<Preset>> allPresets) throws IOException;

    void savePresetManager(List<List<Preset>> allPresets, Path filePath) throws IOException;
}
