package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.roomtype.RoomType;
import seedu.resireg.model.semester.academicyear.AcademicYear;
import seedu.resireg.model.semester.roomrate.RoomRate;
import seedu.resireg.model.semester.semesternumber.SemesterNumber;

/**
 * Represents a Semester in ResiReg.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Semester {

    // Identity fields
    private final AcademicYear academicYear;
    private final SemesterNumber semesterNumber;

    // Data fields
    private final List<Allocation> allocations;
    private final Map<RoomType, RoomRate> roomFees;

    /**
     * SemesterNumber should be present and not null.
     */
    public Semester(AcademicYear academicYear, SemesterNumber semesterNumber) {
        requireAllNonNull(academicYear, semesterNumber);
        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
        allocations = new ArrayList<>();
        roomFees = new HashMap<>();
    }

    /**
     * All fields should be present and not null.
     */
    public Semester(AcademicYear academicYear, SemesterNumber semesterNumber,
                    List<Allocation> allocations, Map<RoomType, RoomRate> roomFees) {
        requireAllNonNull(academicYear, semesterNumber, allocations, roomFees);
        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
        this.allocations = allocations;
        this.roomFees = roomFees;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public Map<RoomType, RoomRate> getRoomFees() {
        return roomFees;
    }

    /**
     * Returns true if both semesters have the same academic year and semester number.
     * This defines a weaker notion of equality between two semesters, and includes semesters that begin in the same
     * year and period, but different allocations and fees;
     */
    public boolean isSameSemester(Semester otherSemester) {
        if (otherSemester == this) {
            return true;
        }

        return otherSemester != null
            && Objects.equals(otherSemester.getAcademicYear(), getAcademicYear())
            && Objects.equals(otherSemester.getSemesterNumber(), getSemesterNumber());
    }

    /**
     * Returns the successor of a given Semester. A next semester should be the second semester of the academic year if
     * the given semester is in sem 1, otherwise it should be the first semester of the next academic year.
     *
     * @return the successor of this semester
     */
    public Semester getNextSemester() {
        if (semesterNumber.value == 1) {
            return new Semester(academicYear, new SemesterNumber(2));
        } else {
            return new Semester(new AcademicYear(academicYear.value + 1), new SemesterNumber(1));
        }
    }

    /**
     * @return a shortened String representation of the semester in form of "AY" + year + "S" + semesterNumber.
     */
    public String getShortRepresentation() {
        return String.format("AY%sS%s", academicYear, semesterNumber);
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
        return otherSemester.getAcademicYear().equals(getAcademicYear())
            && otherSemester.getSemesterNumber().equals(getSemesterNumber())
            && Objects.equals(otherSemester.getRoomFees(), getRoomFees())
            && Objects.equals(otherSemester.getAllocations(), getAllocations());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Academic Year: ")
            .append(getAcademicYear())
            .append(" Semester: ")
            .append(getSemesterNumber())
            .append(" Room Fees: (");
        getRoomFees().forEach((roomType, fee) ->
            builder.append(" Room type: ")
                .append(roomType.toString())
                .append(", Fee: ")
                .append(fee)
                .append(" "));
        builder.append(") ");
        return builder.toString();
    }

    public SemesterNumber getSemesterNumber() {
        return semesterNumber;
    }
}
