package seedu.address.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Todo;

/**
 * Adds a todo to the address book.
 */
public class AddTodoCommand extends AddCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + " todo: Adds a todo to the TodoList. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_TIME + "TIME "
            + "[" + PREFIX_RECURRING + "RECURRING] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " todo "
            + PREFIX_DESCRIPTION + "Finish assignment "
            + PREFIX_DATE + "23-11-2020 "
            + PREFIX_TIME + "2359 "
            + PREFIX_TAG + "CS2100";

    public static final String DATE_TIME_USAGE = "Date and time format should be: date/DD-MM-YYYY time/HHmm (24-hour)";

    public static final String MESSAGE_SUCCESS = "New todo added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO = "This todo already exists in the TodoList";

    private final Todo toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Todo}
     */
    public AddTodoCommand(Todo todo) {
        requireNonNull(todo);
        toAdd = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }
        model.addTodo(toAdd);
        model.getDueSoonTaskList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescriptionDateTime()), "TASK");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTodoCommand // instanceof handles nulls
                && toAdd.equals(((AddTodoCommand) other).toAdd));
    }
}
