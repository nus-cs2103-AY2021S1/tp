package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's name in the todo list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTaskName(String)}
 */
public class TaskName {
    public static final String MESSAGE_CONSTRAINTS = "Task name should be no longer than 30 words.";

    private final String value;

    /**
     * Constructs a {@code TaskName}.
     *
     * @param taskName valid task name.
     */
    public TaskName(String taskName) {
        requireNonNull(taskName);
        this.value = taskName;
    }

    /**
     * Returns true if a given string is a valid task name.
     *
     * @param test given string
     * @return true if task name is valid
     */
    public static boolean isValidTaskName(String test) {
        return test.length() <= 30;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TaskName)) {
            return false;
        }

        return value.equals(((TaskName) other).value);
    }

    @Override
    public String toString() {
        return value;
    }
}
