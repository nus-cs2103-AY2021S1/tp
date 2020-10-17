package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_IS_DONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import seedu.address.model.task.Task;

public class TaskUtil {
    public static String getTaskCommand(Task t) {
        final StringBuilder builder = new StringBuilder();
        builder.append(PREFIX_PROJECT_NAME).append(" ").append(t.getTaskName()).append(" ")
                .append(PREFIX_TASK_PROGRESS).append(" ").append(t.getProgress()).append(" ")
                .append(PREFIX_TASK_IS_DONE).append(" ").append(t.isDone());
        return builder.toString();
    }
}
