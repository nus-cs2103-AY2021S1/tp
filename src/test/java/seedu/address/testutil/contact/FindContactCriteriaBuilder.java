package seedu.address.testutil.contact;

import seedu.address.model.contact.ContactContainsTagsPredicate;
import seedu.address.model.contact.ContactNameContainsKeywordsPredicate;
import seedu.address.model.contact.FindContactCriteria;

/**
 * A utility class to help with building FindContactCriteria objects.
 */
public class FindContactCriteriaBuilder {

    private FindContactCriteria findContactCriteria;

    public FindContactCriteriaBuilder() {
        this.findContactCriteria = new FindContactCriteria();
    }

    /**
     * Adds a {@code ContactNameContainsKeywordsPredicate} to the {@code FindContactCriteria} that we are building.
     */
    public FindContactCriteriaBuilder withNamePredicate(ContactNameContainsKeywordsPredicate predicate) {
        findContactCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Adds a {@code ContactContainsTagsPredicate} to the {@code FindContactCriteria} that we are building.
     */
    public FindContactCriteriaBuilder withTagPredicate(ContactContainsTagsPredicate predicate) {
        findContactCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Returns the newly built {@code FindContactCriteria} object.
     */
    public FindContactCriteria build() {
        return findContactCriteria;
    }
}
