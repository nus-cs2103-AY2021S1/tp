package seedu.bookmark.model.book.predicates;

import java.util.function.Predicate;

import seedu.bookmark.model.book.Book;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class BookCompletedPredicate implements Predicate<Book> {

    @Override
    public boolean test(Book book) {
        return book.isCompleted();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCompletedPredicate); // instanceof handles nulls
    }
}
