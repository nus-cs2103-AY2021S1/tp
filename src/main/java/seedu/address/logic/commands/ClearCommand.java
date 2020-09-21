package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.MainCatalogue;
import seedu.address.model.Model;

/**
 * Clears the main catalogue.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setProjectCatalogue(new MainCatalogue());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
