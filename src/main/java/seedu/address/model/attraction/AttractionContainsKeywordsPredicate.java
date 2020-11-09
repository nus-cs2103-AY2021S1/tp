package seedu.address.model.attraction;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that an {@code Attraction}'s {@code Name}, {@code Address}, {@code Description},
 * {@code Email}, {@code Location}, {@code OpeningHours}, {@code Phone}, {@code PriceRange}, {@code Rating}
 * matches any of the keywords given.
 */
public class AttractionContainsKeywordsPredicate implements Predicate<Attraction> {
    private final List<String> keywords;

    public AttractionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Attraction attraction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(attraction.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getAddress().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getDescription().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getEmail().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getLocation().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getOpeningHours().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getPhone().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getPriceRange().value, keyword)
                        || StringUtil.containsWordIgnoreCase(attraction.getRating().value, keyword)
                        || attraction.getTags().stream().anyMatch(tag -> tag.tagName.equals(keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AttractionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AttractionContainsKeywordsPredicate) other).keywords)); // state check
    }

}
