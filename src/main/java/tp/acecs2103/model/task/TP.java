package tp.acecs2103.model.task;

/**
 * Represents a TP task.
 */
public class TP extends Task {
    public TP(Index index, WeekNumber weekNumber,
              Description description, OfficialDeadline officialDeadline,
              CustomizedDeadline customizedDeadline, Remark remark, boolean isCustomized, boolean doneStatus) {
        super(index, weekNumber, description, officialDeadline, customizedDeadline, remark, isCustomized, doneStatus);
    }

    @Override
    public TaskCategory getCategory() {
        return TaskCategory.TP;
    }
}
