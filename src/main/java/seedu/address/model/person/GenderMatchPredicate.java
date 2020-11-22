package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code gender} matches the current predicate's gender.
 */
public class GenderMatchPredicate implements Predicate<Person> {
    private final Gender gender;

    /**
     * Constructs a {@code GenderMatchPredicate}.
     *
     * @param gender to test people with.
     */
    public GenderMatchPredicate(Gender gender) {
        requireNonNull(gender);
        this.gender = gender;
    }

    @Override
    public boolean test(Person person) {
        return person.getGender().equals(gender);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GenderMatchPredicate // instanceof handles nulls
                && gender.equals(((GenderMatchPredicate) other).gender)); // state check
    }

}
