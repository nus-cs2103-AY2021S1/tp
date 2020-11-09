package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Date;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Status;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskName;



/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String priority;
    private final String date;
    private final String status;
    private final String dateCreated;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(
            @JsonProperty("name") String name,
            @JsonProperty("tag") List<JsonAdaptedTag> tags,
            @JsonProperty("priority") String priority,
            @JsonProperty("date") String date,
            @JsonProperty("status") String status,
            @JsonProperty("dateCreated") String dateCreated) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.status = status;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.dateCreated = dateCreated;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        assert source.getName().isPresent();
        name = source.getName().get().getValue();
        if (source.getPriority().isEmpty()) {
            priority = null;
        } else {
            priority = source.getPriority().get().toString();
        }
        if (source.getDate().isEmpty()) {
            date = null;
        } else {
            date = source.getDate().get().toString();
        }
        if (source.getStatus().isEmpty()) {
            status = null;
        } else {
            status = source.getStatus().get().toString();
        }
        if (source.getTags().isPresent()) {
            tags.addAll(source.getTags().get().stream()
                    .map(JsonAdaptedTag::new)
                    .collect(Collectors.toList()));
        }
        assert source.getDateCreated().isPresent();
        dateCreated = source.getDateCreated().get().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted contact.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            taskTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelTaskName = new TaskName(name);

        Priority modelPriority = null;
        if (priority != null) {
            if (!Priority.isValidPriority(priority)) {
                throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
            }
            modelPriority = Priority.valueOf(priority);
        }

        Date modelDate = null;
        if (date != null) {
            if (!Date.isValidDate(date)) {
                throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
            }
            modelDate = new Date(date);
        }

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Status.class.getSimpleName()));
        }
        final Status modelStatus;
        try {
            modelStatus = Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final LocalDate modelDateCreated;
        final Set<Tag> modelTags = new HashSet<>(taskTags);
        try {
            modelDateCreated = LocalDate.parse(dateCreated);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }

        Task task = new Task(modelTaskName);
        task = task.setTags(modelTags);
        task = task.setPriority(modelPriority);
        task = task.setDate(modelDate);
        task = task.setStatus(modelStatus);
        task = task.setDateCreated(modelDateCreated);

        return task;
    }
}
