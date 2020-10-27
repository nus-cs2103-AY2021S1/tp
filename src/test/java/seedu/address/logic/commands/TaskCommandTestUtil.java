package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;
import static seedu.address.testutil.TypicalPersons.VALID_GITNAME;

import seedu.address.model.project.Deadline;
import seedu.address.model.task.Task;

public class TaskCommandTestUtil {
    public static final String VALID_TASK_DEADLINE = "31-12-2020 10:00:00";
    public static final String VALID_TASK_NAME = "Plan for group meeting on milestone v1.4";
    public static final String VALID_TASK_DESCRIPTION = "";
    public static final double VALID_TASK_PROGRESS = 0;
    public static final double VALID_TASK_PROGRESS_HALF = 0.5;
    public static final boolean VALID_TASK_IS_DONE = true;
    // valid start date and end date to set a time range for task filter
    public static final String VALID_START_DATE = "11-11-2020";
    public static final String VALID_END_DATE = "22-11-2020";
    // string descriptions related to Task Filter
    public static final String TASK_NAME_DESC = " " + PREFIX_TASK_NAME + VALID_TASK_NAME;
    public static final String TASK_DEADLINE_DESC = " " + PREFIX_TASK_DEADLINE + VALID_TASK_DEADLINE;
    public static final String TASK_PROGRESS_DESC = " " + PREFIX_TASK_PROGRESS + VALID_TASK_PROGRESS;
    public static final String TASK_IS_DONE_DESC = " " + PREFIX_TASK_IS_DONE + VALID_TASK_IS_DONE;
    public static final String TASK_ASSIGNEE_DESC = " " + PREFIX_TASK_ASSIGNEE + VALID_GITNAME;
    public static final String TASK_TIME_RANGE_DESC = " " + PREFIX_START_DATE + VALID_START_DATE
        + " " + PREFIX_END_DATE + VALID_END_DATE;
    public static final String INVALID_TASK_DEADLINE_DESC_A = " "
        + PREFIX_TASK_DEADLINE + "14/02/2020 22:00:00"; // use "/" instead of "-"
    public static final String INVALID_TASK_DEADLINE_DESC_B = " "
        + PREFIX_TASK_DEADLINE + "31-02-2020 22:00:00"; // February does not have 31 days
    public static final String INVALID_TASK_DEADLINE_DESC_C = " "
        + PREFIX_TASK_DEADLINE + "14-2-2020 22:00:00"; // should be in the form of dd-mm-yyyy
    public static final String INVALID_TASK_PROGRESS_DESC_A = " "
        + PREFIX_TASK_PROGRESS + "half"; // should input a number
    public static final String INVALID_TASK_PROGRESS_DESC_B = " "
        + PREFIX_TASK_PROGRESS + "-1"; // progress should only range from 0 to 100
    public static final String INVALID_TASK_PROGRESS_DESC_C = " "
        + PREFIX_TASK_PROGRESS + "256"; // progress should only range from 0 to 100
    public static final String INVALID_TASK_IS_DONE_DESC = " "
        + PREFIX_TASK_IS_DONE + "finished"; // done status should only be "true" or "false"
    public static final String INVALID_TASK_ASSIGNEE_DESC = " "
        + PREFIX_TASK_ASSIGNEE + "random name @$"; // assignee's github username is invalid
    public static final String INVALID_TIME_RANGE_DESC_A = " "
        + PREFIX_START_DATE + "09/01/2020"
        + " " + PREFIX_END_DATE + "02/04/2020"; // use "/" instead of "-"
    public static final String INVALID_TIME_RANGE_DESC_B = " "
        + PREFIX_START_DATE + "09-06-2020"
        + " " + PREFIX_END_DATE + "02-04-2020"; // the end date should not be earlier than the start date
    public static final Task PLAN_MEETING = new Task(VALID_TASK_NAME, VALID_TASK_DESCRIPTION,
            new Deadline(VALID_TASK_DEADLINE), VALID_TASK_PROGRESS, VALID_TASK_IS_DONE);
}
