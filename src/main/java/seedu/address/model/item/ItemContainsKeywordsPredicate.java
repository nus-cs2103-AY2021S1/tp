package seedu.address.model.item;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Item}'s {@code Name} or {@code Supplier} or {@code Tags} matches any of the keywords given.
 */
public class ItemContainsKeywordsPredicate implements Predicate<Item> {

    private final List<String> keywords;
    private final Prefix prefix;

    /**
     * Constructor for ItemContainsKeywordsPredicate.
     * @param keywords List of keywords
     * @param prefix Prefix
     */
    public ItemContainsKeywordsPredicate(List<String> keywords, Prefix prefix) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Item item) {
        if (PREFIX_NAME.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getName().fullName, keyword));
        } else if (PREFIX_SUPPLIER.equals(prefix)) {
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getSupplier().value, keyword));
        } else if (PREFIX_TAG.equals(prefix)) {
            StringBuilder tags = new StringBuilder();
            for (Tag tag : item.getTags()) {
                tags.append(tag).append(" ");
            }
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tags.toString(), keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ItemContainsKeywordsPredicate) other).keywords)); // state check
    }

}
