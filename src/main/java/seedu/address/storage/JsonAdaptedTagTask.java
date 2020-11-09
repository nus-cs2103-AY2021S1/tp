package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.TagTask;

public class JsonAdaptedTagTask {

    private final String taskDescription;
    private final boolean isDone;

    /**
     * Constructs a {@code JsonAdaptedTagTask} with the given TagTask details.
     */
    @JsonCreator
    public JsonAdaptedTagTask(@JsonProperty("taskDescription") String taskDescription,
                            @JsonProperty("isDone") boolean isDone) {
        this.taskDescription = taskDescription;
        this.isDone = isDone;
    }

    /**
     * Converts a given {@code TagTask} into this class for Jackson use.
     */
    public JsonAdaptedTagTask(TagTask source) {
        this.taskDescription = source.taskDescription;
        this.isDone = source.getIsDone();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public TagTask toModelType() throws IllegalValueException {
        if (taskDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TagTask.class.getSimpleName()));
        }
        if (!TagTask.isValidTaskDescription(taskDescription)) {
            throw new IllegalValueException(TagTask.MESSAGE_CONSTRAINTS);
        }

        return new TagTask(taskDescription, isDone);
    }
}
