package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashcard.model.FlashcardDeck;
import seedu.flashcard.model.Model;

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
