package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagTask;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tag's %s field is missing!";

    private final String tagName;
    private final List<JsonAdaptedTagTask> tagTasks = new ArrayList<>();
    private final String meetingLink;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given Tag details.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("tagName") String tagName,
                          @JsonProperty("tagTasks") List<JsonAdaptedTagTask> tagTasks,
                          @JsonProperty("meetingLink") String meetingLink) {
        this.tagName = tagName;
        if (tagTasks != null) {
            this.tagTasks.addAll(tagTasks);
        }
        this.meetingLink = meetingLink;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        this.tagName = source.getTagName().tagName;
        this.tagTasks.addAll(source.getTagTasks().stream()
                            .map(JsonAdaptedTagTask::new)
                            .collect(Collectors.toList()));
        this.meetingLink = source.getMeetingLink().isEmpty()
                            ? ""
                            : source.getMeetingLink().get().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     * @throws MalformedURLException if meeting link's value is valid
     */
    public Tag toModelType() throws IllegalValueException, MalformedURLException {
        if (tagName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TagName.class.getSimpleName()));
        }
        if (!TagName.isValidTagName(tagName)) {
            throw new IllegalValueException(TagName.MESSAGE_CONSTRAINTS);
        }
        final TagName modelTagName = new TagName(tagName);

        final List<TagTask> modelTagTasks = new ArrayList<>();
        for (JsonAdaptedTagTask tagTask : tagTasks) {
            modelTagTasks.add(tagTask.toModelType());
        }

        final Optional<MeetingLink> modelLink;
        if (meetingLink == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MeetingLink.class.getSimpleName()));
        }
        if (meetingLink == "") {
            modelLink = Optional.empty();
        } else if (!MeetingLink.isValidMeetingLink((meetingLink))) {
            throw new IllegalValueException(MeetingLink.MESSAGE_CONSTRAINTS);
        } else {
            modelLink = Optional.of(new MeetingLink(meetingLink));
        }

        return new Tag(modelTagName, modelTagTasks, modelLink);
    }

}
