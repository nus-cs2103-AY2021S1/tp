package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FitNus;
import seedu.address.model.Model;

/**
 * Clears fitNUS.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "fitNUS has been cleared. You can start afresh now!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFitNus(new FitNus());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
