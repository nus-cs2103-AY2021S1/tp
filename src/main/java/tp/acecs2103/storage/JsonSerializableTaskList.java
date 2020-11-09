package tp.acecs2103.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.exceptions.IllegalValueException;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.task.Task;

/**
 * An Immutable TaskList that is serializable to JSON format.
 */
@JsonRootName(value = "tasklist")
class JsonSerializableTaskList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Tasks list contains duplicate task(s).";

    private static final Logger logger = LogsCenter.getLogger(JsonSerializableTaskList.class);

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTaskList} with the given persons.
     */
    @JsonCreator
    public JsonSerializableTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code TaskList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTaskList}.
     */
    public JsonSerializableTaskList(TaskList source) {
        tasks.addAll(source.getTaskList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this task list into the model's {@code TaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskList.hasTask(task)) {
                logger.info("The index of duplicate task is: " + task.getIndex().toString());
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskList.initialize(task);
        }
        return taskList;
    }

}
