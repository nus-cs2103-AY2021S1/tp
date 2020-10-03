package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BuildingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Building(null));
    }

    @Test
    public void constructor_invalidDepartment_throwsIllegalArgumentException() {
        String invalidBuilding = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidBuilding));
    }

    @Test
    public void isValidBuilding() {
        // null department
        assertThrows(NullPointerException.class, () -> Building.isValidBuilding(null));

        // invalid department
        assertFalse(Building.isValidBuilding("")); // empty string
        assertFalse(Building.isValidBuilding(" ")); // spaces only

        // valid department
        assertTrue(Building.isValidBuilding("COM2-02-1"));
        assertTrue(Building.isValidBuilding("-")); // one character
    }

}
