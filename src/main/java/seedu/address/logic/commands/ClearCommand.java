package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.HairStyleX;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "HairStyleX has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHairStyleX(new HairStyleX());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
