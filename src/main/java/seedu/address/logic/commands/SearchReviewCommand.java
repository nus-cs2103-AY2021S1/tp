package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.NumberContainsKeywordPredicate;
import seedu.address.ui.Mode;

/**
 * Searches for the review of the corresponding book.
 */
public class SearchReviewCommand extends Command {

    public static final String COMMAND_WORD = "searchReview";
    public static final String SUGGESTION = "searchReview n/<book name>\n" + "searchReview i/<isbn>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Search for the stocking of all the books with"
            + "the corresponding keyword and shows them as a list.\n"
            + "Parameters: [" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_ISBN + "ISBN]\n"
            + "Example: " + COMMAND_WORD + " " + CliSyntax.PREFIX_NAME + "a brief history of time";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private Predicate<Book> predicate;

    /**
     * Creates a StockCommand to search for the stocking information in each location.
     *
     * @param names The list of names that are used as keyword.
     * @param numbers The list of numbers that are used as keyword.
     */
    public SearchReviewCommand(List<String> names, List<String> numbers) {
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate;
        NumberContainsKeywordPredicate numberContainsKeywordPredicate;
        if (names != null && !names.get(0).equals("") && numbers != null) {
            nameMatchesKeywordsPredicate = new NameMatchesKeywordPredicate(names);
            numberContainsKeywordPredicate = new NumberContainsKeywordPredicate(numbers);
            predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                    && numberContainsKeywordPredicate.test(book));
        } else if (names != null && !names.get(0).equals("")) {
            predicate = new NameMatchesKeywordPredicate(names);
        } else if (numbers != null) {
            predicate = new NumberContainsKeywordPredicate(numbers);
        } else {
            predicate = Model.PREDICATE_SHOW_ALL_BOOKS;
        }
    };

    /**
     * Execute search review command on model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(predicate, Mode.REVIEW);
        logger.info("Show the stocking information in the review book card");
        return new CommandResult(String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW,
                model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof SearchReviewCommand
                && this.predicate.equals(((SearchReviewCommand) other).predicate));
    }
}
