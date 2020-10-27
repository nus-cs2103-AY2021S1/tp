package seedu.address.model.bid;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class BidContainsKeywordsPredicate implements Predicate<Bid> {
    private final List<String> keywords;

    /**
     * constructor to make BidContainsKeywordsPredicate
     * @param keywords list of specified key words
     */
    public BidContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Bid bid) {
        return (keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(bid.getPropertyId().toString(), keyword)))
                || (keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(bid.getBidderId().toString(), keyword)))
                || (keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(bid.getBidAmount().toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BidContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BidContainsKeywordsPredicate) other).keywords)); // state check
    }

}
