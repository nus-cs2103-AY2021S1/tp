package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.EditTodoDescriptor;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;
import seedu.address.model.task.Title;
import seedu.address.model.task.ToDo;

/**
 * Edits the details of an existing recipe in the recipe book.
 */
public class EditTodoCommand extends Command {

    public static final String COMMAND_WORD = "editTodo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "tP Tasks "
            + PREFIX_DESCRIPTION + "Refactor tP Code "
            + PREFIX_PRIORITY + "High "
            + PREFIX_TAG + "cs2103 "
            + PREFIX_TAG + "project";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditTodoDescriptor editTodoDescriptor;

    /**
     * @param index                of the task in the filtered task list to edit
     * @param editTodoDescriptor details to edit the task with
     */
    public EditTodoCommand(Index index, EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(index);
        requireNonNull(editTodoDescriptor);

        this.index = index;
        this.editTodoDescriptor = editTodoDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTodo((ToDo) taskToEdit, editTodoDescriptor);

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Todo} with the details of {@code todoToEdit}
     * edited with {@code editTodoDescriptor}.
     */
    public static ToDo createEditedTodo(ToDo todoToEdit, EditTodoDescriptor editTodoDescriptor) {
        assert todoToEdit != null;

        Title updatedTitle = Optional.of(editTodoDescriptor.getTitle()).orElse(todoToEdit.getTitle());
        Description updatedDescription = Optional.of(editTodoDescriptor.getDescription())
                .orElse(todoToEdit.getDescription());
        Priority updatedPriority = Optional.of(editTodoDescriptor.getPriority()).orElse(todoToEdit.getPriority());
        Set<Tag> updatedTagList = Optional.of(editTodoDescriptor.getTagList()).orElse(todoToEdit.getTags());

        return new ToDo(updatedTitle, updatedDescription, updatedPriority, updatedTagList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTodoCommand)) {
            return false;
        }

        // state check
        EditTodoCommand e = (EditTodoCommand) other;
        return index.equals(e.index)
                && editTodoDescriptor.equals(e.editTodoDescriptor);
    }

}
