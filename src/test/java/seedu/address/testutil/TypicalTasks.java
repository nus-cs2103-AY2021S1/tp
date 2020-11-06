package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final String TASK_A_NAME = " "+PREFIX_TASK_NAME+" "+SampleDataUtil.getValidTask().get(0);
    public static final String TASK_B_NAME = " "+PREFIX_TASK_NAME+" "+SampleDataUtil.getTask1().get(0);
    public static final String TASK_A_PROGRESS = " "+PREFIX_TASK_PROGRESS+" "+SampleDataUtil.getValidTask().get(3);
    public static final String TASK_B_PROGRESS = " "+PREFIX_TASK_PROGRESS+" "+SampleDataUtil.getTask1().get(3);
    public static final String TASK_A_DEADLINE = " "+PREFIX_TASK_DEADLINE+" "+SampleDataUtil.getValidTask().get(2);
    public static final String TASK_B_DEADLINE = " "+PREFIX_TASK_DEADLINE+" "+SampleDataUtil.getTask1().get(2);

    public static final Task TASK_A = new TaskBuilder()
            .withTaskName(SampleDataUtil.getValidTask().get(0))
            .withTaskDescription(SampleDataUtil.getValidTask().get(1))
            .withDeadline(SampleDataUtil.getValidTask().get(2))
            .withProgress(SampleDataUtil.getValidTask().get(3))
            .withCompletion(SampleDataUtil.getValidTask().get(4)).build();

    private TypicalTasks() {
    } // prevents instantiation

    public static List<Task> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(TASK_A));
    }
}
