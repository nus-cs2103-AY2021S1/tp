package seedu.address.model.task;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class ToDo extends Task {

    /**
     * Every field must be present and not null.
     */
    public ToDo(Title title, Description description, Priority priority, Set<Tag> tags) {
        super(title, description, priority, tags);
    }
}
