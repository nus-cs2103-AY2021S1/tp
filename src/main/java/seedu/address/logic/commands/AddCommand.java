package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a Module to the GradPad.
 */
public class AddCommand extends Command {
    private final Module toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     *
     * @param module the module to be added.
     */
    public AddCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_MODULE, toAdd.getModuleCode()));
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_ADD_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
