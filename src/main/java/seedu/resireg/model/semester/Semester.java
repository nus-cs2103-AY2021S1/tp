package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Represents a Semester in ResiReg.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Semester {
    public static final int FIRST_SEMESTER = 1;
    public static final int SECOND_SEMESTER = 2;
    public static final int YEAR_OF_ESTABLISHMENT = 1980;
    public static final String ACADEMIC_YEAR_MESSAGE_CONSTRAINTS = "Academic Year has to be greater than or equal to "
            + YEAR_OF_ESTABLISHMENT;
    public static final String SEMESTER_NUMBER_MESSAGE_CONSTRAINTS = "Semester number can only be 1 or 2";

    // Identity fields
    private final IntegerProperty academicYear = new SimpleIntegerProperty();
    private final IntegerProperty semesterNumber = new SimpleIntegerProperty();

    /**
     * academicYear and semesterNumber should be valid.
     */
    public Semester(int academicYear, int semesterNumber) {
        checkArgument(isValidAcademicYear(academicYear), ACADEMIC_YEAR_MESSAGE_CONSTRAINTS);
        checkArgument(isValidSemesterNumber(semesterNumber), SEMESTER_NUMBER_MESSAGE_CONSTRAINTS);
        this.academicYear.set(academicYear);
        this.semesterNumber.set(semesterNumber);
    }

    /**
     * Helper constructor for initializing a new semester.
     */
    public Semester() {
        this(LocalDate.now().getYear(), FIRST_SEMESTER);
    }

    public int getAcademicYear() {
        return academicYear.get();
    }

    public IntegerProperty academicYearProperty() {
        return academicYear;
    }

    public IntegerProperty semesterNumberProperty() {
        return semesterNumber;
    }

    public void setAcademicYear(int academicYear) {
        checkArgument(isValidAcademicYear(academicYear), ACADEMIC_YEAR_MESSAGE_CONSTRAINTS);
        this.academicYear.set(academicYear);
    }

    public void setSemesterNumber(int semesterNumber) {
        checkArgument(isValidSemesterNumber(semesterNumber), SEMESTER_NUMBER_MESSAGE_CONSTRAINTS);
        this.semesterNumber.set(semesterNumber);
    }

    public int getSemesterNumber() {
        return semesterNumber.get();
    }

    /**
     * Returns the successor of a given Semester. A next semester should be the second semester of the academic year if
     * the given semester is in sem 1, otherwise it should be the first semester of the next academic year.
     *
     * @return the successor of this semester
     */
    public Semester getNextSemester() {
        if (semesterNumber.get() == FIRST_SEMESTER) {
            return new Semester(academicYear.get(), SECOND_SEMESTER);
        } else {
            return new Semester(academicYear.get() + 1, FIRST_SEMESTER);
        }
    }

    /**
     * @return a shortened String representation of the semester in form of "AY" + year + "S" + semesterNumber.
     */
    public String getShortRepresentation() {
        return String.format("AY%sS%s", academicYear.get(), semesterNumber.get());
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidSemesterNumber(int test) {
        return test == FIRST_SEMESTER || test == SECOND_SEMESTER;
    }

    /**
     * Returns true if a given string is a valid academic year.
     */
    public static boolean isValidAcademicYear(int test) {
        return test >= YEAR_OF_ESTABLISHMENT;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Semester)) {
            return false;
        }

        Semester otherSemester = (Semester) other;
        return otherSemester.getAcademicYear() == getAcademicYear()
                && otherSemester.getSemesterNumber() == getSemesterNumber();
    }

    @Override
    public String toString() {
        return " Academic Year: " + getAcademicYear() + " Semester: " + getSemesterNumber();
    }
}
