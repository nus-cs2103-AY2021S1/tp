package tp.acecs2103.model.task;

/**
 * Represents a Admin task.
 */
public class Admin extends Task {

    /**
     * Creates a {@code Admin} task.
     */
    public Admin(Index index, WeekNumber weekNumber, Description description,
                 OfficialDeadline officialDeadline,
                 CustomizedDeadline customizedDeadline, Remark remark, boolean isCustomized, boolean doneStatus) {
        super(index, weekNumber, description, officialDeadline, customizedDeadline, remark, isCustomized, doneStatus);
    }

    @Override
    public TaskCategory getCategory() {
        return TaskCategory.ADMIN;
    }
}
