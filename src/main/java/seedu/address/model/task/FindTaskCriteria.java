package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Encapsulates a list of predicates to be used to test for matching {@code Task}.
 */
public class FindTaskCriteria {

    /** List of predicates to be used to test for matching tasks. */
    private final List<Predicate<Task>> predicates = new ArrayList<>();

    /**
     * Adds a predicate into the list of predicates.
     *
     * @param predicate Predicate to be added.
     */
    public void addPredicate(Predicate<Task> predicate) {
        requireNonNull(predicate);
        predicates.add(predicate);
    }

    /**
     * Composes all the predicates in the list of predicates into a single predicate to
     * be used to test for matching tasks.
     *
     * @return Predicate object composed of each individual predicate in the list of predicates.
     */
    public Predicate<Task> getFindTaskPredicate() {
        assert !predicates.isEmpty() : "Predicate List cannot be empty";
        Predicate<Task> findTaskPredicate = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            findTaskPredicate = findTaskPredicate.and(predicates.get(i));
        }
        return findTaskPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindTaskCriteria // instanceof handles nulls
                && predicates.equals(((FindTaskCriteria) other).predicates));
    }
}

