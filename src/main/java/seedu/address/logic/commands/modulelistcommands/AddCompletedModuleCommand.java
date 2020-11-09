package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_POINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULAR_CREDITS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Encapsulates methods and information to add a module into the module list.
 */
public class AddCompletedModuleCommand extends Command {
    public static final String COMMAND_WORD = "addcmodule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a completed module to the module list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MODULAR_CREDITS + "MODULAR CREDITS "
            + PREFIX_GRADE_POINT + "GRADE POINT "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2100 "
            + PREFIX_MODULAR_CREDITS + "4.0 "
            + PREFIX_GRADE_POINT + "5.0 "
            + PREFIX_TAG + "year1";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";

    private final Module toAdd;

    /**
     * Creates and initialises a new AddCompletedModuleCommand for the addition of a new module into the module list.
     *
     * @param module Module to be added.
     */
    public AddCompletedModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasModule(toAdd) || model.hasArchivedModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }
        model.addModule(toAdd);
        model.commitModuleList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCompletedModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddCompletedModuleCommand) other).toAdd));
    }

    @Override
    public String toString() {
        return COMMAND_WORD + " " + toAdd.toString();
    }
}
