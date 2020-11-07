package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.HospifyBook;
import seedu.address.model.Model;

/**
 * Clears Hospify.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Hospify has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": clears all the patients stored in Hospify";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHospifyBook(new HospifyBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
