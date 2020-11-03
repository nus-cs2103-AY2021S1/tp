package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameIsExactlyPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public NameIsExactlyPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getKeyword() {
        assert(keywords.size() > 0);
        requireNonNull(keywords);
        return keywords.get(0);
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.equals(item.getName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameIsExactlyPredicate // instanceof handles nulls
                && keywords.equals(((NameIsExactlyPredicate) other).keywords)); // state check
    }

}
