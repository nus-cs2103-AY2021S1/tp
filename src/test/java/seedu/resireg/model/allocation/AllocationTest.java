package seedu.resireg.model.allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.TypicalAllocations.ALLOCATION_ONE;
import static seedu.resireg.testutil.TypicalAllocations.ALLOCATION_TWO;
import static seedu.resireg.testutil.TypicalRooms.ROOM_FOUR;
import static seedu.resireg.testutil.TypicalRooms.ROOM_ONE;
import static seedu.resireg.testutil.TypicalRooms.ROOM_TWO;
import static seedu.resireg.testutil.TypicalStudents.ALICE;
import static seedu.resireg.testutil.TypicalStudents.BENSON;
import static seedu.resireg.testutil.TypicalStudents.DANIEL;

import org.junit.jupiter.api.Test;

import seedu.resireg.testutil.AllocationBuilder;


public class AllocationTest {

    @Test
    public void isRelated() {
        assertTrue(ALLOCATION_ONE.isRelatedTo(ROOM_ONE));
        assertTrue(ALLOCATION_ONE.isRelatedTo(ALICE));

        assertFalse(ALLOCATION_ONE.isRelatedTo(ROOM_TWO));
        assertFalse(ALLOCATION_ONE.isRelatedTo(BENSON));
    }

    @Test
    public void hasSameStudent() {
        Allocation allocationOneCopy = new AllocationBuilder(ALLOCATION_ONE).build();
        assertTrue(ALLOCATION_ONE.hasSameStudent(allocationOneCopy));
        assertTrue(ALLOCATION_ONE.hasSameRoom(allocationOneCopy));

        Allocation allocationOneEditedStudentId = new AllocationBuilder(ALLOCATION_ONE)
                .withStudentId(DANIEL.getStudentId().value).build();
        assertFalse(ALLOCATION_ONE.hasSameStudent(allocationOneEditedStudentId));

        Allocation allocationOneEditedRoom = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_FOUR.getFloor().value)
                .withRoomNumber(ROOM_FOUR.getRoomNumber().value).build();
        assertFalse(ALLOCATION_ONE.hasSameRoom(allocationOneEditedRoom));
    }

    @Test
    public void isSameAllocation() {
        // same object, returns true
        assertTrue(ALLOCATION_ONE.isSameAllocation(ALLOCATION_ONE));

        // null, returns false
        assertFalse(ALLOCATION_ONE.isSameAllocation(null));

        // different floor but same room number and student id -> returns true
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .build();
        assertTrue(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different room number but same floor and student id -> returns true
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .build();
        assertTrue(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different floor and room number but same student id -> returns true
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .build();
        assertTrue(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different student id but same floor and room number -> returns true
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertTrue(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different student id and floor but same room number -> returns false
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertFalse(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different student id and room number but same floor -> returns false
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertFalse(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));

        // different floor, room number and student id -> returns false
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertFalse(ALLOCATION_ONE.isSameAllocation(editedAllocationOne));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Allocation allocationOneCopy = new AllocationBuilder(ALLOCATION_ONE).build();
        assertTrue(ALLOCATION_ONE.equals(allocationOneCopy));

        // same object -> returns true
        assertTrue(ALLOCATION_ONE.equals(ALLOCATION_ONE));

        // null -> returns false
        assertFalse(ALLOCATION_ONE.equals(null));

        // different type -> returns false
        assertFalse(ALLOCATION_ONE.equals(7));

        // different allocation -> returns false
        assertFalse(ALLOCATION_ONE.equals(ALLOCATION_TWO));

        // different floor -> returns false
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .build();
        assertFalse(ALLOCATION_ONE.equals(editedAllocationOne));

        // different room number -> returns false
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .build();
        assertFalse(ALLOCATION_ONE.equals(editedAllocationOne));

        // different student id -> returns false
        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertFalse(ALLOCATION_ONE.equals(editedAllocationOne));
    }

    @Test
    public void isSameHashCode() {
        Allocation allocationOneCopy = new AllocationBuilder(ALLOCATION_ONE).build();
        assertEquals(ALLOCATION_ONE.hashCode(), allocationOneCopy.hashCode());

        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.hashCode(), editedAllocationOne.hashCode());

        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.hashCode(), editedAllocationOne.hashCode());

        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.hashCode(), editedAllocationOne.hashCode());
    }

    @Test
    public void isSameToString() {
        Allocation allocationOneCopy = new AllocationBuilder(ALLOCATION_ONE).build();
        assertEquals(ALLOCATION_ONE.toString(), allocationOneCopy.toString());

        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withFloor(ROOM_TWO.getFloor().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.toString(), editedAllocationOne.toString());

        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withRoomNumber(ROOM_TWO.getRoomNumber().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.toString(), editedAllocationOne.toString());

        editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
                .withStudentId(BENSON.getStudentId().value)
                .build();
        assertNotEquals(ALLOCATION_ONE.toString(), editedAllocationOne.toString());
    }
}
