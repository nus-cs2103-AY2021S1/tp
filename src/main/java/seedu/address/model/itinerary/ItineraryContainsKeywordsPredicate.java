package seedu.address.model.itinerary;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Itinerary}'s {@code Name}, {@code Description},
 * {@code StartTime}, {@code EndTime}, {@code Budget} and all its itinerary attractions
 * matches any of the keywords given.
 */
public class ItineraryContainsKeywordsPredicate implements Predicate<Itinerary> {
    private final List<String> keywords;

    public ItineraryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }


    @Override
    public boolean test(Itinerary itinerary) {
        if (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(itinerary.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(itinerary.getDescription().value, keyword)
                        || StringUtil.containsWordIgnoreCase(itinerary.getStartDate().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(itinerary.getEndDate().toString(), keyword)
                        || itinerary.getBudget().value.contains(keyword) // numerical budget value
                        || itinerary.getBudget().toString().contains(keyword) // budget value with $
                )
        ) {
            return true;
        } else {
            // Also checks through each itinerary attraction
            ItineraryAttractionContainsKeywordsPredicate iaPredicate =
                    new ItineraryAttractionContainsKeywordsPredicate(keywords);
            for (ItineraryAttraction ia : itinerary.getItineraryAttractions()) {
                if (iaPredicate.test(ia)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItineraryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItineraryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
