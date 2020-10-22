package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Year} matches the given year.
 */
public class YearMatchPredicate implements Predicate<Student> {

    // Attributes
    public final Year year;

    // Constructor
    public YearMatchPredicate(Year year) {
        this.year = year;
    }

    @Override
    public boolean test(Student student) {
        return this.year.equals(student.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearMatchPredicate // instanceof handles nulls
                && this.year.equals(((YearMatchPredicate) other).year)); // state check
    }

}
