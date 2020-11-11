package seedu.bookmark.model.book.predicates;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.bookmark.commons.util.StringUtil;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.tag.Tag;

/**
 * Tests that a {@code Book}'s {@code Name} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate implements Predicate<Book> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Book book) {
        Set<Tag> bookTags = book.getTags();
        String tagsString = "";
        for (Tag tag : bookTags) {
            tagsString = tagsString + tag.getTagName() + " ";
        }
        String finalTagsString = tagsString;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(finalTagsString, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }

}
