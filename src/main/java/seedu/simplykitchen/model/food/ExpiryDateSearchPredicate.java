package seedu.simplykitchen.model.food;

import java.util.function.Predicate;

/**
 * Tests that a {@code Food}'s {@code Priority} matches the search given.
 */
public class ExpiryDateSearchPredicate implements Predicate<Food> {

    private final String expiryDateSearch;

    /**
     * @param expiryDateSearch The expiry date to search for.
     */
    public ExpiryDateSearchPredicate(String expiryDateSearch) {
        assert expiryDateSearch.length() > 0 : "Expiry date cannot be blank.";
        this.expiryDateSearch = expiryDateSearch;
    }

    @Override
    public boolean test(Food food) {
        return food.getExpiryDate().value.equals(expiryDateSearch);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpiryDateSearchPredicate // instanceof handles nulls
                && expiryDateSearch.equals(((ExpiryDateSearchPredicate) other).expiryDateSearch)); // state check
    }

}
