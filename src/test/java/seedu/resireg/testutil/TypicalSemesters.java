package seedu.resireg.testutil;

import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.SemesterNumber;

/**
 * A utility class containing a list of {@code Semester} objects to be used in tests.
 */
public class TypicalSemesters {

    public static final Semester AY2020_SEM_1 = new SemesterBuilder()
            .withAcademicYear(2020)
            .withSemesterNumber(new SemesterNumber(1)).build();

    public static final Semester AY2020_SEM_2 = new SemesterBuilder()
            .withAcademicYear(2020)
            .withSemesterNumber(new SemesterNumber(2)).build();

    private TypicalSemesters() {
    } // prevents instantiation
}
