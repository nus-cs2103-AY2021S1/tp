package seedu.tr4cker.model.daily;

import static seedu.tr4cker.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;


/**
 * Represents a Todo task in Daily tab.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Todo {
    private final Name name;
    private final Deadline deadline;

    /**
     * Every field must be present and not null.
     */
    public Todo(Name name, Deadline deadline) {
        requireAllNonNull(name, deadline);
        this.name = name;
        this.deadline = deadline;
    }

    /**
     * Returns name of a todo task
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns deadline of a todo task
     */
    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if both todos of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two todos.
     */
    public boolean isSameTodo(Todo otherTodo) {
        if (otherTodo == this) {
            return true;
        }

        return otherTodo != null
                && otherTodo.getName().equals(getName())
                && (otherTodo.getDeadline().equals(getDeadline()));
    }

    /**
     * Returns true if both todos have the same identity and data fields.
     * This defines a stronger notion of equality between two todos.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Todo)) {
            return false;
        }

        Todo otherTodo = (Todo) other;
        return otherTodo.getName().equals(getName())
                && otherTodo.getDeadline().equals(getDeadline());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, deadline);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Deadline: ")
                .append(getDeadline());
        return builder.toString();
    }
}
