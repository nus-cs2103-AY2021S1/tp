package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MainCatalogue;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_A  = new TaskBuilder()
            .withTaskName(SampleDataUtil.getValidTask().get(0))
            .withTaskDescription(SampleDataUtil.getValidTask().get(1))
            .withDeadline(SampleDataUtil.getValidTask().get(2))
            .withProgress(SampleDataUtil.getValidTask().get(3))
            .withCompletion(SampleDataUtil.getValidTask().get(4)).build();
    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalProjects() {
        return new ArrayList<>(Arrays.asList(TASK_A));
    }
}
