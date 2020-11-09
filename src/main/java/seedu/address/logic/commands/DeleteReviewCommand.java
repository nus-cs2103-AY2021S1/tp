package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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
import seedu.address.model.review.ReviewNumber;
import seedu.address.ui.Mode;

/**
 * Deletes the review of the corresponding book.
 */
public class DeleteReviewCommand extends Command {

    public static final String COMMAND_WORD = "deleteReview";
    public static final String SUGGESTION = "deleteReview <index> rn/<review number>";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Delete the corresponding review of the book at "
            + "the corresponding position in the list.\n"
            + "Parameters: "
            + "INDEX "
            + CliSyntax.PREFIX_REVIEWNUMBER + "REVIEW_INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 " + CliSyntax.PREFIX_REVIEWNUMBER + "5";

    public static final String MESSAGE_DELETE_REVIEW_SUCCESS = "The review has been deleted for the book %1$s";

    private final Index bookIndex;
    private final int reviewIndex;

    /**
     * Creates a delete review command to delete the review of the corresponding book.
     *
     * @param bookIndex The index of the book in the list.
     * @param reviewIndex The index of the review in the review list of the book.
     */
    public DeleteReviewCommand(Index bookIndex, ReviewNumber reviewIndex) {
        requireNonNull(bookIndex);
        requireNonNull(reviewIndex);

        this.bookIndex = bookIndex;
        this.reviewIndex = reviewIndex.reviewNumber;
    }

    /**
     * Executes delete review command on model and return with result.
     *
     * @param model {@code Model} which the command should operate on
     * @return a new CommandResult object
     * @throws CommandException if illegal size
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert bookIndex != null;
        assert reviewIndex >= 1;
        try {
            requireNonNull(model);
            List<Book> lastShownList = model.getFilteredBookList();

            if (bookIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
            }

            Book bookToReview = lastShownList.get(bookIndex.getZeroBased());

            if (bookToReview.getReviews().size() < reviewIndex) {
                throw new CommandException(Messages.MESSAGE_INVALID_REVIEW_DISPLAYED_INDEX);
            }

            Book newBook = createdChangedBook(bookToReview, reviewIndex);

            model.setBook(bookToReview, newBook);
            model.updateFilteredBookList(new Predicate<Book>() {
                @Override
                public boolean test(Book book) {
                    return book.getIsbn().value.equals(newBook.getIsbn().value);
                }
            }, Mode.REVIEW);
            return new CommandResult(String.format(MESSAGE_DELETE_REVIEW_SUCCESS, newBook));
        } catch (CommandException commandException) {
            throw commandException;
        } catch (IndexOutOfBoundsException indexOutOgBoundsException) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX_IN_REVIEW);
        }
    }

    /**
     * Creates the book with the review removed from the book.
     *
     * @param book The corresponding book.
     * @param reviewIndex The review number of the review.
     * @return The book with the new review list.
     */
    private static Book createdChangedBook(Book book, int reviewIndex) {
        assert book != null;
        assert reviewIndex >= 1;
        Name name = book.getName();
        Isbn isbn = book.getIsbn();
        Email email = book.getEmail();
        Language language = book.getLanguage();
        List<Review> reviewList = book.getReviews();
        reviewList.remove(reviewIndex - 1);

        Times times = book.getTimes();
        Set<Category> categories = book.getCategories();
        Author author = book.getAuthor();
        Publisher publisher = book.getPublisher();
        Stocking stocking = book.getStocking();

        return new Book(name, isbn, email, language, times, categories, stocking, reviewList, author, publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof DeleteReviewCommand)) {
            return false;
        } else {
            DeleteReviewCommand other = (DeleteReviewCommand) o;
            return reviewIndex == other.reviewIndex
                    && bookIndex.equals(((DeleteReviewCommand) o).bookIndex);
        }
    }
}
