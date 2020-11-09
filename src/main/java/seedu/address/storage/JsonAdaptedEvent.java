package seedu.address.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Recurrence;
import seedu.address.model.task.Task;

public class JsonAdaptedEvent extends JsonAdaptedTask {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final String linkDesc;
    private final String linkUrl;
    private final String linkTime;
    private final Recurrence recurrence;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEvent} with the given Event details.
     */
    @JsonCreator
    public JsonAdaptedEvent(@JsonProperty("description") String description, @JsonProperty("isDone") Boolean isDone,
                           @JsonProperty("start") LocalDateTime start, @JsonProperty("end") LocalDateTime end,
                            @JsonProperty("linkDesc") String linkDesc, @JsonProperty("linkUrl") String url,
                            @JsonProperty("linkTime") String linkTime,
                            @JsonProperty("recurrence") JsonAdaptedRecurrence recurrence,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        this.linkDesc = linkDesc;
        this.linkUrl = url;
        this.linkTime = linkTime;
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
     * Converts a given {@code Event} into this class for Jackson use.
     */
    public JsonAdaptedEvent(Task source) {
        super(source);
        start = source.getStart();
        end = source.getEnd();
        if (source.getLink().isPresent()) {
            linkDesc = source.getLink().get().getDescription().split(" ", 2)[0];
            linkUrl = source.getLink().get().getUrl();
            linkTime = ((Event) source).getMeetingLink().saveTimeFormat();
        } else {
            linkDesc = null;
            linkUrl = null;
            linkTime = null;
        }
        recurrence = source.getRecurrence();
        tagged.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Event} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event.
     */
    @Override
    public Task toModelType() throws IllegalValueException {
        final Set<Tag> eventTags = new HashSet<>();
        for (JsonAdaptedTag tag : tagged) {
            eventTags.add(tag.toModelType());
        }
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "description"));
        }
        final String modelDescription = description;
        if (isDone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "isDone"));
        }
        final boolean modelIsDone = isDone;

        if (start == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start date and time"));
        }

        final LocalDateTime modelStart = start;

        if (end == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end date and time"));
        }

        final LocalDateTime modelEnd = end;
        final Recurrence modelRecurrence = recurrence;
        final Set<Tag> modelTags = new HashSet<>(eventTags);

        if (linkUrl == null || linkDesc == null || linkTime == null) {
            if (modelRecurrence == null) {
                return new Event(modelIsDone, modelDescription, modelStart, modelEnd, modelTags);
            } else {
                return new Event(modelIsDone, modelDescription, modelStart, modelEnd, modelRecurrence, modelTags);
            }
        } else {
            if (modelRecurrence == null) {
                return new Event(modelIsDone, modelDescription, modelStart,
                        modelEnd, new MeetingLink(linkDesc, linkUrl, linkTime), modelTags);
            } else {
                return new Event(modelIsDone, modelDescription,
                        modelStart, modelEnd, modelRecurrence, new MeetingLink(linkDesc, linkUrl, linkTime), modelTags);
            }
        }
    }
}

