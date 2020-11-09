package seedu.address.model.information;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Job's vacancy in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidVacancy(String)}
 */
public class Vacancy {

    public static final String MESSAGE_CONSTRAINTS =
        "Vacancy should only contain unsigned integers (non-negative), "
                + "and it should be 1 to 2 digits long (max vacancy: 99)";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final String value;
    public final int vacancyNumber;

    /**
     * Constructs an {@code Vacancy}.
     *
     * @param vacancy A valid vacancy.
     */
    public Vacancy(String vacancy) {
        requireNonNull(vacancy);
        checkArgument(isValidVacancy(vacancy), MESSAGE_CONSTRAINTS);
        value = vacancy;
        vacancyNumber = Integer.parseInt(vacancy);
    }

    /**
     * Returns true if a given string is a valid vacancy.
     */
    public static boolean isValidVacancy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Vacancy // instanceof handles nulls
            && value.equals(((Vacancy) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
