package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.bagel.model.Bagel;
import seedu.bagel.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Flashcards in Bagel has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setBagel(new Bagel());
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
