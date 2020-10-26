package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    private static final String ACTIVE_SMALL = "active";
    private static final String ACTIVE_CAPS = "ACTIVE";
    private static final String ACTIVE_MIX = "aCtIvE";
    private static final String COLD_SMALL = "cold";
    private static final String COLD_CAPS = "COLD";
    private static final String COLD_MIX = "cOLd";
    private static final String CLOSED_SMALL = "closed";
    private static final String CLOSED_CAPS = "CLOSED";
    private static final String CLOSED_MIX = "cLoSeD";
    private static final String INVALID_STATUS = "Hello";
    private static final String EMPTY = "";
    private static final String BLANK = " ";

    @Test
    public void createStatus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Status.createStatus(null));
    }

    @Test
    public void createStatus_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Status.createStatus(INVALID_STATUS));
        assertThrows(IllegalArgumentException.class, () -> Status.createStatus(EMPTY));
        assertThrows(IllegalArgumentException.class, () -> Status.createStatus(BLANK));
    }

    @Test
    public void isValidStatus_validStatus_true() {
        assertTrue(Status.isValidStatus(ACTIVE_SMALL));
        assertTrue(Status.isValidStatus(ACTIVE_MIX));
        assertTrue(Status.isValidStatus(ACTIVE_CAPS));
        assertTrue(Status.isValidStatus(COLD_SMALL));
        assertTrue(Status.isValidStatus(COLD_MIX));
        assertTrue(Status.isValidStatus(COLD_CAPS));
        assertTrue(Status.isValidStatus(CLOSED_SMALL));
        assertTrue(Status.isValidStatus(CLOSED_MIX));
        assertTrue(Status.isValidStatus(CLOSED_CAPS));
    }

    @Test
    public void isValidStatus_invalidStatus_false() {
        assertFalse(Status.isValidStatus(INVALID_STATUS));
        assertFalse(Status.isValidStatus(EMPTY));
        assertFalse(Status.isValidStatus(BLANK));
    }
}
