package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.forgetfulnus.logic.commands.exceptions.CommandException;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.flashcard.Order;

/**
 * Adds a phrase to the glossary.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a phrase to the glossary. "
            + "Parameters: "
            + PREFIX_GERMAN_PHRASE + "GERMAN PHRASE "
            + PREFIX_ENGLISH_PHRASE + "ENGLISH PHRASE "
            + PREFIX_DIFFICULTY_TAG + "DIFFICULTY LEVEL "
            + PREFIX_GENDER_TAG + "GENDER "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GERMAN_PHRASE + "Vergesslichkeit "
            + PREFIX_ENGLISH_PHRASE + "forgetfulness "
            + PREFIX_DIFFICULTY_TAG + "hard "
            + PREFIX_GENDER_TAG + "f "
            + PREFIX_TAG + "chapter1 ";

    public static final String MESSAGE_SUCCESS = "New phrase added: %1$s";
    public static final String MESSAGE_DUPLICATE_PHRASE = "This phrase already exists in the glossary";
    public static final String QUIZ_MODE_REMINDER = "Flashcards cannot be added in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final FlashCard toAdd;

    /**
     * Creates an AddCommand to add the specified {@code FlashCard}
     */
    public AddCommand(FlashCard flashCard) {
        requireNonNull(flashCard);
        toAdd = flashCard;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasFlashCard(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PHRASE);
        }
        int size = model.getGlossary().getFlashCardList().size();
        if (Order.getNextOrderOfAddition() == -1) {
            toAdd.setOrder(size + 1);
        }
        model.addFlashCard(toAdd);
        size = model.getGlossary().getFlashCardList().size();
        Order.setNextOrderOfAddition(size + 1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
