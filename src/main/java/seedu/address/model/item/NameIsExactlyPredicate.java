package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameIsExactlyPredicate implements Predicate<Item> {
    private final List<String> keywords;

    /**
     * Constructs NameIsExactlyPredicate predicate object to search through the InventoryList
     * @param keywords contains string search terms to match to this.
     */
    public NameIsExactlyPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    public String getKeyword() {
        assert(keywords.size() > 0);
        requireNonNull(keywords);
        return keywords.get(0);
    }

    @Override
    public boolean test(Item item) {
        requireNonNull(item);
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
