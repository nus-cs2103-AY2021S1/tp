package seedu.address.testutil;

import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.task.TaskCliSyntax.PREFIX_TASK_NUMBER;
import static seedu.address.model.task.DateTimeDue.ALLOWED_DATETIME_FORMATS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.task.AddTaskCommand;
import seedu.address.logic.commands.task.EditTaskCommand;
import seedu.address.model.task.Task;

/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns an add command string for adding the {@code Task}.
     */
    public static String getAddTaskCommand(Task task, int roomNumber) {
        return AddTaskCommand.COMMAND_WORD + " " + getTaskDetails(task, roomNumber);
    }

    /**
     * Returns an edit command string for editing the {@code Task}.
     */
    public static String getEditTaskCommand(Task task, int roomNumber, Index taskIndex) {
        return EditTaskCommand.COMMAND_WORD + " " + getTaskDetails(task, roomNumber, taskIndex);
    }

    /**
     * Returns the part of command string for the given {@code Task}'s details.
     */
    public static String getTaskDetails(Task task) {
        final StringBuilder builder = new StringBuilder();

        builder.append(PREFIX_DESCRIPTION + task.getDescription().toString() + " ");

        task.getDueAt().getValue().ifPresent((dueAt) -> {
            // DateTimeDue.toString() may return a different string that does not correspond to
            // an allowed date-time format. Here, dueAt is formatted with the first allowed date-time
            // format to obtain a proper command string.
            // ALLOWED_DATETIME_FORMATS.length > 0 or a compiler-time error will occur.
            String formattedDueAt = dueAt.format(ALLOWED_DATETIME_FORMATS[0]);
            builder.append(PREFIX_DUE_DATE + formattedDueAt);
        });

        return builder.toString();
    }

    /**
     * Returns the part of command string for the given {@code Task}'s details.
     * Includes a valid room number given by {@code roomIndex} in the command string.
     */
    public static String getTaskDetails(Task task, int roomNumber) {
        final StringBuilder builder = new StringBuilder();

        builder.append(getTaskDetails(task));
        // Use any valid room number as room number not stored in task
        builder.append(" " + PREFIX_ROOM_NUMBER + roomNumber);

        return builder.toString();
    }

    /**
     * Returns the part of command string for the given {@code Task}'s details.
     * Includes a valid room number given by {@code roomIndex} and a valid task number
     * given by {@code taskIndex} in the command string.
     */
    public static String getTaskDetails(Task task, int roomNumber, Index taskIndex) {
        final StringBuilder builder = new StringBuilder();

        builder.append(getTaskDetails(task, roomNumber));
        // Use any valid task number as task number not stored in task
        builder.append(" " + PREFIX_TASK_NUMBER + String.valueOf(taskIndex.getOneBased()));

        return builder.toString();
    }
}
