package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FlashcardBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all flashcards. \n"
        + "Example: " + COMMAND_WORD + " (case sensitive, 'clear xx' is not allowed)";
    public static final String MESSAGE_SUCCESS = "Flashcard list has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFlashcardBook(new FlashcardBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
