package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.FlashcardDeck;
import seedu.address.model.Model;

/**
 * Clears the flashcard deck.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Flashcard Deck has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setFlashcardDeck(new FlashcardDeck());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
