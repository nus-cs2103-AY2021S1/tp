package seedu.simplykitchen.model.food;

import java.util.function.Predicate;

/**
 * Tests that a {@code Food}'s {@code Priority} matches the search given.
 */
public class PrioritySearchPredicate implements Predicate<Food> {
    private final Priority.Level prioritySearch;

    /**
     * @param prioritySearch The priority to search for.
     */
    public PrioritySearchPredicate(Priority.Level prioritySearch) {
        assert prioritySearch != null : "Priority cannot be null.";
        this.prioritySearch = prioritySearch;
    }

    @Override
    public boolean test(Food food) {
        return food.getPriority().value.equals(prioritySearch);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrioritySearchPredicate // instanceof handles nulls
                && prioritySearch.equals(((PrioritySearchPredicate) other).prioritySearch)); // state check
    }

}
