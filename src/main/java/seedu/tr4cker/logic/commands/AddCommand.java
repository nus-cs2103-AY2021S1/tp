package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.task.Task;

/**
 * Adds a task to TR4CKER.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Adds a task to TR4CKER\n"
            + "Compulsory Parameters: " + PREFIX_NAME + "NAME " + PREFIX_TASK_DESCRIPTION + "TASKDESCRIPTION\n"
            + "Optional Parameters: " + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_MODULE_CODE + "MODULECODE (must be of existing module) "
            + PREFIX_TAG + "TAG...(more than one tag is allowed)\n"
            + "(E.g. " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T tP "
            + PREFIX_DEADLINE + "10-Oct-2021 1010 "
            + PREFIX_TASK_DESCRIPTION + "Update User Guide "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_TAG + "CS2103T "
            + PREFIX_TAG + "UG" + ")";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TR4CKER.";
    public static final String MESSAGE_INVALID_MODULE = "Given module does not exist in TR4CKER.";

    private final Task toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Task}.
     */
    public AddCommand(Task task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        if (!model.hasValidModuleField(toAdd)) {
            throw new CommandException(MESSAGE_INVALID_MODULE);
        }

        model.addTask(toAdd);

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        commandResult.setHomeTab();
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }

}
