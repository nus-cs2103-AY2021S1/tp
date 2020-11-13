package seedu.address.model.deliverable.deliverable;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class TitleDescriptionContainsKeywordsPredicate implements Predicate<Deliverable> {
    private final List<String> keywords;

    public TitleDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Deliverable deliverable) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        deliverable.getTitle().value.replaceAll("\\W", " "), keyword)
                        || StringUtil.containsWordIgnoreCase(
                                deliverable.getDescription().value.orElse("").replaceAll("\\W", " "), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
