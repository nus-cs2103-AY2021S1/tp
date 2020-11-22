package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Ends the ongoing round of vocabulary self-testing.
 */
public class EndQuizCommand extends Command {

    public static final String COMMAND_WORD = "end";

    public static final String MESSAGE_SUCCESS = "Quiz ended!";
    public static final String QUIZ_MODE_REMINDER = "You are currently not in quiz mode.";

    private static final CommandType type = CommandType.QUIZ_MODE;
    private static Logger logger = Logger.getLogger("End Quiz");

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.isQuizMode() || model.isRandomQuizMode()) {
            List<FlashCard> lastShownList = model.getFilteredFlashCardList();
            while (model.getQuizModeIndex() < lastShownList.size()) {
                FlashCard toEdit = lastShownList.get(model.getQuizModeIndex());
                FlashCard changeTo = toEdit.copy();
                changeTo.updateShowingEnglish(true);
                model.setFlashCard(toEdit, changeTo);
                model.addCardToScore(changeTo);
            }
            if (model.isRandomQuizMode()) {
                model.setRandomQuizMode(false);
            }
            model.saveScore();
            model.setQuizMode(false);
            model.updateFilteredPhraseList(unused -> true);
            logger.log(Level.INFO, "Quiz ended. Score: " + model.getQuizScore()
                    + " Total questions: " + model.getQuizTotalQuestions());
            return new CommandResult(String.format(MESSAGE_SUCCESS
                            + " Your score: %s / %s",
                    model.getQuizScore(), model.getQuizTotalQuestions()));
        } else {
            return new CommandResult(QUIZ_MODE_REMINDER);
        }
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }
}
