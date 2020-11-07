package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.flashcard.logic.parser.CliSyntax.PREFIX_FLAG;
import static seedu.flashcard.logic.parser.ViewCommandParser.FLAG_ANSWER;

import java.util.List;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Views a flashcard identified using it's displayed index from the list of flashcards.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the flashcard identified by the index number used in the displayed flashcard list. "
            + "Specify -a to show answer and notes too.\n"
            + "Parameters: INDEX (must be a positive integer greater than 0)"
            + " [" + PREFIX_FLAG + FLAG_ANSWER + "]\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_FLASHCARD_SUCCESS = "Viewed Flashcard: %1$s";

    private final Index targetIndex;
    private final boolean showAnswer;

    /**
     * Creates a view command to show the targeted flashcard.
     * @param targetIndex
     * @param showAnswer
     */
    public ViewCommand(Index targetIndex, boolean showAnswer) {
        this.targetIndex = targetIndex;
        this.showAnswer = showAnswer;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }
        Flashcard flashcardToView = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_VIEW_FLASHCARD_SUCCESS,
                flashcardToView), targetIndex.getZeroBased(), showAnswer);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)
                && showAnswer == ((ViewCommand) other).showAnswer); // state check
    }
}
