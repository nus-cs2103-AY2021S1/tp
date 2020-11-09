package seedu.zookeep.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.zookeep.commons.core.Messages;
import seedu.zookeep.model.Model;
import seedu.zookeep.model.animal.AnimalContainsKeywordsPredicate;

/**
 * Finds and lists all animals in ZooKeepBook whose fields contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all animals whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " ahmeng buttercup coco";

    private final AnimalContainsKeywordsPredicate predicate;

    public FindCommand(AnimalContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAnimalList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ANIMALS_LISTED_OVERVIEW, model.getFilteredAnimalList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
