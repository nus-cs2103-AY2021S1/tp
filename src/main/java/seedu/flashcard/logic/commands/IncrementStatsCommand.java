package seedu.flashcard.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.Statistics;

/**
 * Increments the statistics of a flashcard.
 */
public class IncrementStatsCommand extends Command {
    public static final String MESSAGE_INCREMENT_STATS_FLASHCARD_SUCCESS = "Increment Flashcard Stats: %1$s";
    private final Flashcard flashcard;
    private final boolean isCorrect;


    /**
     * Creates an IncrementStatsCommand to increment the statistics of the given {@code Flashcard}
     * @param flashcard Flashcard that contains the statistics to be incremented.
     * @param isCorrect Represents the correctness of the user's answer to the flashcard.
     */
    public IncrementStatsCommand(Flashcard flashcard, boolean isCorrect) {
        this.flashcard = flashcard;
        this.isCorrect = isCorrect;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Statistics statistics = flashcard.getStatistics().incrementReviewFrequency();
        if (isCorrect) {
            statistics = statistics.incrementSuccessFrequency();
        }
        Flashcard editedFlashcard = new Flashcard(flashcard.getQuestion(), flashcard.getAnswer(),
                flashcard.getCategory(), flashcard.getNote(), flashcard.getRating(), flashcard.getTags(),
                flashcard.getDiagram(), statistics, flashcard.isFavourite());

        model.setFlashcard(flashcard, editedFlashcard);
        return new CommandResult(String.format(MESSAGE_INCREMENT_STATS_FLASHCARD_SUCCESS, editedFlashcard));
    }
}
