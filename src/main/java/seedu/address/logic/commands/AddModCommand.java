package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DUPLICATE_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a person to the address book.
 */
public class AddModCommand extends Command {

    public static final String COMMAND_WORD = "addmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to FaculType.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_MODULE_NAME + "MODULE_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2103 "
            + PREFIX_MODULE_NAME + "Software Engineering ";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";

    private final Module moduleToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Module}
     */
    public AddModCommand(Module module) {
        requireNonNull(module);
        moduleToAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(moduleToAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_MODULE,
                    moduleToAdd.getModuleCode()));
        }

        model.addModule(moduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModCommand // instanceof handles nulls
                && moduleToAdd.equals(((AddModCommand) other).moduleToAdd));
    }
}
