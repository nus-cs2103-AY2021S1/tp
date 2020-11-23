package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_DELETE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import java.util.List;
import java.util.Objects;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.module.Module;

/**
 * Allows user to add or delete modules from TR4CKER.
 */
public class ModuleCommand extends Command {

    public static final String COMMAND_WORD = "modules";

    public static final String MESSAGE_SWITCH_TAB_USAGE = COMMAND_WORD + ": Switches to Module tab\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Adds/deletes a module from TR4CKER\n"
            + "Add Module Parameters: "
            + PREFIX_MODULE_NAME + "MODULE_NAME " + PREFIX_MODULE_CODE + "MODULE_CODE\n"
            + "Delete Module Parameters: "
            + PREFIX_MODULE_DELETE + "INDEX (must be a positive integer and valid index number)\n"
            + "Example Add Module: " + COMMAND_WORD + " "
            + PREFIX_MODULE_NAME + "Discrete Structures "
            + PREFIX_MODULE_CODE + "CS1231S\n"
            + "Example Delete Module: " + COMMAND_WORD + " "
            + PREFIX_MODULE_DELETE + "2";

    public static final String MESSAGE_DELETE_MODULE_USAGE = COMMAND_WORD
            + ": Deletes a module from module list\n"
            + "Parameters: "
            + PREFIX_MODULE_DELETE + "INDEX\n"
            + "Note: INDEX must be a valid index!\n"
            + "Examples: \n"
            + COMMAND_WORD + " " + PREFIX_MODULE_DELETE + "1\n";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to Module tab!";
    public static final String MESSAGE_MODULE_ADD_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_MODULE_DELETE_SUCCESS = "Deleted module: %1$s";

    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in TR4CKER.";
    public static final String MESSAGE_STILL_HAS_TASKS = "This module still has related tasks in TR4CKER.";

    private final Module toAdd;
    private final Index toDeleteIndex;

    /**
     * Constructor for ModuleCommand when user wants to switch to Module tab.
     */
    public ModuleCommand() {
        this.toAdd = null;
        this.toDeleteIndex = null;
    }

    /**
     * Constructor for ModuleCommand when user wants to add a module.
     */
    public ModuleCommand(Module toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
        this.toDeleteIndex = null;
    }

    /**
     * Constructor for ModuleCommand when user wants to delete a module.
     */
    public ModuleCommand(Index toDeleteIndex) {
        requireNonNull(toDeleteIndex);
        this.toAdd = null;
        this.toDeleteIndex = toDeleteIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (toAdd == null && toDeleteIndex == null) {
            return CommandResult.createModuleTabSwitchCommandResult(MESSAGE_SWITCH_TAB_SUCCESS);
        }

        if (toDeleteIndex == null) {
            assert toAdd != null;

            if (model.hasModule(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_MODULE);
            }

            model.addModule(toAdd);
            return new CommandResult(String.format(MESSAGE_MODULE_ADD_SUCCESS, toAdd));
        }

        List<Module> lastShownList = model.getFilteredModuleList();
        if (toDeleteIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
        }

        Module moduleToDelete = lastShownList.get(toDeleteIndex.getZeroBased());
        if (model.hasRelatedIncompleteTasks(moduleToDelete)) {
            throw new CommandException(MESSAGE_STILL_HAS_TASKS);
        }
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_MODULE_DELETE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCommand // instanceof handles nulls
                && (Objects.equals(toAdd, ((ModuleCommand) other).toAdd))
                && (Objects.equals(toDeleteIndex, ((ModuleCommand) other).toDeleteIndex)));
    }
}
