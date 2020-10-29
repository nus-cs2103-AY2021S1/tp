package seedu.resireg.testutil;

import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.resireg.model.ResiReg;
import seedu.resireg.model.allocation.Allocation;
import seedu.resireg.model.room.Room;
import seedu.resireg.model.student.Student;

/**
 * A utility class containing a list of {@code Allocation} objects to be used in tests.
 * Assumes typical students and rooms.
 */
public class TypicalAllocations {
    public static final Allocation ALLOCATION_ONE = new AllocationBuilder()
            .withFloor(TypicalRooms.ROOM_ONE.getFloor().value)
            .withRoomNumber(TypicalRooms.ROOM_ONE.getRoomNumber().value)
            .withStudentId(TypicalStudents.ALICE.getStudentId().value)
            .build();

    public static final Allocation ALLOCATION_TWO = new AllocationBuilder()
            .withFloor(TypicalRooms.ROOM_TWO.getFloor().value)
            .withRoomNumber(TypicalRooms.ROOM_TWO.getRoomNumber().value)
            .withStudentId(TypicalStudents.BENSON.getStudentId().value)
            .build();

    public static final Allocation ALLOCATION_THREE = new AllocationBuilder()
            .withFloor(TypicalRooms.ROOM_THREE.getFloor().value)
            .withRoomNumber(TypicalRooms.ROOM_THREE.getRoomNumber().value)
            .withStudentId(TypicalStudents.CARL.getStudentId().value)
            .build();

    // Manually added
    public static final Allocation ALLOCATION_A = new AllocationBuilder()
            .withFloor(VALID_FLOOR_A)
            .withRoomNumber(VALID_ROOM_NUMBER_A)
            .withStudentId(VALID_STUDENT_ID_AMY)
            .build();
    public static final Allocation ALLOCATION_B = new AllocationBuilder()
            .withFloor(VALID_FLOOR_B)
            .withRoomNumber(VALID_ROOM_NUMBER_B)
            .withStudentId(VALID_STUDENT_ID_BOB)
            .build();

    private TypicalAllocations() {} // prevents instantiation

    /**
     * Returns an {@code ResiReg} with all the typical allocations.
     */
    public static ResiReg getTypicalResiReg() {
        ResiReg ab = new ResiReg();
        for (Student student : TypicalStudents.getTypicalStudents()) {
            ab.addStudent(student);
        }
        for (Room room : TypicalRooms.getTypicalRooms()) {
            ab.addRoom(room);
        }
        for (Allocation allocation: getTypicalAllocations()) {
            ab.addAllocation(allocation);
        }
        return ab;
    }

    public static List<Allocation> getTypicalAllocations() {
        return new ArrayList<>(Arrays.asList(ALLOCATION_ONE, ALLOCATION_TWO, ALLOCATION_THREE));
    }
}
