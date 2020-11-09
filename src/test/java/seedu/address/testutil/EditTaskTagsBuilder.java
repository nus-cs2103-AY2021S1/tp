package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.ContactTaskTagCommand.EditTaskTags;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditTaskTags objects.
 */
public class EditTaskTagsBuilder {
    private EditTaskTags editTaskTags;

    public EditTaskTagsBuilder() {
        editTaskTags = new EditTaskTags();
    }

    public EditTaskTagsBuilder(EditTaskTags editTaskTags) {
        this.editTaskTags = new EditTaskTags(editTaskTags);
    }

    /**
     * Returns a {@code EditTaskTags} with fields containing {@code Task}'s details
     */
    public EditTaskTagsBuilder(Task task) {
        editTaskTags = new EditTaskTags();
        editTaskTags.setTags(task.getTags());
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditTaskTags}
     * that we are building.
     */
    public EditTaskTagsBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        editTaskTags.setTags(tagSet);
        return this;
    }

    public EditTaskTags build() {
        return editTaskTags;
    }

}
