package seedu.address.testutil;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagTask;
import seedu.address.model.util.SampleDataUtil;

public class TagBuilder {

    public static final String DEFAULT_NAME = "friend";

    private TagName tagName;
    private List<TagTask> tagTasks;
    private Optional<MeetingLink> meetingLink;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        this.tagName = new TagName(DEFAULT_NAME);
        this.tagTasks = new ArrayList<>();
        this.meetingLink = Optional.empty();
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        this.tagName = tagToCopy.getTagName();
        this.tagTasks = tagToCopy.getTagTasks();
        this.meetingLink = tagToCopy.getMeetingLink();
    }

    /**
     * Sets the {@code TagName} of the {@code Tag} that we are building.
     */
    public TagBuilder withTagName(String tagName) {
        this.tagName = new TagName(tagName);
        return this;
    }

    /**
     * Sets the {@code List<TagTask>} of the {@code Tag} that we are building.
     */
    public TagBuilder withTagTasks(TagTask ... tagTasks) {
        this.tagTasks = SampleDataUtil.getTagTaskList(tagTasks);
        return this;
    }

    /**
     * Sets the {@code MeetingLink} of the {@code Tag} that we are building.
     */
    public TagBuilder withMeetingLink(String meetingLink) {
        try {
            this.meetingLink = Optional.of(new MeetingLink(meetingLink));
        } catch (MalformedURLException e) {
            this.meetingLink = Optional.empty();
        }
        return this;
    }

    /**
     * Parses the {@code taskDescription} and {@code isDone} into a {@code TagTask}
     * and add it to the {@code List<TagTask>} of this {@code Tag} that we are building.
     */
    public TagBuilder addTagTask(String taskDescription, boolean isDone) {
        this.tagTasks.add(new TagTask(taskDescription, isDone));
        return this;
    }

    public Tag build() {
        return new Tag(tagName, tagTasks, meetingLink);
    }
}
