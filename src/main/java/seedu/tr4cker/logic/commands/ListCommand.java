package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_COMPLETED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_EXPIRED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import javafx.collections.ObservableList;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

/**
 * Lists all tasks in TR4CKER to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Here are your pending tasks:" + "\n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredExpiredTaskList(PREDICATE_SHOW_EXPIRED_TASKS);
        model.updateFilteredCompletedTaskList(PREDICATE_SHOW_COMPLETED_TASKS);

        ObservableList<Task> taskList = model.getFilteredPendingTaskList();
        String displayListNames = "";

        for (int i = 0; i < taskList.size(); i++) {
            displayListNames += (i + 1) + ". " + taskList.get(i).getName().toString() + "\n";
        }

        CommandResult commandResult = new CommandResult(MESSAGE_SUCCESS + displayListNames);
        return commandResult;
    }
}
