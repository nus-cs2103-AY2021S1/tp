package tp.acecs2103.model.task;

/**
 * Represents a IP task.
 */
public class IP extends Task {

    /**
     * Creates a {@code IP} task.
     */
    public IP(Index index, WeekNumber weekNumber,
          Description description, OfficialDeadline officialDeadline,
          CustomizedDeadline customizedDeadline, Remark remark, boolean isCustomized, boolean doneStatus) {
        super(index, weekNumber, description, officialDeadline, customizedDeadline, remark, isCustomized, doneStatus);
    }

    @Override
    public TaskCategory getCategory() {
        return TaskCategory.IP;
    }
}
