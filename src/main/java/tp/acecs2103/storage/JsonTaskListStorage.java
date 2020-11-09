package tp.acecs2103.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tp.acecs2103.commons.exceptions.DataConversionException;
import tp.acecs2103.commons.exceptions.IllegalValueException;
import tp.acecs2103.commons.util.FileUtil;
import tp.acecs2103.commons.util.JsonUtil;
import tp.acecs2103.model.TaskList;



public class JsonTaskListStorage implements TaskListStorage {


    private Path filePath;

    public JsonTaskListStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskListFilePath() {
        return filePath;
    }

    @Override
    public Optional<TaskList> readTaskList() throws DataConversionException {
        return readTaskList(filePath);
    }

    /**
     * Similar to {@link #readTaskList()}}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<TaskList> readTaskList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskList> jsonTaskList = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (!jsonTaskList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskList.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        saveTaskList(taskList, filePath);
    }

    @Override
    public void saveTaskList(TaskList taskList, Path filePath) throws IOException {
        requireNonNull(taskList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(taskList), filePath);
    }
}

