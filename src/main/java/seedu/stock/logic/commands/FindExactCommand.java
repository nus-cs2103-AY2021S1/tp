package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.List;
import java.util.function.Predicate;

import seedu.stock.commons.core.Messages;
import seedu.stock.commons.util.FindUtil;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;

/**
 * Finds and lists all stocks in stock book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindExactCommand extends Command {

    public static final String COMMAND_WORD = "findexact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all stocks whose fields match all of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters (any combination, in any order, of the following four fields):\n"
            + PREFIX_NAME + " KEYWORD [more KEYWORDS which will be matched with Name field of stock]\n"
            + PREFIX_SOURCE + " KEYWORD [more KEYWORDS which will be matched with Source field of stock]\n"
            + PREFIX_SERIAL_NUMBER + " KEYWORD [more KEYWORDS which will be matched with SerialNumber field of stock]\n"
            + PREFIX_LOCATION + " KEYWORD [more KEYWORDS which will be matched with Location field of stock]\n"
            + "Example: " + COMMAND_WORD + " n/ pork 100grams  s/ farm";

    private final List<FieldContainsKeywordsPredicate> predicates; // list of predicates to filter stocks by
    private final Predicate<Stock> combinedPredicates; // combined predicates to filter stocks by

    /**
     * Constructs a FindCommand object initialised with
     * a list of predicates to filter and find stocks by
     * @param predicates list of predicates to filter stocks
     */
    public FindExactCommand(List<FieldContainsKeywordsPredicate> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
        this.combinedPredicates = FindUtil.getCombinedPredicatesWithAnd(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // status message to show what user has searched for
        String statusMessage = "Searching for:\n"
                + predicates.stream().map(Object::toString)
                .reduce((predicate, next) -> predicate + ", " + next).get();

        // updates the filtered stock list based on the combined predicates to test and filter stocks
        // based on all of user's search fields
        model.updateFilteredStockList(combinedPredicates);

        return new CommandResult(statusMessage + "\n"
                + String.format(Messages.MESSAGE_STOCKS_LISTED_OVERVIEW, model.getFilteredStockList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindExactCommand // instanceof handles nulls
                && predicates.equals(((FindExactCommand) other).predicates)); // state check
    }
}
