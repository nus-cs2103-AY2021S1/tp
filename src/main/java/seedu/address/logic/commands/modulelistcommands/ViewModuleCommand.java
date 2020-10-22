package seedu.address.logic.commands.modulelistcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewModuleCommand extends Command {

    public static final String COMMAND_WORD = "viewmodule";

    public static final String MESSAGE_SUCCESS = "Module details have been displayed successfully!\n"
            + "%1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views a module in the module list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + "1";
    public static final String MESSAGE_MODULE_NOT_FOUND = "The module requested cannot be found.";

    private ModuleName moduleName;
    /**
     * Creates an ViewCommand to view the specified {@code Module}
     *
     */
    public ViewModuleCommand(ModuleName moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Module> lastShownList = model.getFilteredModuleList();
        Module moduleToView = null;
        for (Module module : lastShownList) {
            if (module.getName().equals(moduleName)) {
                moduleToView = module;
                break;
            }
        }
        if (moduleToView == null) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToView));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
