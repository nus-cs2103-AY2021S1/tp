package seedu.tr4cker.testutil;

import seedu.tr4cker.model.Tr4cker;
import seedu.tr4cker.model.countdown.Event;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.task.Task;

/**
 * A utility class to help with building Tr4cker objects.
 * Example usage: <br>
 *     {@code Tr4cker ab = new Tr4ckerBuilder().withTask("John", "Doe").build();}
 */
public class Tr4ckerBuilder {

    private Tr4cker tr4cker;

    public Tr4ckerBuilder() {
        tr4cker = new Tr4cker();
    }

    public Tr4ckerBuilder(Tr4cker tr4cker) {
        this.tr4cker = tr4cker;
    }

    /**
     * Adds a new {@code Task} to the {@code Tr4cker} that we are building.
     */
    public Tr4ckerBuilder withTask(Task task) {
        tr4cker.addTask(task);
        return this;
    }

    /**
     * Adds a new {@code Event} to the {@code Tr4cker} that we are building.
     */
    public Tr4ckerBuilder withEvent(Event event) {
        tr4cker.addEvent(event);
        return this;
    }

    public Tr4cker build() {
        return tr4cker;
    }

    /**
     * Adds a new {@code Module} to the {@code Tr4cker} that we are building.
     */
    public Tr4ckerBuilder withModule(Module module) {
        tr4cker.addModule(module);
        return this;
    }
}
