package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;
import seedu.address.ui.Mode;

/**
 * Finds and lists all books with the maximum number of times that was borrowed.
 * Keyword matching is case insensitive.
 */
public class FindMostPopularCommand extends Command {

    public static final String COMMAND_WORD = "findpop";
    public static final String SUGGESTION = "findpop <category>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Find the list of books"
            + "of a certain category that is most popular(borrowed most number of times).\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Novels";

    private static Logger logger = Logger.getLogger(FindMostPopularCommand.class.getName());
    private final Category category;


    public FindMostPopularCommand(Category category) {
        this.category = category;
    }

    /**
     * Executes find most popular command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);
        logger.log(Level.INFO, "going to start to find max times");
        int maxTimes = findMaxTimes(model);
        logger.log(Level.INFO, "max time has been found");
        Predicate<Book> predicate = book -> book.getCategories().contains(category)
                        && (Integer.parseInt(book.getTimes().value) == maxTimes);

        model.updateFilteredBookList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMostPopularCommand // instanceof handles nulls
                && category.equals(((FindMostPopularCommand) other).category)); // state check
    }

    /**
     * Finds the maximum number of times that any books of the specific book category has been borrowed.
     * @param model the library model.
     * @return the maximum number of times been borrowed of a book of the specified category.
     */
    public int findMaxTimes(Model model) {
        List<Book> lastShownList = model.getFilteredBookList();
        int maxTime = -1;
        for (Book b : lastShownList) {
            if (b.getCategories().contains(category) && Integer.parseInt(b.getTimes().value) > maxTime) {
                maxTime = Integer.parseInt(b.getTimes().value);
            }
        }
        return maxTime;
    }
}
