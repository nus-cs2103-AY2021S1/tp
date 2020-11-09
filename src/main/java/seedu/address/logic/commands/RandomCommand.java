package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BOOKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.category.Category;
import seedu.address.ui.Mode;


/**
 * Finds a random book from the library of a specific category.
 * Keyword matching is case insensitive.
 */
public class RandomCommand extends Command {

    public static final String COMMAND_WORD = "random";
    public static final String SUGGESTION = "random <category>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Randomly select a book"
            + "of a certain category.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Novels";

    private static Logger logger = Logger.getLogger(RandomCommand.class.getName());
    private final Category category;


    public RandomCommand(Category category) {
        this.category = category;
    }

    /**
     * Execute random command on model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult object
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredBookList((book -> false), Mode.NORMAL);
        model.updateFilteredBookList(PREDICATE_SHOW_ALL_BOOKS, Mode.NORMAL);
        Optional<Book> randomBook = findRandomBook(model);
        Predicate<Book> predicate = (Book book) -> randomBook.map(book::equals).orElse(false);
        logger.log(Level.INFO, "going to start to filter the book list to match the select book");
        model.updateFilteredBookList(predicate, Mode.NORMAL);
        return new CommandResult(
                String.format(Messages.MESSAGE_BOOKS_LISTED_OVERVIEW, model.getFilteredBookList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RandomCommand // instanceof handles nulls
                && category.equals(((RandomCommand) other).category)); // state check
    }

    /**
     * Finds a random book within the specified category.
     * @param model the model of the library.
     * @return Optional of the random book, it will be an empty Optional if no books are listed.
     */
    public Optional<Book> findRandomBook(Model model) {
        List<Book> lastShownList = model.getFilteredBookList();
        List<Book> matchingBooks = new ArrayList<>();
        Random random = new Random();
        Optional<Book> randomBook = Optional.empty();
        for (Book b : lastShownList) {
            if (b.getCategories().contains(category)) {
                matchingBooks.add(b);
            }
        }
        if (!matchingBooks.isEmpty()) {
            randomBook = Optional.of(matchingBooks.get(random.nextInt(matchingBooks.size())));
        }
        return randomBook;
    }
}
