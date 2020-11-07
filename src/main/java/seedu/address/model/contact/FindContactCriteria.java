package seedu.address.model.contact;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Encapsulates a list of predicates to be used to test for matching {@code Contact}.
 */
public class FindContactCriteria {

    /**
     * List of predicates to be used to test for matching contacts.
     */
    private final List<Predicate<Contact>> predicates = new ArrayList<>();

    /**
     * Adds a predicate into the list of predicates.
     *
     * @param predicate Predicate to be added.
     */
    public void addPredicate(Predicate<Contact> predicate) {
        requireNonNull(predicate);
        predicates.add(predicate);
    }

    /**
     * Composes all the predicates in the list of predicates into a single predicate to
     * be used to test for matching contacts.
     *
     * @return Predicate object composed of each individual predicate in the list of predicates.
     */
    public Predicate<Contact> getFindContactPredicate() {
        assert !predicates.isEmpty() : "Predicate List cannot be empty";
        Predicate<Contact> findContactPredicate = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            findContactPredicate = findContactPredicate.and(predicates.get(i));
        }
        return findContactPredicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCriteria // instanceof handles nulls
                && predicates.equals(((FindContactCriteria) other).predicates));
    }
}
