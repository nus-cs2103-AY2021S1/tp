package seedu.address.testutil;

import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

public class TypicalTasks {
    public static final String VALID_TASK_DEADLINE = "31-12-2020 10:00:00";
    public static final String VALID_TASK_NAME = "Plan for group meeting on milestone v1.4";
    public static final String VALID_TASK_DESCRIPTION = "";
    public static final double VALID_TASK_PROGRESS = 0;
    public static final double VALID_TASK_PROGRESS_HALF = 0.5;
    public static final boolean VALID_TASK_IS_DONE = true;
    // valid start date and end date to set a time range for task filter
    public static final String VALID_START_DATE = "11-11-2020";
    public static final String VALID_END_DATE = "22-11-2020";

    public static final Task PLAN_MEETING = new Task(VALID_TASK_NAME, VALID_TASK_DESCRIPTION,
            new Deadline(VALID_TASK_DEADLINE), VALID_TASK_PROGRESS, VALID_TASK_IS_DONE);
}
