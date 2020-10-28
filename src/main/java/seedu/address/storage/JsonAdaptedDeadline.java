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
import seedu.address.model.task.deadline.Deadline;
import seedu.address.model.task.deadline.DeadlineDateTime;
import seedu.address.model.task.deadline.DoneDateTime;
import seedu.address.model.task.deadline.Duration;
import seedu.address.model.task.deadline.Status;


/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedDeadline {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedDeadline.class);

    private final String title;
    private final String deadlineDateTime;
    private final String description;
    private final String doneDateTime;
    private final String status;
    private final int duration;
    private final String tag;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedDeadline(@JsonProperty("title") String title,
                               @JsonProperty("deadlineDateTime") String deadlineDateTime,
                               @JsonProperty("description") String description,
                               @JsonProperty("doneDateTime") String doneDateTime,
                               @JsonProperty("duration") int duration,
                               @JsonProperty("tag") String tag,
                               @JsonProperty("status") String status) {
        this.title = title;
        this.deadlineDateTime = deadlineDateTime;
        this.description = description;
        this.doneDateTime = doneDateTime;
        this.duration = duration;
        this.tag = tag;
        this.status = status;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedDeadline(Deadline source) {
        title = source.getTitle().title;
        deadlineDateTime = source.getDeadlineDateTime().toString();
        description = source.getDescription().value;
        doneDateTime = source.getDoneDateTime().toString();
        status = source.getStatus().toString();
        duration = source.getTimeTaken();
        tag = source.getTag().tagName;

        logger.info("Planus deadline with title: '" + title + "' successfully converted to adapted task object");
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Deadline toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (deadlineDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeadlineDateTime.class.getSimpleName()));
        }

        final DeadlineDateTime modelDeadlineDateTime;

        if (deadlineDateTime.equals("")) {
            logger.info("Deadline datetime for deadline with title: '" + title + "' is empty. "
                    + "Creating a default deadline datetime for it");
            modelDeadlineDateTime = DeadlineDateTime.createNullDeadlineDateTime();
        } else if (!DateUtil.isValidDateTime(deadlineDateTime)) {
            throw new IllegalValueException(DateUtil.MESSAGE_CONSTRAINTS);
        } else {
            modelDeadlineDateTime = new DeadlineDateTime(deadlineDateTime);
        }

        // tentatively description field is not allowed to be empty
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }

        final Description modelDescription;
        if (description.equals("")) {
            logger.info("Description for deadline title: '" + title + "' is empty. "
                    + "Creating a default description for it");
            modelDescription = Description.defaultDescription();
        } else if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        } else {
            modelDescription = new Description(description);
        }

        if (doneDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DoneDateTime.class.getSimpleName()));
        }

        final DoneDateTime modelDoneDateTime;

        if (doneDateTime.equals("")) {
            logger.info("Done datetime for deadline with title: '" + title + "' is empty. Creating a default "
                    + "done datetime for it");
            modelDoneDateTime = DoneDateTime.createNullDoneDateTime();
        } else if (!DateUtil.isValidDateTime(doneDateTime)) {
            throw new IllegalValueException(DateUtil.MESSAGE_CONSTRAINTS);
        } else {
            modelDoneDateTime = new DoneDateTime(doneDateTime);
        }

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        final Status modelStatus;

        if (Status.isComplete(status)) {
            modelStatus = Status.createCompleteStatus();
        } else {
            modelStatus = Status.createIncompleteStatus();
        }

        final Duration modelDuration;

        if (!Duration.isValidDuration(duration)) {
            throw new IllegalValueException(Duration.INVALID_DURATION_FORMAT);
        } else {
            modelDuration = new Duration(duration);
        }

        final Tag modelTag;

        if (tag.equals("")) {
            logger.info("Tag for deadline title: '" + title + "' is empty. Creating a default tag for it");
            modelTag = Tag.defaultTag();
        } else if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        } else {
            modelTag = new Tag(tag);
        }

        return new Deadline(modelTitle, modelDeadlineDateTime, modelDescription, modelTag, modelStatus, modelDuration,
                modelDoneDateTime);
    }

}
