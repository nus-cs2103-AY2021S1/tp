package chopchop.model.attributes;

import chopchop.model.Entry;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that an item's {@code Name} matches any of the keywords given.
 */
public class ExpiryDateMatchesKeywordsPredicate implements Predicate<Entry> {
    private final ExpiryDate exd;

    public ExpiryDateMatchesKeywordsPredicate(ExpiryDate exd) {
        this.exd = exd;
    }

    @Override
    public boolean test(Entry entry) {
        Optional<ExpiryDate> currExp = entry.getExpiryDate();
        if (currExp.isPresent()) {
            return currExp.get().compareTo(exd) <= 0;
        }

        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDateMatchesKeywordsPredicate // instanceof handles nulls
                && this.exd.equals(((ExpiryDateMatchesKeywordsPredicate) other).exd)); // state check
    }

}
