package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

import java.time.LocalDate;
import java.util.Set;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public final String taskName;
        private final String description;
        private final LocalDate publishDate;
        private final Deadline deadline;
        private final double progress;
        private final boolean isDone;
        private final Set<String> assignees;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskName}.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("description")String description,
                           @JsonProperty("publishDate")LocalDate publishDate,
                           @JsonProperty("deadline")Deadline deadline,
                           @JsonProperty("progress")double progress,
                           @JsonProperty("isDone")boolean isDone,
                           @JsonProperty("assignees")Set<String> assignees) {
        this.taskName = taskName;
        this.description = description;
        this.publishDate = publishDate;
        this.deadline = deadline;
        this.progress = progress;
        this.isDone = isDone;
        this.assignees = assignees;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.taskName;
                description = source.getDescription();
                publishDate = source.getPublishDate();
                deadline = source.getDeadline();
                progress = source.getProgress();
                isDone = source.isDone();
                assignees = source.getAssignees();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        Task task = new Task(taskName, description, deadline, progress, isDone);
        task.setPublishDate(publishDate);
        return task;
    }

}
