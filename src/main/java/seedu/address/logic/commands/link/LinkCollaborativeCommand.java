package seedu.address.logic.commands.link;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_URL;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.LinkCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.CollaborativeLink;
import seedu.address.model.task.Task;
import seedu.address.model.task.Todo;

/**
 * Add a Collaborative link to a todo.
 */
public class LinkCollaborativeCommand extends LinkCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " doc: Add collaborative link to todo. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_URL + "URL "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " doc "
            + PREFIX_DESCRIPTION + "proposal "
            + PREFIX_URL + "https://docs.google.com "
            + PREFIX_INDEX + "1";

    public static final String MESSAGE_SUCCESS = "New collaborative folder link added: %1$s";

    private final CollaborativeLink collaborativeLink;

    private final Index index;

    /**
     * Creates an LinkCollaborativeCommand to add collaborative link to a {@code Todo}
     */
    public LinkCollaborativeCommand(Index index, CollaborativeLink collaborativeLink) {
        requireNonNull(collaborativeLink);
        this.collaborativeLink = collaborativeLink;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TODO_DISPLAYED_INDEX);
        }
        try {
            if (lastShownList.get(index.getZeroBased()).getClass() != Todo.class) {
                throw new CommandException(Messages.MESSAGE_INVALID_INDEX_NOT_TODO);
            }

            Todo todoToEdit = (Todo) lastShownList.get(index.getZeroBased());
            Todo editedTodo;
            if (todoToEdit.getRecurrence() != null) {
                editedTodo = new Todo(todoToEdit.getDescription(), todoToEdit.getDeadline(),
                        todoToEdit.getRecurrence(), collaborativeLink, todoToEdit.getTags());
            } else {
                editedTodo = new Todo(todoToEdit.getDescription(), todoToEdit.getDeadline(),
                    collaborativeLink, todoToEdit.getTags());
            }

            model.setTask(todoToEdit, editedTodo);
            model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        } catch (ClassCastException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_INDEX_NOT_TODO);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, collaborativeLink.getDescription()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkCollaborativeCommand // instanceof handles nulls
                && collaborativeLink.equals(((LinkCollaborativeCommand) other).collaborativeLink));
    }
}
