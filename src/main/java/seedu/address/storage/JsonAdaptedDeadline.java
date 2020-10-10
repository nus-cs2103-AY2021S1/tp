package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.DoneStatus;
import seedu.address.model.task.Priority;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.Title;

public class JsonAdaptedDeadline {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deadline's %s field is missing!";

    private final String title;
    private final String description;
    private final String priority;
    private final Integer doneStatus;
    private final String deadlineDate;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("title") String title, @JsonProperty("description") String description,
                           @JsonProperty("priority") String priority, @JsonProperty("doneStatus") Integer doneStatus,
                           @JsonProperty("deadlineDate") String deadlineDate,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.doneStatus = doneStatus;
        this.deadlineDate = deadlineDate;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code ToDo} into this class for Jackson use.
     */
    public JsonAdaptedDeadline(Deadline source) {
        title = source.getTitle().title;
        description = source.getDescription().value;
        priority = source.getPriority().level.toString();
        doneStatus = source.getStatus().status.getStatusCode();
        deadlineDate = source.getDeadlineDate().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code ToDo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted todo.
     */
    public Deadline toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(description);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (doneStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DoneStatus.class.getSimpleName()));
        }
        if (!DoneStatus.isValidDoneStatus(doneStatus)) {
            throw new IllegalValueException(DoneStatus.MESSAGE_CONSTRAINTS);
        }
        final DoneStatus modelStatus = new DoneStatus(doneStatus);

        if (deadlineDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDate.class.getSimpleName()));
        }
        if (!TaskDate.isValidDate(deadlineDate)) {
            throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
        }
        final TaskDate modelDlDate = new TaskDate(deadlineDate);

        final Set<Tag> modelTags = new HashSet<>(taskTags);
        return new Deadline(modelTitle, modelDesc, modelPriority, modelDlDate, modelStatus, modelTags);
    }
}
