package tp.cap5buddy.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import tp.cap5buddy.modules.ModuleList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonModuleListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleListStorage.class);

    private Path filePath;

    public JsonModuleListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getModuleListFilePath() {
        return filePath;
    }

    public Optional<ModuleList> readModuleList() throws DataConversionException {
        return readModuleList(filePath);
    }

    public Optional<ModuleList> readModuleList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableModuleList> jsonModuleList = JsonUtil.readJsonFile(
                filePath, JsonSerializableModuleList.class);
        if (!jsonModuleList.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonModuleList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void saveModuleList(ModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    public void saveModuleList(ModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(moduleList), filePath);
    }

}
