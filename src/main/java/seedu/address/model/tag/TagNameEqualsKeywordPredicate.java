package seedu.address.model.tag;

import java.util.function.Predicate;

/**
 * Tests that a {@code Tag}'s {@code TagName} equals to the tag name given.
 */
public class TagNameEqualsKeywordPredicate implements Predicate<Tag> {
    private final TagName tagNameToFind;

    public TagNameEqualsKeywordPredicate(TagName tagNameToFind) {
        this.tagNameToFind = tagNameToFind;
    }

    @Override
    public boolean test(Tag tag) {
        return tagNameToFind.equals(tag.getTagName());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagNameEqualsKeywordPredicate // instanceof handles nulls
                && tagNameToFind.equals(((TagNameEqualsKeywordPredicate) other).tagNameToFind)); // state check
    }
}
