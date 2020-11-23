package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

public class DeleteExpiredCommand extends DeleteCommand {

    public DeleteExpiredCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredExpiredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTask(taskToDelete);

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete));
        commandResult.setHomeTab();
        return commandResult;
    }
}
