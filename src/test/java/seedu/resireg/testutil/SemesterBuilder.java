package seedu.resireg.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.semester.Semester;

public class SemesterBuilder {

    public static final int DEFAULT_ACADEMIC_YEAR = 2020;
    public static final int DEFAULT_SEMESTER_NUMBER = 1;

    private int academicYear;
    private int semesterNumber;
    private List<Allocation> allocations;

    /**
     * Creates a {@code SemesterBuilder} with the default details.
     */
    public SemesterBuilder() {
        academicYear = DEFAULT_ACADEMIC_YEAR;
        semesterNumber = DEFAULT_SEMESTER_NUMBER;
        allocations = new ArrayList<>();
    }

    /**
     * Initializes the SemesterBuilder with the data of {@code semesterToCopy}.
     */
    public SemesterBuilder(Semester semesterToCopy) {
        academicYear = semesterToCopy.getAcademicYear();
        semesterNumber = semesterToCopy.getSemesterNumber();
        allocations = semesterToCopy.getAllocations();
    }

    /**
     * Sets the academicYear of the {@code Semester} that we are building.
     */
    public SemesterBuilder withAcademicYear(int academicYear) {
        this.academicYear = academicYear;
        return this;
    }

    /**
     * Sets the semesterNumber of the {@code Semester} that we are building.
     */
    public SemesterBuilder withSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
        return this;
    }

    /**
     * Sets the allocations of the {@code Semester} that we are building.
     */
    public SemesterBuilder withAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
        return this;
    }

    /**
     * Returns a new {@code Semester} created.
     */
    public Semester build() {
        return new Semester(academicYear, semesterNumber, allocations);
    }
}
