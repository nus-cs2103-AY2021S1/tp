package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Jackson-friendly version of {@link Todo}.
 */
public class JsonAdaptedTodo extends JsonAdaptedTask {

    private final LocalDateTime deadline;
    private final String linkDesc;
    private final String linkUrl;
    private final Recurrence recurrence;
    private final Set<JsonAdaptedTag> tagged = new HashSet<>();

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given Todo details.
     */
    @JsonCreator
    public JsonAdaptedTodo(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("deadline") LocalDateTime deadline, @JsonProperty("linkDesc") String linkDesc,
                           @JsonProperty("linkUrl") String url,
                           @JsonProperty("recurrence") JsonAdaptedRecurrence recurrence,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(description, isDone);
        this.deadline = deadline;
        this.linkDesc = linkDesc;
        this.linkUrl = url;
        if (recurrence != null) {
            this.recurrence = recurrence.toModelType();
        } else {
            this.recurrence = null;
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Todo} into this class for Jackson use.
     */
    public JsonAdaptedTodo(Task source) {
        super(source);
        deadline = source.getDeadline();
        if (source.getLink().isPresent()) {
            linkDesc = source.getLink().get().getDescription();
            linkUrl = source.getLink().get().getUrl();
        } else {
            linkDesc = null;
            linkUrl = null;
        }
        recurrence = source.getRecurrence();
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code Todo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Todo.
     */
    @Override
    public Task toModelType() throws IllegalValueException {
        final Set<Tag> personTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        final String modelDescription = description;
        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone"));
        }
        final boolean modelIsDone = isDone;

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "deadline"));
        }

        final LocalDateTime modelDeadline = deadline;
        final Recurrence modelRecurrence = recurrence;
        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (linkUrl == null || linkDesc == null) {
            if (modelRecurrence == null) {
                return new Todo(modelIsDone, modelDescription, modelDeadline, modelTags);
            } else {
                return new Todo(modelIsDone, modelDescription, modelDeadline, modelRecurrence, modelTags);
            }
        } else {
            if (modelRecurrence == null) {
                return new Todo(modelIsDone, modelDescription, modelDeadline,
                    new CollaborativeLink(linkDesc, linkUrl), modelTags);
            } else {
                return new Todo(modelIsDone, modelDescription, modelDeadline,
                        modelRecurrence, new CollaborativeLink(linkDesc, linkUrl), modelTags);
            }
        }
    }
}
