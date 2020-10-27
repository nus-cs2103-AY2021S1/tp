package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.ModuleList;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Module list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setModuleList(new ModuleList());
        model.commitModuleList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
