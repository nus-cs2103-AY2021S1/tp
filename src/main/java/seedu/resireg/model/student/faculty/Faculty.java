package seedu.resireg.model.student.faculty;

import static java.util.Objects.requireNonNull;
import static seedu.resireg.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a Student's faculty in ResiReg.
 * Guarantees: immutable; is valid as declared in {@link #isValidFaculty(String)}
 */
public class Faculty {


    public static final String MESSAGE_CONSTRAINTS =
        "A faculty must be listed here: http://www.nus.edu.sg/education\n"
            + "The following is a list of all faculty codes used in ResiReg: "
            + FacultyEnum.toListString();

    public final String value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param facultyAbbr A valid faculty abbreviation.
     */
    public Faculty(String facultyAbbr) {
        requireNonNull(facultyAbbr);
        checkArgument(isValidFaculty(facultyAbbr), MESSAGE_CONSTRAINTS);
        value = facultyAbbr;
    }

    /**
     * Returns true if a given string is a valid faculty abbreviation.
     */
    public static boolean isValidFaculty(String test) {
        List<FacultyEnum> faculties = Arrays.asList(FacultyEnum.values());
        return faculties.stream().anyMatch(faculty -> faculty.matchesFacultyAbbreviation(test));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Faculty // instanceof handles nulls
            && value.equals(((Faculty) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
