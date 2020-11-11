package seedu.bookmark.model.book.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.bookmark.commons.util.StringUtil;
import seedu.bookmark.model.book.Book;

/**
 * Tests that a {@code Book}'s {@code Genre} matches any of the keywords given.
 */
public class GenreContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public GenreContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(book.getGenre().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenreContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((GenreContainsKeywordsPredicate) other).keywords)); // state check
    }

}
