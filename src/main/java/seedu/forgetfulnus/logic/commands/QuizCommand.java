package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_QUIZ_ALREADY_STARTED;

import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Sets the program to quiz mode by hiding the English definitions on flashcards and starts quizzing.
 */
public class QuizCommand extends Command {

    public static final String COMMAND_WORD = "quiz";

    public static final String MESSAGE_SUCCESS = "Quiz started!";

    public static final String QUIZ_MODE_REMINDER = MESSAGE_QUIZ_ALREADY_STARTED;

    public static final String NO_FLASHCARD_MESSAGE = "There is no flashcard to quiz with.\n "
            + "Add a flashcard with "
            + "'add g/<GERMAN PHRASE> e/<ENGLISH PHRASE> d/[<DIFFICULTY>] s/[<GENDER>] [t/<TAG>]}'";

    public static final String FIRST_CARD = " Enter the definition of: ";
    public static final String TRY_COMMAND_REMINDER = "Type in: try <ATTEMPT> and enter.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;
    private static Logger logger = Logger.getLogger("Quiz");

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
        if (lastShownList.size() == 0) {
            logger.log(Level.INFO, "No flashcard in list");
            return new CommandResult(NO_FLASHCARD_MESSAGE);
        }
        ListIterator<FlashCard> iterator = lastShownList.listIterator();
        while (iterator.hasNext()) {
            FlashCard toEdit = iterator.next();
            FlashCard changeTo = toEdit.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(toEdit, changeTo);
        }
        model.updateFilteredPhraseList();
        model.setQuizMode(true);
        logger.log(Level.INFO, "Quiz starting...");
        return new CommandResult(String.format(MESSAGE_SUCCESS) + FIRST_CARD
                + lastShownList.get(model.getQuizModeIndex()).getGermanPhrase().toString() + "\n"
                + TRY_COMMAND_REMINDER);
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
