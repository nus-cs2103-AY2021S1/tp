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

    public static final String TASK_A_NAME = PREFIX_TASK_NAME+" "+SampleDataUtil.getValidTask().get(0);
    public static final String TASK_A_PROGRESS = PREFIX_TASK_PROGRESS+" "+SampleDataUtil.getValidTask().get(3);
    public static final String TASK_A_DEADLINE = PREFIX_TASK_DEADLINE+" "+SampleDataUtil.getValidTask().get(2);

//    private static final ArrayList<String> validTask = new ArrayList<>(Arrays.asList(
//            "Write testcases",
//            "testcases for storage, person and project class",
//            "10-10-2020 23:59:00",
//            "10.0",
//            "false"
//    ));

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
