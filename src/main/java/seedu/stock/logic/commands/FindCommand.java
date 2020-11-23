package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER_DESCRIPTION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE_DESCRIPTION;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.core.Messages;
import seedu.stock.commons.util.FindUtil;
import seedu.stock.model.Model;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.FieldContainsKeywordsPredicate;

/**
 * Finds and lists all stocks in stock book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all stocks whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Format: "
            + COMMAND_WORD + " {"
            + "[" + PREFIX_NAME + PREFIX_NAME_DESCRIPTION + "] "
            + "[" + PREFIX_SERIAL_NUMBER + PREFIX_SERIAL_NUMBER_DESCRIPTION + "] "
            + "[" + PREFIX_SOURCE + PREFIX_SOURCE_DESCRIPTION + "] "
            + "[" + PREFIX_LOCATION + PREFIX_LOCATION_DESCRIPTION + "] }\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "banana "
            + PREFIX_SOURCE + "SHENGSIONG";
    private static final Logger logger = LogsCenter.getLogger(FindCommand.class);

    private final List<FieldContainsKeywordsPredicate> predicates; // list of predicates to filter stocks by
    private final Predicate<Stock> combinedPredicates; // combined predicates to filter stocks by

    /**
     * Constructs a FindCommand object initialised with
     * a list of predicates to filter and find stocks by
     * @param predicates list of predicates to filter stocks
     */
    public FindCommand(List<FieldContainsKeywordsPredicate> predicates) {
        requireNonNull(predicates);
        this.predicates = predicates;
        this.combinedPredicates = FindUtil.getCombinedPredicatesWithOr(predicates);
    }

    @Override
    public CommandResult execute(Model model) {
        logger.log(Level.INFO, "Starting to execute find command");
        requireNonNull(model);

        // status message to show what user has searched for
        String statusMessage = "Searching for:\n"
                + predicates.stream().map(Object::toString)
                .reduce((predicate, next) -> predicate + ", " + next).get();

        // updates the filtered stock list based on the combined predicates to test and filter stocks
        // based on all of user's search fields
        model.updateFilteredStockList(combinedPredicates);

        logger.log(Level.INFO, "Finished finding stocks successfully");
        return new CommandResult(statusMessage + "\n"
                + String.format(Messages.MESSAGE_STOCKS_LISTED_OVERVIEW, model.getFilteredStockList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }

    @Override
    public String toString() {
        return predicates.toString();
    }

    public List<FieldContainsKeywordsPredicate> getPredicates() {
        return this.predicates;
    }
}
