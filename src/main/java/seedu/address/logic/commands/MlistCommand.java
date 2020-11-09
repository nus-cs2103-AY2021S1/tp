package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import seedu.address.model.Model;

/**
 * Lists all modules in the address book to the user.
 */
public class MlistCommand extends Command {

    public static final String COMMAND_WORD = "mlist";
    public static final String MESSAGE_SUCCESS = "Listed all modules";
    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + " : Lists all modules\n"
        + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MlistCommand); // instanceof handles nulls
    }
}
