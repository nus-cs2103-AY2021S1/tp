package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;

/**
 * Review flashcards one by one.
 */
public class ReviewCommand extends Command {

    public static final String COMMAND_WORD = "review";

    public static final String MESSAGE_INITIATE_REVIEW_SUCCESS = "Entered review mode";
    public static final String MESSAGE_INITIATE_REVIEW_ERROR = "There are no flashcards to review";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredFlashcardList().size() == 0) {
            throw new CommandException(MESSAGE_INITIATE_REVIEW_ERROR);
        }

        return new CommandResult(MESSAGE_INITIATE_REVIEW_SUCCESS, false, false, true, false);
    }
}
