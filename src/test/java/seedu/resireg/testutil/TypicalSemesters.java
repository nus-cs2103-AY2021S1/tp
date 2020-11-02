package seedu.resireg.testutil;

import seedu.resireg.model.semester.Semester;

/**
 * A utility class containing a list of {@code Semester} objects to be used in tests.
 */
public class TypicalSemesters {
    public static final int YEAR_2020 = 2020;
    public static final int SEMESTER_ONE = 1;
    public static final int SEMESTER_TWO = 2;

    public static final Semester AY2020_SEM_1 = new SemesterBuilder()
            .withAcademicYear(YEAR_2020)
            .withSemesterNumber(SEMESTER_ONE).build();

    public static final Semester AY2020_SEM_2 = new SemesterBuilder()
            .withAcademicYear(YEAR_2020)
            .withSemesterNumber(SEMESTER_TWO).build();

    private TypicalSemesters() {
    } // prevents instantiation
}
