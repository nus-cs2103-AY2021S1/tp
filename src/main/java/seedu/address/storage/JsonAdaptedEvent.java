package seedu.address.storage;

import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedEvent.class);

    private final String title;
    private final String startDateTime;
    private final String endDateTime;
    private final String description;
    private final boolean isLesson;
    private final String tag;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("title") String title, @JsonProperty("startDateTime") String startDateTime,
                            @JsonProperty("endDateTime") String endDateTime,
                            @JsonProperty("description") String description, @JsonProperty("isLesson") boolean isLesson,
                            @JsonProperty("tag") String tag) {
        this.title = title;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.description = description;
        this.isLesson = isLesson;
        this.tag = tag;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Event source) {
        title = source.getTitle().title;
        startDateTime = source.getStartDateTime().toString();
        endDateTime = source.getEndDateTime().toString();
        description = source.getDescription().value;
        isLesson = source.isLesson();
        tag = source.getTag().tagName;

        logger.info("Planus event with title: '" + title + "' successfully converted to adapted task object");
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Event toModelType() throws IllegalValueException {
        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (startDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartDateTime.class.getSimpleName()));
        }

        final StartDateTime modelStartDateTime;

        if (!DateUtil.isValidDateTime(startDateTime) || startDateTime.equals("")) {
            throw new IllegalValueException(DateUtil.MESSAGE_CONSTRAINTS);
        } else {
            modelStartDateTime = new StartDateTime(startDateTime);
        }

        if (endDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StartDateTime.class.getSimpleName()));
        }

        final EndDateTime modelEndDateTime;

        if (!DateUtil.isValidDateTime(endDateTime) || endDateTime.equals("")) {
            throw new IllegalValueException(DateUtil.MESSAGE_CONSTRAINTS);
        } else {
            modelEndDateTime = new EndDateTime(endDateTime);
        }

        // tentatively description field is not allowed to be empty
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        final Description modelDescription;
        if (description.equals("")) {
            logger.info("Description for event title: '" + title + "' is empty. Creating a default description for it");
            modelDescription = Description.defaultDescription();
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        final Tag modelTag;
        if (tag.equals("")) {
            logger.info("Tag for event title: '" + title + "' is empty. Creating a default tag for it");
            modelTag = Tag.defaultTag();
        } else if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        } else {
            modelTag = new Tag(tag);
        }

        return new Event(modelTitle, modelStartDateTime, modelEndDateTime, modelDescription, modelTag, isLesson);
    }
}
