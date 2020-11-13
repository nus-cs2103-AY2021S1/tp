package seedu.bagel.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.bagel.model.Model;
import seedu.bagel.model.flashcard.Flashcard;

/**
 * Flip and show next flashcard in the list.
 */
public class FlipCommand extends Command {

    public static final String COMMAND_WORD = "flip";

    public static final String MESSAGE_SUCCESS = "Flipped flashcard";

    private static int currentIndex;
    private static int nextIndex;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        ObservableList<Flashcard> flashcards = model.getBagel().getFlashcardList();
        ObservableList<Flashcard> filteredFlashcards = model.getFilteredFlashcardList();
        // find index of flashcard displaying next
        if (flashcards.size() == 0) {
            // empty flashcard
            nextIndex = 0;
        } else if (filteredFlashcards.size() == 0) {
            // flashcard is deleted
            nextIndex = currentIndex;
        } else if (filteredFlashcards.size() > 1) {
            // multiple flashcards are visible, show top flashcard
            nextIndex = flashcards.indexOf(filteredFlashcards.get(0));
        } else if (filteredFlashcards.size() == 1) {
            // single flashcard is visible
            currentIndex = flashcards.indexOf(filteredFlashcards.get(0));
            nextIndex = currentIndex + 1;
        }

        // go back to start of list when reached end
        if ((flashcards.size() - 1) <= currentIndex) {
            nextIndex = 0;
        }

        Predicate<Flashcard> nextFlashcard = flashcard -> flashcards.indexOf(flashcard) == nextIndex;
        model.updateFilteredFlashcardList(nextFlashcard);

        return new CommandResult(MESSAGE_SUCCESS);
    }

}
