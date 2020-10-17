package seedu.address.model.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Assignment}'s {@code Name} matches any of the keywords given.
 */
public class DeadlineContainsKeywordsPredicate implements Predicate<Assignment> {
    private final Deadline deadline;

    public DeadlineContainsKeywordsPredicate(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean test(Assignment assignment) {
        List<Deadline> listForm = new ArrayList<>();
        listForm.add(deadline);
        return listForm.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(assignment.getDeadline().value, deadline.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineContainsKeywordsPredicate // instanceof handles nulls
                && deadline.equals(((DeadlineContainsKeywordsPredicate) other).deadline)); // state check
    }

}
