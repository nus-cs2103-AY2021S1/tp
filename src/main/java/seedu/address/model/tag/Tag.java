package seedu.address.model.tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Tag in the Projact.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Tag {

    // Identity fields
    private final TagName tagName;
    private final List<TagTask> tagTasks;
    private final Optional<MeetingLink> meetingLink;

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName, List<TagTask> tagTasks, Optional<MeetingLink> link) {
        requireAllNonNull(tagName, tagTasks);
        this.tagName = tagName;
        this.tagTasks = tagTasks;
        this.meetingLink = link;
    }

    /**
     * Every field must be present and not null.
     */
    public Tag(TagName tagName) {
        requireAllNonNull(tagName);
        this.tagName = tagName;
        this.tagTasks = new ArrayList<TagTask>();
        this.meetingLink = Optional.empty();
    }

    public TagName getTagName() {
        return this.tagName;
    }

    public List<TagTask> getTagTasks() {
        return Collections.unmodifiableList(this.tagTasks);
    }

    public Optional<MeetingLink> getMeetingLink() {
        return meetingLink;
    }

    /**
     * Returns a list of the tag's uncompleted tasks.
     */
    public List<TagTask> getAllUncompletedTasks() {
        List<TagTask> newTagTasks = new ArrayList<>();
        for (TagTask task : tagTasks) {
            if (!task.getIsDone()) {
                newTagTasks.add(task);
            }
        }
        return newTagTasks;
    }

    /**
     * Returns true if both tags have the same name
     * This defines a weaker notion of equality between two tags.
     */
    public boolean isSameTag(Tag otherTag) {
        if (otherTag == null) {
            return false;
        }
        if (otherTag == this) {
            return true;
        }

        return otherTag.getTagName().equals(getTagName());
    }

    /**
     * Returns true if both tags have the same name and data fields.
     * This defines a stronger notion of equality between two tags.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return otherTag.getTagName().equals(getTagName())
                && otherTag.getTagTasks().equals(getTagTasks())
                && otherTag.getMeetingLink().equals(getMeetingLink());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(tagName, tagTasks, meetingLink);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTagName());
        if (!getMeetingLink().isEmpty()) {
            builder.append(" Link: ");
            getMeetingLink().ifPresent(link -> builder.append(link));
        }

        if (!getTagTasks().isEmpty()) {
            builder.append(" Tasks: ");
            getTagTasks().forEach(builder::append);
        }

        return builder.toString();
    }

}
