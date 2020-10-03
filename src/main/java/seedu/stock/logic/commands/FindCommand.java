package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.stock.commons.core.Messages;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;

import java.util.List;
import java.util.function.Predicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final List<Predicate<Stock>> predicates;
    private final Predicate<Stock> combinedPredicates;

    public FindCommand(List<Predicate<Stock>> predicates) {
        this.predicates = predicates;
        this.combinedPredicates = predicates.stream().reduce(x -> false, Predicate::or);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
       
        model.updateFilteredStockList(combinedPredicates);

        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStockList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
