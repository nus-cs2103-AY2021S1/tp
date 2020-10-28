package seedu.address.model.location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalLocations.DENVER;
import static seedu.address.testutil.TypicalLocations.NEW_YORK;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.model.location.exceptions.DuplicateLocationException;
import seedu.address.model.location.exceptions.LocationNotFoundException;

public class UniqueLocationListTest {

    @Test
    public void contains() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        assertTrue(uList.contains(NEW_YORK));
        assertFalse(uList.contains(DENVER));
    }

    @Test
    public void add() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        assertEquals(1, uList.asUnmodifiableObservableList().size());

        // duplicate add -> duplicate location error
        assertThrows(DuplicateLocationException.class, () -> uList.add(NEW_YORK));
    }

    @Test
    public void setLocation() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        uList.setLocation(NEW_YORK, DENVER);
        assertTrue(uList.contains(DENVER));
        assertFalse(uList.contains(NEW_YORK));
    }

    /**
     * Tests that setLocation throws an exception when location is not found.
     */
    @Test
    public void setLocation_throwsLocationNotFoundException() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        assertThrows(LocationNotFoundException.class, () -> uList.setLocation(DENVER, NEW_YORK));
    }

    /**
     * Tests that setLocation throws an exception when input is a duplicate location.
     */
    @Test
    public void setLocation_throwsDuplicateLocationException() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(DENVER);
        uList.add(NEW_YORK);
        assertThrows(DuplicateLocationException.class, () -> uList.setLocation(DENVER, NEW_YORK));
    }

    @Test
    public void remove() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(DENVER);
        uList.remove(DENVER);
        assertFalse(uList.contains(DENVER));
    }

    /**
     * Tests that exception is thrown when attempting to
     * remove location which does not exist.
     */
    @Test
    public void remove_throwsLocationNotFoundException() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        assertThrows(LocationNotFoundException.class, ()-> uList.remove(DENVER));
    }

    /**
     * Tests replacing a UniqueLocationList with another.
     */
    @Test
    public void setLocations() {
        UniqueLocationList uList = new UniqueLocationList();
        UniqueLocationList uList2 = new UniqueLocationList();
        uList2.add(NEW_YORK);
        uList.setLocations(uList2);
        assertTrue(uList.contains(NEW_YORK));
    }

    /**
     * Tests replacing a UniqueLocationList with a list.
     */
    @Test
    public void setLocations_success() {
        UniqueLocationList uList = new UniqueLocationList();
        ArrayList<Location> arr = new ArrayList<>();
        arr.add(NEW_YORK);
        uList.setLocations(arr);
        assertTrue(uList.contains(NEW_YORK));
    }

    @Test
    public void setLocations_throwsDuplicateLocationException() {
        UniqueLocationList uList = new UniqueLocationList();
        ArrayList<Location> arr = new ArrayList<>();
        arr.add(NEW_YORK);
        arr.add(NEW_YORK);
        assertThrows(DuplicateLocationException.class, () -> uList.setLocations(arr));
    }

    @Test
    public void findLocationFromId_validInput_returnsLocation() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(DENVER);
        uList.add(NEW_YORK);
        int id = NEW_YORK.getId();
        assertEquals(NEW_YORK, uList.findLocationFromId(id).get());
    }

    @Test
    public void findLocationFromId_invalidInput_throwsNoSuchElementException() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(DENVER);
        uList.add(NEW_YORK);
        assertThrows(NoSuchElementException.class, () -> uList.findLocationFromId(-1).get());
    }

    @Test
    public void findLocationId_validInput_returnsId() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(DENVER);
        uList.add(NEW_YORK);
        int id = NEW_YORK.getId();
        assertEquals(id, uList.findLocationID(NEW_YORK));
    }

    @Test
    public void findLocationId_invalidInput_throwsNoSuchElementException() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        assertThrows(NoSuchElementException.class, () -> uList.findLocationID(DENVER));
    }

    @Test
    public void equals() {
        UniqueLocationList uList = new UniqueLocationList();
        uList.add(NEW_YORK);
        // same object -> returns true
        assertTrue(uList.equals(uList));

        // null -> returns false
        assertFalse(uList.equals(null));

        // same internal lists
        UniqueLocationList uList2 = new UniqueLocationList();
        uList2.add(NEW_YORK);
        assertTrue(uList.equals(uList2));

        // different internal lists
        UniqueLocationList uList3 = new UniqueLocationList();
        uList3.add(DENVER);
        assertFalse(uList.equals(uList3));
    }
}
