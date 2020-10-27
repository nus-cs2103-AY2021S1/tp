package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.EditDeadlineDescriptor;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDate;
import seedu.address.model.task.Title;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "editDeadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditDeadlineDescriptor editDeadlineDescriptor;

    /**
     * @param index                of the task in the filtered task list to edit
     * @param editDeadlineDescriptor details to edit the task with
     */
    public EditDeadlineCommand(Index index, EditDeadlineDescriptor editDeadlineDescriptor) {
        requireNonNull(index);
        requireNonNull(editDeadlineDescriptor);

        this.index = index;
        this.editDeadlineDescriptor = editDeadlineDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedDeadline((Deadline) taskToEdit, editDeadlineDescriptor);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Deadline} with the details of {@code deadlineToEdit}
     * edited with {@code editDeadlineDescriptor}.
     */
    public static Deadline createEditedDeadline(Deadline deadlineToEdit,
                                                EditDeadlineDescriptor editDeadlineDescriptor) {
        assert deadlineToEdit != null;

        Title updatedTitle = Optional.of(editDeadlineDescriptor.getTitle()).orElse(deadlineToEdit.getTitle());
        Description updatedDescription = Optional.of(editDeadlineDescriptor.getDescription())
                .orElse(deadlineToEdit.getDescription());
        Priority updatedPriority = Optional.of(editDeadlineDescriptor.getPriority())
                .orElse(deadlineToEdit.getPriority());
        TaskDate updatedTaskDeadline = Optional.of(editDeadlineDescriptor.getTaskDeadline())
                .orElse(deadlineToEdit.getDeadlineDate());
        Set<Tag> updatedTagList = Optional.of(editDeadlineDescriptor.getTagList()).orElse(deadlineToEdit.getTags());

        return new Deadline(updatedTitle, updatedDescription, updatedPriority, updatedTaskDeadline, updatedTagList);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDeadlineCommand)) {
            return false;
        }

        // state check
        EditDeadlineCommand e = (EditDeadlineCommand) other;
        return index.equals(e.index)
                && editDeadlineDescriptor.equals(e.editDeadlineDescriptor);
    }

}
