package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_DELETE_SUCCESS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

/**
 * Deletes a Module identified using a Module's ModuleCode.
 * This delete operation requires a confirmation to be given.
 */
public class DeleteCommand extends Command {
    private final ModuleCode code;

    /**
     * Creates a DeleteCommand to delete the module with the specified {@code ModuleCode}
     *
     * @param code
     */
    public DeleteCommand(ModuleCode code) {
        this.code = code;
    }

    /**
     * Retrieves the module to be deleted.
     *
     * @param model the Model which the command operates on.
     * @return the module to be deleted.
     * @throws CommandException if the module cannot be found in Completed Modules.
     */
    public Module getModuleToDelete(Model model) throws CommandException {
        List<Module> modules = model.getGradPad().getModuleList();

        Optional<Module> moduleToDelete = modules.stream()
                .filter(module -> module.getModuleCode().equals(code)).findFirst();
        if (moduleToDelete.isEmpty()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MODULE, code.toString()));
        }
        return moduleToDelete.get();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Module moduleToDelete = getModuleToDelete(model);
        model.deleteModule(moduleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, moduleToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && code.equals(((DeleteCommand) other).code)); // state check
    }

    @Override
    public boolean requiresStall() {
        return true;
    }
}
