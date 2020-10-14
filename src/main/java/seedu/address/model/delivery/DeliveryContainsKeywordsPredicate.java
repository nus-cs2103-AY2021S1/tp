package seedu.address.model.delivery;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Delivery}'s {@code Name} or {@code Address}
 * or {@code Phone} or {@code Order} matches any of the keywords given.
 */
public class DeliveryContainsKeywordsPredicate implements Predicate<Delivery> {

    private final List<String> keywords;
    private final Prefix prefix;

    /**
     * Constructor for DeliveryContainsKeywordsPredicate.
     * @param keywords List of keywords
     * @param prefix Prefix
     */
    public DeliveryContainsKeywordsPredicate(List<String> keywords, Prefix prefix) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Delivery delivery) {
        String keywordsAsOneString = keywords.stream().reduce("", (keyword, res) -> keyword + " " + res);

        if (PREFIX_NAME.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(delivery.getName().fullName, keyword));
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(delivery.getAddress().value, keyword));
        } else if (PREFIX_PHONE.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(delivery.getPhone().value, keyword));
        } else if (PREFIX_ORDER.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsStringIgnoreCase(delivery.getOrder().value, keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeliveryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DeliveryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
