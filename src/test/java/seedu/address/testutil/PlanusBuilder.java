package seedu.address.testutil;

import seedu.address.model.Planus;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building PlaNus objects.
 * Example usage: <br>
 *     {@code Planus planus = new PlanusBuilder().withTask("John", "Doe").build();}
 */
public class PlanusBuilder {

    private Planus planus;

    public PlanusBuilder() {
        planus = new Planus();
    }

    public PlanusBuilder(Planus planus) {
        this.planus = planus;
    }

    /**
     * Adds a new {@code Task} to the {@code Planus} that we are building.
     */
    public PlanusBuilder withTask(Task task) {
        planus.addTask(task);
        return this;
    }

    public Planus build() {
        return planus;
    }
}
