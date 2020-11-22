package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.commons.core.Messages.MESSAGE_QUIZ_ALREADY_STARTED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.commons.core.index.Index;
import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Glossary;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;

/**
 * Begins a round of vocabulary self-testing with a specified
 * number of flashcards randomly chosen from the existing glossary.
 */
public class RandomQuizCommand extends Command {

    public static final String COMMAND_WORD = "random";

    public static final String MESSAGE_SUCCESS = "Random Quiz started!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Randomly selects specified number of flashcards and starts quiz\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String QUIZ_MODE_REMINDER = MESSAGE_QUIZ_ALREADY_STARTED;

    public static final String FIRST_CARD = " Enter the definition of: ";

    public static final String TRY_COMMAND_REMINDER = "Type in: try <ATTEMPT> and enter.";


    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final Index targetIndex;

    /**
     * Creates a RandomQuizCommand to with the specified {@code Index}
     */
    public RandomQuizCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setRandomQuizMode(true);
        List<FlashCard> lastShownList = model.getFilteredFlashCardList();

        if (targetIndex.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
        }

        List<FlashCard> randomList = new ArrayList<>(lastShownList);
        Collections.shuffle(randomList);
        while (randomList.size() > targetIndex.getOneBased()) {
            randomList.remove(randomList.size() - 1);
        }

        replaceGlossary(model, randomList);
        ListIterator<FlashCard> iterator = model.getFilteredFlashCardList().listIterator();
        while (iterator.hasNext()) {
            FlashCard toEdit = iterator.next();
            FlashCard changeTo = toEdit.copy();
            changeTo.updateShowingEnglish(false);
            model.setFlashCard(toEdit, changeTo);
        }
        model.setQuizMode(true);
        model.updateFilteredPhraseList();

        return new CommandResult(String.format(MESSAGE_SUCCESS)
                + FIRST_CARD
                + model
                .getFilteredFlashCardList()
                .get(model.getQuizModeIndex())
                .getGermanPhrase()
                .toString()
                + "\n"
                + TRY_COMMAND_REMINDER);
    }

    /**
     * Initialises a new glossary with the given randomList and replaces the
     * existing glossary in model.
     * @param model Model that the command is operating on.
     * @param randomList List containing randomly selected flashcards.
     */
    private void replaceGlossary(Model model, List<FlashCard> randomList) {
        Glossary randomGlossary = new Glossary();
        randomGlossary.setFlashCards(randomList);
        model.setGlossary(randomGlossary);
        model.updateFilteredPhraseList();
    }

    @Override
    public String getQuizModeReminder() {
        return QUIZ_MODE_REMINDER;
    }

    @Override
    public CommandType isQuizModeCommand() {
        return type;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RandomQuizCommand // instanceof handles nulls
                && targetIndex.equals(((RandomQuizCommand) other).targetIndex)); // state check
    }
}
