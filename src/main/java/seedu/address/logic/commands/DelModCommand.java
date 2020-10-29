package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_DOES_NOT_EXIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;

public class DelModCommand extends Command {

    public static final String COMMAND_WORD = "delmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a module from FaculType.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2103 ";

    public static final String MESSAGE_SUCCESS = "Module deleted: %1$s";

    private final ModuleCode moduleCode;

    /**
     * @param moduleCode module code of the module to be deleted
     */
    public DelModCommand(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasModuleCode(this.moduleCode)) {
            throw new CommandException(String.format(MESSAGE_MODULE_DOES_NOT_EXIST, moduleCode));
        }

        model.deleteModule(this.moduleCode);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleCode));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DelModCommand // instanceof handles nulls
                && moduleCode.equals(((DelModCommand) other).moduleCode));
    }
}
