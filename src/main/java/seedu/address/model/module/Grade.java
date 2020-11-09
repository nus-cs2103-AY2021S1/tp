package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Module's grade in the grade book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grades can be either A+, A, A-, B+, B, B-, C+, C, D+, D or F, and it should not be blank";

    /*
     * The first character of the Grade must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private final Cap cap;

    /**
     * Constructs an {@code Grade}.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        grade = convertSymbolToString(grade);
        cap = Cap.valueOf(grade);
    }

    /**
     * Replaces the plus or minus sign in a grade
     * with the _PLUS or _MINUS syntax respectively
     * to convert the grade string into an enum value name
     * as enum value names cannot take in symbols.
     *
     * @param grade the grade string to be converted.
     * @return the equivalent string representation of the enum value.
     */
    public String convertSymbolToString(String grade) {
        final int lengthOfGradeWithSymbol = 2;
        final String plusSymbol = "+";
        final String minusSymbol = "-";
        final String enumNameForPlus = "_PLUS";
        final String enumNameForMinus = "_MINUS";

        if (grade.length() == lengthOfGradeWithSymbol) {
            if (grade.endsWith(plusSymbol)) {
                grade = grade.replace(plusSymbol, enumNameForPlus);
            } else if (grade.endsWith(minusSymbol)) {
                grade = grade.replace(minusSymbol, enumNameForMinus);
            }
        }
        return grade;
    }

    /**
     * Returns true if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return (containsGrade(test) || test.equals(Cap.getEmptyGrade()))
                && test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if the input grade string is
     * either "A+", "A", "A-", "B+", "B", "B-",
     * "C+", "C", "D+", "D", or 'F".
     *
     * @param test the input grade string to be verified.
     * @return true if the grade is a valid grade,
     * false if the grade is any letter other
     * than the alphabets listed above.
     */
    public static boolean containsGrade(String test) {
        for (Cap c : Cap.values()) {
            if (c.getGradeString().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public double getGradePoint() {
        return cap.getGradePoint();
    }

    @Override
    public String toString() {
        return cap.getGradeString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Grade // instanceof handles nulls
                && cap.equals(((Grade) other).cap)); // state check
    }

    @Override
    public int hashCode() {
        return cap.hashCode();
    }

}
