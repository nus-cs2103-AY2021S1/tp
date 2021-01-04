package seedu.homerce.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeOfDay(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new TimeOfDay(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // Null status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));
        //Invalid status
        assertFalse(Status.isValidStatus("")); //empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("t"));
        assertFalse(Status.isValidStatus("f"));
        assertFalse(Status.isValidStatus("yes"));
        assertFalse(Status.isValidStatus("no"));
        //Valid Status
        assertTrue(Status.isValidStatus("Y"));
        assertTrue(Status.isValidStatus("N"));
        assertTrue(Status.isValidStatus("y"));
        assertTrue(Status.isValidStatus("n"));
    }

    @Test
    public void toStringTest() {
        Status status1 = new Status("y");
        assertEquals("Yes", status1.toString());
        Status status2 = new Status("n");
        assertEquals("No", status2.toString());
    }

    @Test
    public void markDoneTest() {
        Status status = new Status("n");
        assertFalse(status.isDone());
        status.markDone();
        assertTrue(status.isDone());
        status.markUnDone();
        assertFalse(status.isDone());
    }

    @Test
    public void equalityTest() {
        Status status1 = new Status("n");
        Status status2 = new Status("n");
        assertEquals(status1, status2);
        status1.markDone();
        assertNotEquals(status1, status2);
        status2.markDone();
        assertEquals(status1, status2);
    }
}
