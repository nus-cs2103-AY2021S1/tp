package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.stock.logic.parser.CliSyntax.*;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;

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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all stocks whose fields contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters (any combination, in any order, of the following four fields):\n"
            + PREFIX_NAME + " KEYWORD [more KEYWORDS which will be matched with Name field of stock]\n"
            + PREFIX_SOURCE + " KEYWORD [more KEYWORDS which will be matched with Source field of stock]\n"
            + PREFIX_SERIALNUMBER + " KEYWORD [more KEYWORDS which will be matched with SerialNumber field of stock]\n"
            + PREFIX_LOCATION + " KEYWORD [more KEYWORDS which will be matched with Location field of stock]\n"
            + "Example: " + COMMAND_WORD + " n/ pork 100grams  s/ farm";

    private final List<Predicate<Stock>> predicates;
    private final Predicate<Stock> combinedPredicates;

    public FindCommand(List<Predicate<Stock>> predicates) {
        this.predicates = predicates;
        this.combinedPredicates = predicates.stream().reduce(x -> false, Predicate::or);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        String statusMessage = "Searching for:\n"
                + predicates.stream().map(Object::toString)
                .reduce((pred, next) -> pred + ", " + next).get();

        model.updateFilteredStockList(combinedPredicates);

        return new CommandResult(statusMessage + "\n"
                + String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStockList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicates.equals(((FindCommand) other).predicates)); // state check
    }
}
