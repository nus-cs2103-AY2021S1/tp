package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import seedu.address.commons.util.StringUtil;


/**
 * Tests that a {@code Item}'s {@code Name} contains any of the keywords given, case-insensitive.
 */
public class NameMatchesKeywordsPredicate implements Predicate<Item> {
    private final List<String> keywords;

    /**
     * Constructs a NameMatchesKeywordsPredicate that must not be empty
     * @param keywords keyword patterns to match.
     */
    public NameMatchesKeywordsPredicate(List<String> keywords) {
        requireNonNull(keywords);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Item item) {
        return keywords.stream()
                .anyMatch(keyword -> !keyword.strip().isBlank() && StringUtil.matchesPatternIgnoreCase(item.getName(),
                        Pattern.compile(keyword.strip(), Pattern.CASE_INSENSITIVE)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameMatchesKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameMatchesKeywordsPredicate) other).keywords)); // state check
    }

}
