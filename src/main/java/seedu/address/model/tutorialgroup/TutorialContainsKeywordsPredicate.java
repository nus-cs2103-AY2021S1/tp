package seedu.address.model.tutorialgroup;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class TutorialContainsKeywordsPredicate implements Predicate<TutorialGroup> {
    private final List<String> keywords;

    public TutorialContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(TutorialGroup tutorialGroup) {
        String idstring = tutorialGroup.getId().toString();
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tutorialGroup.getId().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TutorialContainsKeywordsPredicate // instanceof handles nulls
            && keywords.equals(((TutorialContainsKeywordsPredicate) other).keywords)); // state check
    }
}
