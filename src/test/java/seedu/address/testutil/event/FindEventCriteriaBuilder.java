package seedu.address.testutil.event;

import seedu.address.model.event.EventContainsDatePredicate;
import seedu.address.model.event.EventNameContainsKeyWordsPredicate;
import seedu.address.model.event.FindEventCriteria;

/**
 * A utility class to help with building FindEventCriteria objects.
 */
public class FindEventCriteriaBuilder {
    private FindEventCriteria findEventCriteria;

    public FindEventCriteriaBuilder() {
        this.findEventCriteria = new FindEventCriteria();
    }

    /**
     * Adds a {@code EventNameContainsKeyWordsPredicate} to the {@code FindEventCriteria} that we are building.
     */
    public FindEventCriteriaBuilder withNamePredicate(EventNameContainsKeyWordsPredicate predicate) {
        findEventCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Adds a {@code EventContainsDatePredicate} to the {@code FindEventCriteria} that we are building.
     */
    public FindEventCriteriaBuilder withDatePredicate(EventContainsDatePredicate predicate) {
        findEventCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Returns the newly built {@code FindEventCriteria} object.
     */
    public FindEventCriteria build() {
        return findEventCriteria;
    }
}
