package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Contact}'s {@code ContactName} matches any of the keywords given.
 */
public class ContactNameContainsKeywordsPredicate implements Predicate<Contact> {

    /** List of search keywords to test for matching contacts. */
    private final List<String> keywords;

    /**
     * Creates and initialises a ContactNameContainsKeywordsPredicate object to test for matching contacts.
     *
     * @param keywords List of keywords to test for matching contacts.
     */
    public ContactNameContainsKeywordsPredicate(List<String> keywords) {
        assert !keywords.isEmpty() : "At least one search keyword must be present";
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
