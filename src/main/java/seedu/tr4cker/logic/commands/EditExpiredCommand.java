package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_EXPIRED_TASKS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.List;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

public class EditExpiredCommand extends EditCommand {

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditExpiredCommand(Index index, EditTaskDescriptor editTaskDescriptor,
                              EditTodoDescriptor editTodoDescriptor) {
        super(index, editTaskDescriptor, editTodoDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredExpiredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (!taskToEdit.isEdited(editedTask)) {
            throw new CommandException(MESSAGE_UNCHANGED);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);
        model.updateFilteredExpiredTaskList(PREDICATE_SHOW_EXPIRED_TASKS);

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
        commandResult.setHomeTab();
        return commandResult;
    }
}
