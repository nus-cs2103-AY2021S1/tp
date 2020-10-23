package seedu.resireg.model.semester;

import static seedu.resireg.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.roomtype.RoomType;

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

    @Override
    public int hashCode() {
        return Objects.hash(academicYear, semesterNumber);
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
