package quickcache.logic.commands;

import static java.util.Objects.requireNonNull;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import quickcache.commons.core.Messages;
import quickcache.model.Model;
import quickcache.model.flashcard.FlashcardPredicate;

/**
 * Finds and lists all flashcards in QuickCache with tags equivalent to any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose question "
            + "and tags respectively contains all of\n"
            + "[" + PREFIX_QUESTION + "KEYWORD]...\n"
            + "[" + PREFIX_TAG + "TAG]...\n"
            + " and displays them as a list with index numbers.\n"
            + "Parameters: [" + PREFIX_QUESTION + "KEYWORD]..." + " [" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_QUESTION + "What "
            + PREFIX_TAG + "CS2100";

    private final FlashcardPredicate predicate;

    public FindCommand(FlashcardPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredFlashcardList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, model.getFilteredFlashcardList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
