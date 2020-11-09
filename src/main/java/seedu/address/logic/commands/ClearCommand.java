package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Library;
import seedu.address.model.Model;

/**
 * Clears the intellibrary.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Library has been cleared!";

    /**
     * Executes clear command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setLibrary(new Library());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
