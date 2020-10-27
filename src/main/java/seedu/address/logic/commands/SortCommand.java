package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Event;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all tasks based on their time.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "All results sorted";

    class SortByDate implements Comparator<Task> {
        private TaskDate getDate(Task t) {
            if (t instanceof Deadline)
                return ((Deadline) t).getDeadlineDate();
            else if (t instanceof Event)
                return ((Event) t).getEventDate();
            else
                return null;
        }

        @Override
        public int compare(Task task1, Task task2) {
            TaskDate date1 = getDate(task1), date2 = getDate(task2);
            if (date1 != null && date2 != null) return date1.compareTo(date2);
            else if (date1 == null) return -1;
            else return 0;
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        lastShownList.sort(new SortByDate());

        for (Task t : lastShownList){
            System.out.println(t.getTitle());
        }

        return null;
    }
}
