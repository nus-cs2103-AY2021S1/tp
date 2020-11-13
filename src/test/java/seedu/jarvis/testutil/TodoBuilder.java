package seedu.jarvis.testutil;

import seedu.jarvis.model.task.Todo;

public class TodoBuilder {

    public static final String DEFAULT_TODO_DESCRIPTION = "Complete CS2103T increments";

    private String description;

    public TodoBuilder() {
        description = DEFAULT_TODO_DESCRIPTION;
    }

    /**
     * Sets the description of the Todo we are building
     * @param newDescription
     * @return
     */
    public TodoBuilder withDescription(String newDescription) {
        description = newDescription;
        return this;
    }

    public Todo build() {
        return new Todo(description);
    }

    public Todo buildJson(String taskId) {
        return new Todo(taskId, description);
    }

}
