package seedu.resireg.testutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.semester.AcademicYear;
import seedu.resireg.model.semester.RoomRate;
import seedu.resireg.model.semester.Semester;
import seedu.resireg.model.semester.SemesterNumber;

public class SemesterBuilder {

    public static final AcademicYear DEFAULT_ACADEMIC_YEAR = new AcademicYear(2020);
    public static final SemesterNumber DEFAULT_SEMESTER_NUMBER = new SemesterNumber(1);

    private AcademicYear academicYear;
    private SemesterNumber semesterNumber;
    private List<Allocation> allocations;
    private Map<RoomType, RoomRate> roomFees;

    /**
     * Creates a {@code SemesterBuilder} with the default details.
     */
    public SemesterBuilder() {
        academicYear = DEFAULT_ACADEMIC_YEAR;
        semesterNumber = DEFAULT_SEMESTER_NUMBER;
        allocations = new ArrayList<>();
        roomFees = new HashMap<>();
    }

    /**
     * Initializes the SemesterBuilder with the data of {@code semesterToCopy}.
     */
    public SemesterBuilder(Semester semesterToCopy) {
        academicYear = new AcademicYear(semesterToCopy.getAcademicYear().value);
        semesterNumber = semesterToCopy.getSemesterNumber();
        allocations = semesterToCopy.getAllocations();
        roomFees = semesterToCopy.getRoomFees();
    }

    /**
     * Sets the {@code academicYear} of the {@code Semester} that we are building.
     */
    public SemesterBuilder withAcademicYear(int academicYear) {
        this.academicYear = new AcademicYear(academicYear);
        return this;
    }

    /**
     * Sets the {@code semesterNumber} of the {@code Semester} that we are building.
     */
    public SemesterBuilder withSemesterNumber(int semesterNumber) {
        this.semesterNumber = new SemesterNumber(semesterNumber);
        return this;
    }

    /**
     * Sets the {@code allocations} of the {@code Semester} that we are building.
     */
    public SemesterBuilder withAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
        return this;
    }

    /**
     * Sets the {@code roomFees} of the {@code Semester} that we are building.
     */
    public SemesterBuilder withRoomFees(HashMap<RoomType, RoomRate> roomFees) {
        this.roomFees = roomFees;
        return this;
    }

    /**
     * Returns a new {@code Semester} created.
     */
    public Semester build() {
        return new Semester(academicYear, semesterNumber, allocations, roomFees);
    }
}
