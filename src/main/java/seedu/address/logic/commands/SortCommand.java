package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDateComparator;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all tasks based on their time.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "All results sorted";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();
        lastShownList.sort(new TaskDateComparator());
        TaskDateComparator tdc = new TaskDateComparator();
        System.out.println(lastShownList.get(0).getTitle());
        System.out.println(lastShownList.get(2).getTitle());
        System.out.println(tdc.getDate(lastShownList.get(0)));
        System.out.println(tdc.getDate(lastShownList.get(2)));
        System.out.println(tdc.compare(lastShownList.get(0), lastShownList.get(2)));

        for (Task t : lastShownList){
            System.out.println(t.getTitle());
        }

        return  new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
    }
}
