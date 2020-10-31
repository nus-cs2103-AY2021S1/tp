package seedu.tasklist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.tasklist.model.Model;
import seedu.tasklist.model.ProductiveNus;

/**
 * Clears ProductiveNus.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "ProductiveNus has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setProductiveNus(new ProductiveNus());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
