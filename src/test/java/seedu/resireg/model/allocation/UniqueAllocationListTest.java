package seedu.resireg.model.allocation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;
import static seedu.resireg.testutil.TypicalAllocations.ALLOCATION_ONE;
import static seedu.resireg.testutil.TypicalAllocations.ALLOCATION_TWO;
import static seedu.resireg.testutil.TypicalRooms.ROOM_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.allocation.exceptions.AllocationNotFoundException;
import seedu.resireg.model.allocation.exceptions.DuplicateAllocationException;
import seedu.resireg.testutil.AllocationBuilder;


public class UniqueAllocationListTest {

    private final UniqueAllocationList uniqueAllocationList = new UniqueAllocationList();

    @Test
    public void contains_nullAllocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAllocationList.contains((Allocation) null));
    }

    @Test
    public void contains_allocationNotInList_returnsFalse() {
        assertFalse(uniqueAllocationList.contains(ALLOCATION_ONE));
    }

    @Test
    public void contains_allocationInList_returnsTrue() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        assertTrue(uniqueAllocationList.contains(ALLOCATION_ONE));
    }

    @Test
    public void contains_allocationWithSameIdentityFieldsDifferentRoomNumberInList_returnsTrue() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
            .withRoomNumber(ROOM_B.getRoomNumber().value)
            .build();
        assertTrue(uniqueAllocationList.contains(editedAllocationOne));
    }

    @Test
    public void contains_allocationWithSameIdentityFieldsDifferentFloorInList_returnsTrue() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
            .withFloor(ROOM_B.getFloor().value)
            .build();
        assertTrue(uniqueAllocationList.contains(editedAllocationOne));
    }

    @Test
    public void add_nullAllocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAllocationList.add(null));
    }

    @Test
    public void add_duplicateAllocation_throwsDuplicateAllocationException() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        assertThrows(DuplicateAllocationException.class, () -> uniqueAllocationList.add(ALLOCATION_ONE));
    }

    @Test
    public void setAllocation_nullTargetAllocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAllocationList.setAllocation(null, ALLOCATION_ONE));
    }

    @Test
    public void setAllocation_nullEditedAllocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniqueAllocationList.setAllocation(ALLOCATION_ONE, null));
    }

    @Test
    public void setAllocation_targetAllocationNotInList_throwsAllocationNotFoundException() {
        assertThrows(AllocationNotFoundException.class, () ->
            uniqueAllocationList.setAllocation(ALLOCATION_ONE, ALLOCATION_ONE));
    }

    @Test
    public void setAllocation_editedAllocationIsSameAllocation_success() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        uniqueAllocationList.setAllocation(ALLOCATION_ONE, ALLOCATION_ONE);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(ALLOCATION_ONE);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocation_editedAllocationHasSameIdentityDifferentFloor_success() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
            .withFloor(ROOM_B.getFloor().value)
            .build();
        uniqueAllocationList.setAllocation(ALLOCATION_ONE, editedAllocationOne);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(editedAllocationOne);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocation_editedAllocationHasSameIdentityDifferentRoomNumber_success() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        Allocation editedAllocationOne = new AllocationBuilder(ALLOCATION_ONE)
            .withRoomNumber(ROOM_B.getRoomNumber().value)
            .build();
        uniqueAllocationList.setAllocation(ALLOCATION_ONE, editedAllocationOne);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(editedAllocationOne);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocation_editedAllocationHasDifferentIdentity_success() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        uniqueAllocationList.setAllocation(ALLOCATION_ONE, ALLOCATION_TWO);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(ALLOCATION_TWO);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocation_editedAllocationHasNonUniqueIdentity_throwsDuplicateAllocationException() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        uniqueAllocationList.add(ALLOCATION_TWO);
        assertThrows(DuplicateAllocationException.class, () ->
                uniqueAllocationList.setAllocation(ALLOCATION_ONE, ALLOCATION_TWO));
    }

    @Test
    public void remove_nullAllocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAllocationList.remove(null));
    }

    @Test
    public void remove_allocationDoesNotExist_throwsAllocationNotFoundException() {
        assertThrows(AllocationNotFoundException.class, () -> uniqueAllocationList.remove(ALLOCATION_ONE));
    }

    @Test
    public void remove_existingAllocation_removesAllocation() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        uniqueAllocationList.remove(ALLOCATION_ONE);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocations_nullUniqueAllocationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniqueAllocationList.setAllocations((UniqueAllocationList) null));
    }

    @Test
    public void setAllocation_uniqueAllocationList_replacesOwnListWithProvidedUniqueAllocationList() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(ALLOCATION_ONE);
        uniqueAllocationList.setAllocations(expectedUniqueAllocationList);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocation_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAllocationList.setAllocations((List<Allocation>) null));
    }

    @Test
    public void setAllocations_list_replacesOwnListWithProvidedList() {
        uniqueAllocationList.add(ALLOCATION_ONE);
        List<Allocation> allocationList = Collections.singletonList(ALLOCATION_TWO);
        uniqueAllocationList.setAllocations(allocationList);
        UniqueAllocationList expectedUniqueAllocationList = new UniqueAllocationList();
        expectedUniqueAllocationList.add(ALLOCATION_TWO);
        assertEquals(expectedUniqueAllocationList, uniqueAllocationList);
    }

    @Test
    public void setAllocations_listWithDuplicateAllocations_throwsDuplicateAllocationException() {
        List<Allocation> listWithDuplicateAllocations = Arrays.asList(ALLOCATION_ONE, ALLOCATION_ONE);
        assertThrows(DuplicateAllocationException.class, () ->
            uniqueAllocationList.setAllocations(listWithDuplicateAllocations));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniqueAllocationList.asUnmodifiableObservableList().remove(0));
    }
}
