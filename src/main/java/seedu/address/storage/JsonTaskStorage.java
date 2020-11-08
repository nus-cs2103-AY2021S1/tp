package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyList;
import seedu.address.model.room.RoomTasks;
import seedu.address.model.task.Task;

//@@author itssodium
/**
 * Reads data from storage data files and imports them into RoomTasks
 */
public class JsonTaskStorage {
    private Path task;

    /**
     * Creates the TaskOccupancy object
     */
    public JsonTaskStorage() {}

    /**
     * Creates the TaskOccupancy object
     */
    public JsonTaskStorage(Path task) {
        this.task = task;
    }

    public Path getTask() {
        return task;
    }

    public Optional<ReadOnlyList<Task>> readOnlyTask() throws IOException, DataConversionException {
        return readOnlyTask(task);
    }

    /**
     * Returns RoomTasks data as a {@code ReadOnlyList<Task>}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    public Optional<ReadOnlyList<Task>> readOnlyTask(Path filePath) throws IOException, DataConversionException {
        Optional<JsonSerializableTaskList> jsonCovigentApp = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskList.class);
        if (!jsonCovigentApp.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonCovigentApp.get().toModelType());
        } catch (IllegalValueException ive) {
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the task to the {@code roomTasks}.
     */
    public void saveTask(RoomTasks roomTasks) throws IOException {
        saveTasks(roomTasks, task);
    }

    /**
     * Saves the task to the {@code roomTasks} with the path.
     *
     * @throws IOException
     */
    public void saveTasks(RoomTasks roomTasks, Path fileTask) throws IOException {
        FileUtil.createIfMissing(fileTask);
        List<Task> tasks = new ArrayList<>();
        tasks.addAll(roomTasks.getReadOnlyList());
        JsonUtil.saveJsonFile(new JsonSerializableTaskList(roomTasks), fileTask);
    }
}
