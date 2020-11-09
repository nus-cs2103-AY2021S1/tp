package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.model.Model;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Email;
import seedu.address.model.book.Isbn;
import seedu.address.model.book.Language;
import seedu.address.model.book.Name;
import seedu.address.model.book.Publisher;
import seedu.address.model.book.Stocking;
import seedu.address.model.book.Times;
import seedu.address.model.category.Category;
import seedu.address.model.review.Review;
import seedu.address.ui.Mode;

/**
 * Adds the review of the corresponding book.
 */
public class AddReviewCommand extends Command {

    public static final String COMMAND_WORD = "addReview";
    public static final String SUGGESTION = "addReview <index> ra/<rating> re/<review content>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add the review to the book at "
            + "the corresponding position in the list, where the rating is an integer between 0 and 5.\n"
            + "Parameters: "
            + "INDEX "
            + CliSyntax.PREFIX_RATING + "RATING "
            + CliSyntax.PREFIX_REVIEW + "REVIEW_CONTENT\n"
            + "Example: " + COMMAND_WORD + " 1 " + CliSyntax.PREFIX_RATING + "5" + " " + CliSyntax.PREFIX_REVIEW
            + "The book is interesting";

    public static final String MESSAGE_ADD_REVIEW_SUCCESS = "The review has been added to the book %1$s";

    private final Index index;
    private final Review review;

    /**
     * Creates a add review command to add the review of the corresponding book.
     *
     * @param index The index of the book in the list.
     * @param review The review of the book to add.
     */
    public AddReviewCommand(Index index, Review review) {
        requireNonNull(index);
        requireNonNull(review);

        this.index = index;
        this.review = review;
        this.review.setCreatedTime(LocalDateTime.now());
    }

    /**
     * Executes add review command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     * @throws CommandException if invalid book
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            List<Book> lastShownList = model.getFilteredBookList();
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
            }
            Book bookToReview = lastShownList.get(index.getZeroBased());
            Book reviewedBook = createdChangedBook(bookToReview, review);

            model.setBook(bookToReview, reviewedBook);
            model.updateFilteredBookList(new Predicate<Book>() {
                @Override
                public boolean test(Book book) {
                    return book.getIsbn().value.equals(reviewedBook.getIsbn().value);
                }
            }, Mode.REVIEW);
            return new CommandResult(String.format(MESSAGE_ADD_REVIEW_SUCCESS, reviewedBook));
        } catch (Exception exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
        }
    }

    /**
     * Creates the book with the new review added to the review list of the book.
     *
     * @param book The corresponding book.
     * @param review The review to add.
     * @return The book with the new review list.
     */
    private static Book createdChangedBook(Book book, Review review) {
        assert book != null;
        assert review != null;
        Name name = book.getName();
        Isbn isbn = book.getIsbn();
        Email email = book.getEmail();
        Language language = book.getLanguage();
        List<Review> reviews = book.getReviews();
        reviews.add(review);
        Times times = book.getTimes();
        Set<Category> categories = book.getCategories();
        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();
        Stocking stocking = book.getStocking();

        return new Book(name, isbn, email, language, times, categories, stocking, reviews, author, publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof AddReviewCommand)) {
            return false;
        } else {
            AddReviewCommand other = (AddReviewCommand) o;
            return index.equals(other.index)
                   && review.equals(other.review);
        }
    }
}
