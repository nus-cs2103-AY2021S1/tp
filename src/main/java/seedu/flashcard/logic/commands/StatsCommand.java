package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.flashcard.commons.core.Messages;
import seedu.flashcard.commons.core.index.Index;
import seedu.flashcard.logic.commands.exceptions.CommandException;
import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Flashcard;

/**
 * Shows a flashcard's statistics identified using it's displayed index from the list of flashcards.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the flashcard's statistics identified by the index number used in the displayed "
            + "flashcard list.\n"
            + "Parameters: INDEX (must be a positive integer greater than 0)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_FLASHCARD_STATISTICS_SUCCESS = "Viewed Flashcard's Statistics: %1$s";

    private final Index targetIndex;

    /**
     * Creates a stats command to show the targeted flashcard.
     *
     * @param targetIndex
     */
    public StatsCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Flashcard> lastShownList = model.getFilteredFlashcardList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }
        Flashcard flashcardToShowStatistics = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SHOW_FLASHCARD_STATISTICS_SUCCESS,
                flashcardToShowStatistics), targetIndex.getZeroBased());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommand // instanceof handles nulls
                && targetIndex.equals(((StatsCommand) other).targetIndex)); // state check
    }
}
