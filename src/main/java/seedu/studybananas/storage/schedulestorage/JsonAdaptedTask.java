package seedu.studybananas.storage.schedulestorage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.studybananas.commons.exceptions.IllegalValueException;
import seedu.studybananas.model.task.DateTime;
import seedu.studybananas.model.task.Description;
import seedu.studybananas.model.task.Duration;
import seedu.studybananas.model.task.Task;
import seedu.studybananas.model.task.Title;

public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String title;
    private final Optional<String> description;
    private final Optional<Integer> duration;
    private final Optional<JsonAdaptedDateTime> dateTime;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title,
                           @JsonProperty("description") Optional<String> description,
                           @JsonProperty("dateTime") Optional<String> dateTime,
                           @JsonProperty("duration") Optional<Integer> duration) {
        this.title = title;
        this.description = description;
        this.dateTime = dateTime.map(JsonAdaptedDateTime::new);
        this.duration = duration;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle().title;
        description = source.getDescription().map(description -> description.toString());
        dateTime = source.getDateTime().map(JsonAdaptedDateTime::new);
        duration = source.getDuration().map(duration-> duration.duration);
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {

        if (title == null || title.equals("")) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        final Title modelTitle = new Title(title);
        final Description modelDescription = description.map(desc ->
            desc.equals("") ? null : new Description(desc)
        ).orElse(null);

        final DateTime modelDateTime = dateTime.map(jsonDateTime -> {
            try {
                return jsonDateTime.toModelType();
            } catch (IllegalValueException e) {
                return null;
            }
        }).orElse(null);

        final Duration modelDuration = duration.map(dur -> new Duration(dur)).orElse(null);
        return new Task(modelTitle, modelDescription, modelDateTime, modelDuration);
    }

}
