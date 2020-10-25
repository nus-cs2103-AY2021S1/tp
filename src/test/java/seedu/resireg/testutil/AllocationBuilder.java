package seedu.resireg.testutil;

import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Floor;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.room.RoomNumber;
import seedu.resireg.model.student.*;
import seedu.resireg.model.student.faculty.Faculty;
import seedu.resireg.model.tag.Tag;
import seedu.resireg.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Allocation objects.
 */
public class AllocationBuilder {

    public static final String DEFAULT_FLOOR = "8";
    public static final String DEFAULT_ROOM_NUMBER = "105";
    public static final String DEFAULT_STUDENT_ID = "E0407889";

    private Floor floor;
    private RoomNumber roomNumber;
    private StudentId studentId;

    /**
     * Creates a {@code AllocationBuilder} with the default details.
     */
    public AllocationBuilder() {
        floor = new Floor(DEFAULT_FLOOR);
        roomNumber = new RoomNumber(DEFAULT_ROOM_NUMBER);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
    }

    /**
     * Initializes the AllocationBuilder with the data of {@code allocationToCopy}.
     */
    public AllocationBuilder(Allocation allocationToCopy) {
        floor = allocationToCopy.getFloor();
        roomNumber = allocationToCopy.getRoomNumber();
        studentId = allocationToCopy.getStudentId();
    }

    /**
     * Sets the {@code Floor} of the {@code Allocation} that we are building.
     */
    public AllocationBuilder withFloor(String floor) {
        this.floor = new Floor(floor);
        return this;
    }

    /**
     * Sets the {@code RoomNumber} of the {@code Allocation} that we are building.
     */
    public AllocationBuilder withRoomNumber(String roomNumber) {
        this.roomNumber = new RoomNumber(roomNumber);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code Allocation} that we are building.
     */
    public AllocationBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Returns a new {@code Allocation} created.
     */
    public Allocation build() {
        return new Allocation(floor, roomNumber, studentId);
    }

}
