package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void isValidStatus() {
        // null Status
        assertThrows(NullPointerException.class, () -> Status.isValidStatus(null));

        // invalid Status, random characters
        assertFalse(Status.isValidStatus("")); // empty string
        assertFalse(Status.isValidStatus(" ")); // spaces only
        assertFalse(Status.isValidStatus("$")); // only non-alphanumeric characters
        assertFalse(Status.isValidStatus("peter*")); // contains non-alphanumeric characters
        assertFalse(Status.isValidStatus("1234")); // contains all number

        // invalid Status, all uppercase
        assertFalse(Status.isValidStatus("C O M P L E T E D")); // contains whitespaces in between
        assertFalse(Status.isValidStatus("COMPLET ED")); // contains one whitespace
        assertFalse(Status.isValidStatus(" COMPLETED")); // contains whitespace in the front
        assertFalse(Status.isValidStatus("COMPLETED ")); // contains trailing whitespace

        // invalid Status, all lowercase
        assertFalse(Status.isValidStatus("completed"));
        assertFalse(Status.isValidStatus("c o m p l e t e d")); // contains whitespaces in between
        assertFalse(Status.isValidStatus("complet ed")); // contains one whitespace
        assertFalse(Status.isValidStatus(" completed")); // contains whitespace in the front
        assertFalse(Status.isValidStatus("completed ")); // contains trailing whitespace

        // invalid Status, mixed
        assertFalse(Status.isValidStatus("Completed"));
        assertFalse(Status.isValidStatus("C o m p l e t e d")); // contains whitespaces in between
        assertFalse(Status.isValidStatus("Complet ed")); // contains one whitespace
        assertFalse(Status.isValidStatus(" Completed")); // contains whitespace in the front
        assertFalse(Status.isValidStatus("Completed ")); // contains trailing whitespace

        // valid Status, all uppercase
        assertTrue(Status.isValidStatus("COMPLETED"));
        assertTrue(Status.isValidStatus("NOT_COMPLETED"));
    }
}
