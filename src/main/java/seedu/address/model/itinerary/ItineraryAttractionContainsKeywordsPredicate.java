package seedu.address.model.itinerary;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.attraction.AttractionContainsKeywordsPredicate;

/**
 * Tests that a {@code ItineraryAttraction}'s {@code Name}, {@code Address}, {@code Description},
 * {@code Email}, {@code Location}, {@code OpeningHours}, {@code Phone}, {@code PriceRange}, {@code Rating},
 * {@code StartTime}, {@code EndTime}
 * matches any of the keywords given.
 */
public class ItineraryAttractionContainsKeywordsPredicate implements Predicate<ItineraryAttraction> {
    private final List<String> keywords;

    public ItineraryAttractionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(ItineraryAttraction itiAttraction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(itiAttraction.getStartTime().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(itiAttraction.getEndTime().toString(), keyword)
                        || new AttractionContainsKeywordsPredicate(keywords).test(itiAttraction.getAttraction())
                );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryAttractionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItineraryAttractionContainsKeywordsPredicate) other).keywords)); // state check
    }

}

