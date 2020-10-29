package seedu.taskmaster.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.commons.exceptions.IllegalValueException;
import seedu.taskmaster.commons.util.FileUtil;
import seedu.taskmaster.commons.util.JsonUtil;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.record.StudentRecord;

/**
 * A class to access Taskmaster data stored as a json file on the hard disk.
 */
public class JsonTaskmasterStorage implements TaskmasterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskmasterStorage.class);

    private Path filePath;

    public JsonTaskmasterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskmasterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskmaster> readTaskmaster() throws DataConversionException {
        return readTaskmaster(filePath);
    }

    /**
     * Similar to {@link #readTaskmaster()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskmaster> readTaskmaster(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskmaster> jsonTaskmaster = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskmaster.class);
        if (!jsonTaskmaster.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskmaster.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException {
        saveTaskmaster(taskmaster, filePath);
    }

    /**
     * Similar to {@link #saveTaskmaster(ReadOnlyTaskmaster)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
        requireNonNull(taskmaster);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskmaster(taskmaster), filePath);
    }

    /**
     * Saves the list of Attendances in a Taskmaster to the filepath specified.
     */
    @Override
    public void saveAttendance(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
        requireNonNull(taskmaster);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(
                JsonSerializableAttendanceList.getSerializableListFromAttendances(
                        taskmaster.getStudentRecordList()),
                filePath);
    }

    /**
     * Gets a list of Attendances from the filePath specified.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<List<StudentRecord>> readAttendance(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAttendanceList> jsonAttendanceList = JsonUtil.readJsonFile(
                filePath, JsonSerializableAttendanceList.class);

        if (!jsonAttendanceList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAttendanceList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }
}
