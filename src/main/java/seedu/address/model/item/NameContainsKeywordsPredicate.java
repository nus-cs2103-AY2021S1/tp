package seedu.address.model.item;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Item}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Item> {

    private final List<String> keywords;
    private final String type;

    public NameContainsKeywordsPredicate(List<String> keywords, String type) {
        this.keywords = keywords;
        this.type = type;
    }

    @Override
    public boolean test(Item item) {
        switch(type) {
        case "Name":
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getName().fullName, keyword));
        case "Supplier": ;
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(item.getSupplier().value, keyword));
        case "Tag":
            StringBuilder tags = new StringBuilder();
            for (Tag tag : item.getTags()) {
                tags.append(tag);
            }
            return keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tags.toString(), keyword));
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
