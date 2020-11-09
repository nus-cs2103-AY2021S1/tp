package seedu.address.testutil.todolist;

import seedu.address.model.task.FindTaskCriteria;
import seedu.address.model.task.TaskContainsTagsPredicate;
import seedu.address.model.task.TaskMatchesDatePredicate;
import seedu.address.model.task.TaskMatchesPriorityPredicate;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

/**
 * A utility class to help with building FindTaskCriteria objects.
 */
public class FindTaskCriteriaBuilder {

    private FindTaskCriteria findTaskCriteria;

    public FindTaskCriteriaBuilder() {
        this.findTaskCriteria = new FindTaskCriteria();
    }

    /**
     * Adds a {@code TaskNameContainsKeywordsPredicate} to the {@code FindTaskCriteria} that we are building.
     */
    public FindTaskCriteriaBuilder withNamePredicate(TaskNameContainsKeywordsPredicate predicate) {
        this.findTaskCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Adds a {@code TaskMatchesDatePredicate} to the {@code FindTaskCriteria} that we are building.
     */
    public FindTaskCriteriaBuilder withDatePredicate(TaskMatchesDatePredicate predicate) {
        this.findTaskCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Adds a {@code TaskMatchesPriorityPredicate} to the {@code FindTaskCriteria} that we are building.
     */
    public FindTaskCriteriaBuilder withPriorityPredicate(TaskMatchesPriorityPredicate predicate) {
        this.findTaskCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Adds a {@code TaskContainsTagsPredicate} to the {@code FindTaskCriteria} that we are building.
     */
    public FindTaskCriteriaBuilder withTagPredicate(TaskContainsTagsPredicate predicate) {
        this.findTaskCriteria.addPredicate(predicate);
        return this;
    }

    /**
     * Returns the newly built {@code FindContactCriteria} object.
     */
    public FindTaskCriteria build() {
        return findTaskCriteria;
    }

}
