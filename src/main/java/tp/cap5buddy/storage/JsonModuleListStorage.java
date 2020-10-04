package tp.cap5buddy.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import tp.cap5buddy.modules.ModuleList;



/**
 * A class to access CAP5Buddy data stored as a json file on the hard disk.
 */
public class JsonModuleListStorage implements ModuleListStorage {
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

    @Override
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

    @Override
    public void saveModuleList(ModuleList moduleList) throws IOException {
        saveModuleList(moduleList, filePath);
    }

    /**
     * Similar to {@link #saveModuleList(ModuleList)}.
     * @param moduleList
     * @param filePath
     * @throws IOException
     */
    public void saveModuleList(ModuleList moduleList, Path filePath) throws IOException {
        requireNonNull(moduleList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableModuleList(moduleList), filePath);
    }

}
