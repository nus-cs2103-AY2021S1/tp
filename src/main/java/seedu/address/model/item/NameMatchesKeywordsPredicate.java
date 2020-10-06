package seedu.address.model.item;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;


/**
 * Tests that a {@code Item}'s {@code Name} contains any of the keywords given.
 */
public class NameMatchesKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    public NameMatchesKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.matchesPatternIgnoreCase(item.getName(),
                        Pattern.compile(keyword, Pattern.CASE_INSENSITIVE)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameMatchesKeywordsPredicate) other).keywords)); // state check
    }

}
