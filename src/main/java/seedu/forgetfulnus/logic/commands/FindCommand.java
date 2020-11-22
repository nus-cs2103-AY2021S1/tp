package seedu.forgetfulnus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.forgetfulnus.commons.core.Messages;
import seedu.forgetfulnus.model.Model;
import seedu.forgetfulnus.model.flashcard.GermanPhraseContainsKeywordsPredicate;

/**
 * Finds and lists all flashcards in the glossary which contain any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards which contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Montag Dienstag Mittwoch";

    public static final String QUIZ_MODE_REMINDER = COMMAND_WORD + " command cannot be used in quiz mode. "
            + "Enter 'end' to end quizzing.";

    private static final CommandType type = CommandType.NOT_QUIZ_MODE;

    private final GermanPhraseContainsKeywordsPredicate predicate;

    public FindCommand(GermanPhraseContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPhraseList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_PHRASES_LISTED_OVERVIEW,
                model.getFilteredFlashCardList().size()));
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
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
