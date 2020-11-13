package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "addMod";
    public static final String MESSAGE_SUCCESS = "New module added: %s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Module.\n"
            + "Parameters: "
            + PREFIX_MODULE + "MODULE_CODE \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE + "CS2100";
    public static final String MESSAGE_NOT_IN_MODULE_VIEW = "You are currently not in the Module view. \n"
            + "Run listMod to go back to the Module view.";


    private final Module toAdd;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInModuleView()) {
            throw new CommandException(MESSAGE_NOT_IN_MODULE_VIEW);
        }

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
