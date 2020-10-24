package seedu.resireg.model.semester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.resireg.model.semester.roomrate.RoomRate;

class RoomRateTest {
    @Test
    public void constructor_invalidRoomRate_throwsIllegalArgumentException() {
        int invalidRoomRate = -1;
        assertThrows(IllegalArgumentException.class, () -> new RoomRate(invalidRoomRate));
    }

    @Test
    void isValidRoomRate() {
        // invalid room rates
        assertFalse(RoomRate.isValidRoomRate(0)); // zero
        assertFalse(RoomRate.isValidRoomRate(-1)); // negative numbers
        assertFalse(RoomRate.isValidRoomRate(Integer.MIN_VALUE)); // boundary

        // valid room rates
        assertTrue(RoomRate.isValidRoomRate(1)); // positive number
        assertTrue(RoomRate.isValidRoomRate(Integer.MAX_VALUE)); // boundary. Infeasible, but hey
    }

    @Test
    void testHashCode() {
        RoomRate rateA = new RoomRate(2500);
        RoomRate rateB = new RoomRate(2500);

        // same room rates should have same hashcode
        assertEquals(rateA.hashCode(), rateB.hashCode());
    }
}
