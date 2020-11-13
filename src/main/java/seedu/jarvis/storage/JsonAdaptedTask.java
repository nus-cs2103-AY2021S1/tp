package seedu.jarvis.storage;

import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.jarvis.commons.exceptions.IllegalValueException;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;

public class JsonAdaptedTask {

    public static final String INVALID_TASK_TYPE = "Task type is invalid! Please follow ID format:\n"
            + "T for Todo, E for Event, D for Deadline, followed by a number. Eg E15";
    public static final String INVALID_DATE_TIME_FORMAT = "Date time format is wrong! Please follow format: "
            + "YYYY-MM-DDTHH:MM";
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public static final String ID_FIELD = "Task ID";
    public static final String DESCRIPTION_FIELD = "Task's %s field is missing!";
    public static final String DATETIME_FIELD = "Task's %s field is missing!";

    public static final String TASK_TODO = "T";
    public static final String TASK_EVENT = "E";
    public static final String TASK_DEADLINE = "D";

    private final String id;
    private final String description;
    private final String dateTime;

    /**
     * Constructs a Json-friendly task object with the given task details.
     * @param id task's id
     * @param description task's description
     * @param dateTime task's date and time
     */
    public JsonAdaptedTask(@JsonProperty("id") String id, @JsonProperty("description") String description,
                           @JsonProperty("dateTime") String dateTime) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        id = source.getTaskId();
        description = source.getDescription();
        dateTime = source.getUnformattedPossibleDateTime(source);
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ID_FIELD));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DESCRIPTION_FIELD));
        }

        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, DATETIME_FIELD));
        }

        String taskType = id.substring(0, 1);

        switch (taskType) {
        case TASK_TODO:
            return new Todo(id, description);

        case TASK_EVENT:
            try {
                return new Event(id, description, dateTime);

            } catch (DateTimeParseException e) {
                throw new IllegalValueException(INVALID_DATE_TIME_FORMAT);
            }

        case TASK_DEADLINE:
            try {
                return new Deadline(id, description, dateTime);

            } catch (DateTimeParseException e) {
                throw new IllegalValueException(INVALID_DATE_TIME_FORMAT);
            }

        default:
            throw new IllegalValueException(INVALID_TASK_TYPE);
        }

    }

}
