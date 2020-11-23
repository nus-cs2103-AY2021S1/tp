package seedu.tr4cker.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;

/**
 * Jackson-friendly version of {@link Todo}.
 */
public class JsonAdaptedDaily {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    private final String name;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedDaily} with the given todo details.
     */
    @JsonCreator
    public JsonAdaptedDaily(@JsonProperty("name") String name,
                            @JsonProperty("deadline") String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedDaily(Todo source) {
        name = source.getName().taskName;
        deadline = source.getDeadline().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Todo toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }
        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline, false);

        return new Todo(modelName, modelDeadline);
    }
}
